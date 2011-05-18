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
package org.more.hypha.commons.engine;
import org.more.hypha.AbstractBeanDefine;
import org.more.hypha.ApplicationContext;
/**
 * �ýӿ��ǻ�����bean��ȡ�ӿڣ��ýӿڵ�ְ���Ǹ���bean���岢�ҽ����bean���崴��������
 * @version : 2011-4-22
 * @author ������ (zyc@byshell.org)
 */
public abstract class IocEngine {
    /**��ʼ�������� */
    public void init(ApplicationContext context, ValueMetaDataParser<?> rootParser) throws Throwable {}
    /**���ٷ�����*/
    public void destroy() throws Throwable {}
    /**ִ��ע�롣*/
    public abstract void ioc(Object obj, AbstractBeanDefine define, Object[] params);
};