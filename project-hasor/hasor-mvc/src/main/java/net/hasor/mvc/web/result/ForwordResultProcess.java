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
package net.hasor.mvc.web.result;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.hasor.mvc.result.ResultDefine;
import net.hasor.mvc.result.ResultProcess;
import net.hasor.mvc.support.Call;
import net.hasor.mvc.web.WebCall;
import org.more.logger.LoggerHelper;
/**
* 
* @version : 2013-6-5
* @author 赵永春 (zyc@hasor.net)
*/
@ResultDefine(Forword.class)
public class ForwordResultProcess implements ResultProcess {
    public Object returnData(Object result, Call call) throws ServletException, IOException {
        if (result == null) {
            return result;
        }
        if (call instanceof WebCall == false) {
            return result;
        }
        WebCall webCall = (WebCall) call;
        HttpServletRequest request = webCall.getHttpRequest();
        HttpServletResponse response = webCall.getHttpResponse();
        LoggerHelper.logFine("forword to %s.", result);
        //
        request.getRequestDispatcher(result.toString()).forward(request, response);
        return result;
    }
}