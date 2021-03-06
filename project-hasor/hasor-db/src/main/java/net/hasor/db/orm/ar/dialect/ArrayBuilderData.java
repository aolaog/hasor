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
package net.hasor.db.orm.ar.dialect;
import net.hasor.db.orm.ar.SQLBuilder.BuilderData;
import org.more.builder.ReflectionToStringBuilder;
import org.more.builder.ToStringStyle;
/**
 * 
 * @version : 2015年2月13日
 * @author 赵永春(zyc@hasor.net)
 */
public class ArrayBuilderData extends AbstractBuilderData implements BuilderData {
    private Object[] sqlData;
    public ArrayBuilderData(String sqlString, Object[] sqlData) {
        super(sqlString);
        this.sqlData = sqlData == null ? new Object[0] : sqlData;
    }
    public Object[] getData() {
        return this.sqlData;
    }
    public String toString() {
        return super.toString() + " -- params:" + //
                ReflectionToStringBuilder.toString(getData(), ToStringStyle.SIMPLE_STYLE);
    }
}