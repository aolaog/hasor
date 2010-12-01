/*
 * Copyright 2008-2009 the original author or authors.
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
package org.more.hypha.beans.support;
import org.more.core.xml.XmlElementHook;
import org.more.core.xml.XmlStackDecorator;
import org.more.core.xml.stream.EndElementEvent;
import org.more.core.xml.stream.StartElementEvent;
import org.more.hypha.context.Tag_Abstract;
import org.more.hypha.context.XmlDefineResource;
/**
 * 用于解析/beans标签
 * @version 2010-9-16
 * @author 赵永春 (zyc@byshell.org)
 */
public class TagBeans_Beans extends Tag_Abstract implements XmlElementHook {
    /**保存于上下文中的Bean管理器。*/
    public static final String BeanDefineManager = "$more_Beans_BeanDefineManager";
    /**创建{@link TagBeans_Beans}对象*/
    public TagBeans_Beans(XmlDefineResource configuration) {
        super(configuration);
    }
    public void beginElement(XmlStackDecorator context, String xpath, StartElementEvent event) {
        context.createStack();
    }
    public void endElement(XmlStackDecorator context, String xpath, EndElementEvent event) {
        context.dropStack();
    }
}