/*
 * Copyright to the original author or authors.
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
package mstc.model.build

import mstc.model.parts.ExertionEvaluator
import mstc.model.parts.ExpressionEvaluator
import mstc.model.parts.Output

/**
 *
 * @author Dennis Reedy
 */
class OutputBuilder {

    OutputBuilder output(Output output) {
        StringBuilder b = new StringBuilder()
        VarBuilder varBuilder = new VarBuilder()
        b.append(varBuilder.var(output).addToList("outputVarList").build())
        println b.toString()
        output.computedBy.each { e ->
            if(e.evaluatorRef instanceof ExertionEvaluator) {
                ExertionEvaluator ee = e.evaluatorRef
                int ndx = ee.interfaceClass.lastIndexOf(".")
                String simpleName = ee.interfaceClass.substring(ndx+1, ee.interfaceClass.length())
                String lowerCaseSimpleName = "${simpleName.charAt(0).toLowerCase()}${simpleName.substring(1)}"
                println "${simpleName}Context ${lowerCaseSimpleName}Context = new ${simpleName}Context(\"${ee.name}\");"
            }
            if(e.evaluatorRef instanceof ExpressionEvaluator) {

            }
        }
        this
    }

}
