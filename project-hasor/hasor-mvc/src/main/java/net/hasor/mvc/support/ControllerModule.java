/*
 * Copyright 2008-2009 the original 赵永春(zyc@hasor.net).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.hasor.mvc.support;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.UUID;
import net.hasor.core.ApiBinder;
import net.hasor.core.EventContext;
import net.hasor.core.Hasor;
import net.hasor.core.Module;
import net.hasor.mvc.MappingTo;
import net.hasor.mvc.ModelController;
import net.hasor.mvc.strategy.CallStrategyFactory;
import net.hasor.mvc.strategy.DefaultCallStrategyFactory;
import org.more.logger.LoggerHelper;
/***
 * 创建MVC环境
 * @version : 2014-1-13
 * @author 赵永春(zyc@hasor.net)
 */
public class ControllerModule implements Module {
    /**通过位运算决定check是否在data里。*/
    private static boolean checkIn(final int data, final int check) {
        int or = data | check;
        return or == data;
    };
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        LoggerHelper.logInfo("work at ControllerModule.", this.getClass());
        //1.搜索ModelController
        Set<Class<?>> controllerSet = apiBinder.findClass(ModelController.class);
        if (controllerSet == null || controllerSet.isEmpty() == true) {
            LoggerHelper.logInfo("load Controller, controllerSet isEmpty.", this.getClass());
            return;
        }
        CallStrategyFactory strategyFactory = this.createCallStrategyFactory(apiBinder);
        LoggerHelper.logInfo("create CallStrategyFactory. type is " + strategyFactory.getClass());
        //2.绑定到Hasor
        for (Class<?> clazz : controllerSet) {
            int modifier = clazz.getModifiers();
            if (checkIn(modifier, Modifier.INTERFACE) || checkIn(modifier, Modifier.ABSTRACT)) {
                continue;
            }
            //
            String newID = UUID.randomUUID().toString();
            boolean hasMapping = false;
            //
            Method[] methodArrays = clazz.getMethods();
            for (Method atMethod : methodArrays) {
                if (atMethod.isAnnotationPresent(MappingTo.class) == false) {
                    continue;
                }
                hasMapping = true;
                //
                MappingTo mto = atMethod.getAnnotation(MappingTo.class);
                LoggerHelper.logInfo("method ‘%s’ mappingTo: ‘%s’, form Type :%s.", atMethod.getName(), mto.value(), clazz.getName());
                apiBinder.bindType(MappingDefine.class).uniqueName().toInstance(createMappingDefine(newID, atMethod, strategyFactory));
            }
            //
            if (hasMapping == true) {
                apiBinder.bindType(clazz).idWith(newID);
            }
        }
        //3.安装服务
        apiBinder.bindType(RootController.class).toInstance(apiBinder.autoAware(new RootController()));
    }
    /**创建 {@link CallStrategyFactory}*/
    protected CallStrategyFactory createCallStrategyFactory(ApiBinder apiBinder) {
        return new DefaultCallStrategyFactory(apiBinder);
    }
    /**
     * 创建 {@link MappingDefine}
     * @param newID 唯一ID
     * @param atMethod 映射的方法
     * @param strategyFactory CallStrategy 工厂。
     * @return 返回mvc定义。
     */
    protected MappingDefine createMappingDefine(String newID, Method atMethod, CallStrategyFactory strategyFactory) {
        return new MappingDefine(newID, atMethod, strategyFactory);
    }
}