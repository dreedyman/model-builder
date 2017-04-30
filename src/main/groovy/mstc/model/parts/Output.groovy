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
class Output {
    String type
    String name
    List<EvaluatorInfo> computedBy= []
    String path
    String selectedFidelity

    Output(String name, String type, String path) {
        this.name = name
        this.type = type
        this.path = path
    }

    String getSelectedFidelity() {
        if(selectedFidelity==null)
            selectedFidelity = computedBy.get(0).fidelity
        selectedFidelity
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder()
        b.append("Output: ${name}, type: ${type}, path: ${path}\n")
        b.append("\t\tcomputedBy:\n")
        for(EvaluatorInfo p : computedBy) {
            b.append("\t${p.toString()}")
        }
        b.append("\t\tselectedFidelity: ${getSelectedFidelity()}\n")
        return b.toString()
    }
}
