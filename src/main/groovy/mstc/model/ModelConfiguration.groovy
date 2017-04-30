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
package mstc.model

import mstc.model.parts.Evaluator
import mstc.model.parts.Input
import mstc.model.parts.Output

/**
 *
 * @author Dennis Reedy
 */
class ModelConfiguration {
    String name
    List<Evaluator> evaluators = []
    List<Output> outputs = []
    List<Input> inputs = []

    ModelConfiguration setName(String name) {
        this.name = name
        this
    }

    ModelConfiguration setEvaluators(evaluators) {
        this.evaluators = evaluators
        this
    }

    ModelConfiguration setOutputs(outputs) {
        this.outputs = outputs
        this
    }

    ModelConfiguration setInputs(inputs) {
        this.inputs = inputs
        this
    }

}
