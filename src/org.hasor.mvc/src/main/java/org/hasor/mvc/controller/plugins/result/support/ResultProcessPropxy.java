/*
 * Copyright 2008-2009 the original ������(zyc@hasor.net).
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
package org.hasor.mvc.controller.plugins.result.support;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hasor.context.AppContext;
import org.hasor.mvc.controller.plugins.result.ControllerResultProcess;
/**
 * 
 * @version : 2013-4-11
 * @author ������ (zyc@hasor.net)
 */
class ResultProcessPropxy implements ControllerResultProcess {
    private AppContext                               appContext        = null;
    private String                                   annoType          = null;
    private Class<? extends ControllerResultProcess> targetProcessType = null;
    private ControllerResultProcess                  targetProcess     = null;
    // 
    public ResultProcessPropxy(String annoType, Class<? extends ControllerResultProcess> targetProcessType, AppContext appContext) {
        this.annoType = annoType;
        this.targetProcessType = targetProcessType;
        this.appContext = appContext;
    }
    public String getName() {
        return this.annoType;
    }
    public void process(HttpServletRequest request, HttpServletResponse response, Object result) throws ServletException, IOException {
        if (this.targetProcess == null)
            this.targetProcess = appContext.getInstance(this.targetProcessType);
        this.targetProcess.process(request, response, result);
    }
}