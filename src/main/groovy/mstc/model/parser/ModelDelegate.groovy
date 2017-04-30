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
package mstc.model.parser

import mstc.model.parts.*
/**
 *
 * @author Dennis Reedy
 */
@SuppressWarnings("GroovyUnusedDeclaration")
class ModelDelegate {
    String name
    def evaluators = []
    def outputs = []
    def inputs = []
    def currentOutput
    def currentEvaluator
    def currentEvaluatorInfo

    def output(Map attributes, Closure cl) {
        currentOutput = new Output(attributes.name as String, attributes.type as String, attributes.path as String)
        outputs << currentOutput
        cl()
    }

    def computedBy(Map attributes) {
        currentEvaluatorInfo = new EvaluatorInfo(attributes.evaluator as String)
        currentOutput.computedBy << currentEvaluatorInfo
    }

    def computedBy(Map attributes, Closure cl) {
        currentEvaluatorInfo = new EvaluatorInfo(attributes.evaluator as String)
        currentOutput.computedBy << currentEvaluatorInfo
        cl()
    }

    def evaluator(Map attributes) {
        String ref = attributes.ref as String
        String name = attributes.as as String
        evaluators.each { e ->
            if (e.name == ref) {
                e.name = name
                println "Set ${ref} to ${name}"
            }
        }
    }

    def evaluator(Map attributes, Closure cl) {
        String type =  attributes.type
        if(type.startsWith("Exertion")) {
            currentEvaluator = new ExertionEvaluator(attributes.name as String)
        } else {
            currentEvaluator = new ExpressionEvaluator(attributes.name as String)
        }
        evaluators << currentEvaluator
        cl()
    }

    def input(Map attributes) {
        String value = attributes.value?attributes.value:attributes.values
        inputs << new Input(attributes.name as String,
                            attributes.type as String,
                            value,
                            attributes.path as String)
    }

    def inputs(String... inputs) {
        inputs.each { input ->
            currentEvaluatorInfo.inputs << input
        }
    }

    def providerName(String providerName) {
        currentEvaluator.providerName = providerName
    }

    def interfaceClass(String interfaceClass) {
        currentEvaluator.interfaceClass = interfaceClass
    }

    def deployArtifact(String deployArtifact) {
        currentEvaluator.deployArtifact = deployArtifact
    }

    def operation(String operation) {
        currentEvaluatorInfo.operation = operation
    }

    def operations(String... ops) {
        currentEvaluator.operations.addAll(ops)
    }

    def expression(String expression) {
        currentEvaluator.expression = expression
    }

    def usingSpace(boolean usingSpace) {
        currentEvaluatorInfo.usingSpace = usingSpace
    }

    def fidelity(String fidelity) {
        currentEvaluatorInfo.fidelity = fidelity
    }

    def provisioned(boolean provisioned) {
        currentEvaluatorInfo.provisioned = provisioned
    }

    def selectedFidelity(String selectedFidelity) {
        currentOutput.selectedFidelity = selectedFidelity
    }
}
