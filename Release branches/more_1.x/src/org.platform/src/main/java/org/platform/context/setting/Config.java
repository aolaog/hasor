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
package org.platform.context.setting;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import org.platform.context.SettingListener;
/**
 * ���ö���
 * @version : 2013-3-28
 * @author ������ (zyc@byshell.org)
 */
public interface Config {
    /**��ȡ{@link ServletContext}����*/
    public ServletContext getServletContext();
    /**��ȡһ��ָ���ĳ�ʼ������.*/
    public String getInitParameter(String name);
    /**��ȡ��ʼ���������Ƽ���.*/
    public Enumeration<String> getInitParameterNames();
    /**��ȡӦ�ó������á�*/
    public Settings getSettings();
    /**���������ļ��ı��¼���������*/
    public void addSettingsListener(SettingListener settingsListener);
    /**ɾ�������ļ��ı��¼���������*/
    public void removeSettingsListener(SettingListener settingsListener);
}