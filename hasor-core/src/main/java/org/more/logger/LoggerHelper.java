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
package org.more.logger;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
/**
 * 日志工具类
 * @version : 2015年1月6日
 * @author 赵永春(zyc@hasor.net)
 */
public class LoggerHelper {
    private static class CallerFilter implements Filter {
        private Filter            filter;
        private StackTraceElement onCode;
        public CallerFilter(Filter filter, StackTraceElement onCode) {
            this.filter = filter;
            this.onCode = onCode;
        }
        public boolean isLoggable(LogRecord record) {
            record.setSourceClassName(onCode.getClassName());
            record.setSourceMethodName(onCode.getMethodName());
            record.setSequenceNumber(onCode.getLineNumber());
            record.setResourceBundleName(onCode.getFileName());
            //
            if (this.filter != null)
                return this.filter.isLoggable(record);
            return true;
        }
    }
    private static Logger getLogger() {
        StackTraceElement[] stackElements = Thread.currentThread().getStackTrace();
        StackTraceElement onCode = stackElements[5];
        //
        Logger logger = Logger.getLogger(onCode.getClassName());
        logger.setFilter(new CallerFilter(logger.getFilter(), onCode));
        return logger;
    }
    //
    //
    //
    /**是否启用 Severe 级日志。*/
    public static boolean isEnableSevereLoggable() {
        return getLogger().isLoggable(Level.SEVERE);
    }
    /**是否启用 Warning 级日志。*/
    public static boolean isEnableWarningLoggable() {
        return getLogger().isLoggable(Level.WARNING);
    }
    /**是否启用 Info 级日志。*/
    public static boolean isEnableInfoLoggable() {
        return getLogger().isLoggable(Level.INFO);
    }
    /**是否启用 Config 级日志。*/
    public static boolean isEnableConfigLoggable() {
        return getLogger().isLoggable(Level.CONFIG);
    }
    /**是否启用 Fine级别。*/
    public static boolean isEnableFineLoggable() {
        return getLogger().isLoggable(Level.FINE);
    }
    /**是否启用 Finer级别。*/
    public static boolean isEnableFinerLoggable() {
        return getLogger().isLoggable(Level.FINER);
    }
    /**是否启用 Finest级别。*/
    public static boolean isEnableFinestLoggable() {
        return getLogger().isLoggable(Level.FINEST);
    }
    /**
     * 输出 <i><b>严重</b></i> 日志信息。该方法使用：<code>String.format(String, Object[])</code>方式实现。
     * @param string 要输出的日志信息，或将要输出的格式化日志信息。
     * @param params 需要被格式化的内容。
     */
    public static void logSevere(String string, Object... params) {
        Logger log = getLogger();
        if (log.isLoggable(Level.SEVERE) == false)
            return;
        log.severe(String.format(string, params));
    }
    /**
     * 输出 <i><b>警告</b></i> 日志信息。该方法使用：<code>String.format(String, Object[])</code>方式实现。
     * @param string 要输出的日志信息，或将要输出的格式化日志信息。
     * @param params 需要被格式化的内容。
     */
    public static void logWarn(String string, Object... params) {
        Logger log = getLogger();
        if (log.isLoggable(Level.WARNING) == false)
            return;
        log.warning(String.format(string, params));
    }
    /**
     * 输出 <i><b>信息</b></i> 日志信息。该方法使用：<code>String.format(String, Object[])</code>方式实现。
     * @param string 要输出的日志信息，或将要输出的格式化日志信息。
     * @param params 需要被格式化的内容。
     */
    public static void logInfo(final String string, final Object... params) {
        Logger log = getLogger();
        if (log.isLoggable(Level.INFO) == false)
            return;
        log.info(String.format(string, params));
    }
    /**
     * 输出 <i><b>静态配置</b></i> 日志信息。该方法使用：<code>String.format(String, Object[])</code>方式实现。
     * @param string 要输出的日志信息，或将要输出的格式化日志信息。
     * @param params 需要被格式化的内容。
     */
    public static void logConfig(final String string, final Object... params) {
        Logger log = getLogger();
        if (log.isLoggable(Level.CONFIG) == false)
            return;
        log.config(String.format(string, params));
    }
    /**
     * 输出 <i><b>精细的</b></i> 日志信息。该方法使用：<code>String.format(String, Object[])</code>方式实现。
     * @param string 要输出的日志信息，或将要输出的格式化日志信息。
     * @param params 需要被格式化的内容。
     */
    public static void logFine(final String string, final Object... params) {
        Logger log = getLogger();
        if (log.isLoggable(Level.FINE) == false)
            return;
        log.fine(String.format(string, params));
    }
    /**
     * 输出 <i><b>更加精细的</b></i> 日志信息。该方法使用：<code>String.format(String, Object[])</code>方式实现。
     * @param string 要输出的日志信息，或将要输出的格式化日志信息。
     * @param params 需要被格式化的内容。
     */
    public static void logFiner(final String string, final Object... params) {
        Logger log = getLogger();
        if (log.isLoggable(Level.FINER) == false)
            return;
        log.finer(String.format(string, params));
    }
    /**
     * 输出 <i><b>最精细的</b></i> 日志信息。该方法使用：<code>String.format(String, Object[])</code>方式实现。
     * @param string 要输出的日志信息，或将要输出的格式化日志信息。
     * @param params 需要被格式化的内容。
     */
    public static void logFinest(final String string, final Object... params) {
        Logger log = getLogger();
        if (log.isLoggable(Level.FINEST) == false)
            return;
        log.finest(String.format(string, params));
    }
}