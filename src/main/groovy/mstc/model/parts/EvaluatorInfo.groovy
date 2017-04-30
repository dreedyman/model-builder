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
package mstc.model.parts

/**
 *
 * @author Dennis Reedy
 */
class EvaluatorInfo {
    List<String> inputs = []
    String name
    String operation
    boolean usingSpace = true
    boolean provisioned = true
    String fidelity
    Evaluator evaluatorRef

    EvaluatorInfo(String name) {
        this.name = name
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder()
        b.append("\t\tEvaluator: name: ${name}\n")
        if(operation)
            b.append("\t\t\t\toperation: ${operation}\n")
        if(fidelity)
            b.append("\t\t\t\tfidelity: ${fidelity}\n")
        b.append("\t\t\t\tinputs=${inputs}\n")
        if(evaluatorRef instanceof ExertionEvaluator) {
            b.append("\t\t\t\tdeployArtifact: ${evaluatorRef.deployArtifact}\n")
            b.append("\t\t\t\tinterfaceClass: ${evaluatorRef.interfaceClass}\n")
            b.append("\t\t\t\tusingSpace : ${usingSpace}, provisioned: ${provisioned}\n")
        }
        if(evaluatorRef instanceof ExpressionEvaluator) {
            b.append("\t\t\t\texpression: ${evaluatorRef.expression}\n")
        }
        return b.toString()
    }
}
