package mstc.model.parser

import mstc.model.ModelConfiguration
import mstc.model.parts.*

class ModelParser {

    ModelParser() {
        ExpandoMetaClass.enableGlobally()
    }

    ModelConfiguration parse(File modelFile) {
        ModelDelegate modelDelegate = new ModelDelegate()
        Script modelScript = new GroovyShell().parse(modelFile.text)
        modelScript.metaClass = createEMC(modelScript.class, { ExpandoMetaClass emc ->
            emc.using { Map options ->
                if(options.evaluators) {
                    String name = options.evaluators as String
                    if (!name.endsWith("groovy"))
                        name = "${name}.groovy"
                    File f = new File(modelFile.parentFile, name)
                    ModelConfiguration modelConfiguration = parseUsesFile(f)
                    modelDelegate.evaluators.addAll(modelConfiguration.evaluators)
                    println "===> ${modelDelegate.evaluators}"
                }
            }
            emc.model { Map attributes, Closure cl ->
                modelDelegate.name = attributes.name as String
                cl.delegate = modelDelegate
                cl.resolveStrategy = DELEGATE_FIRST
                cl()
            }

        })
        modelScript.run()
        for(Output output : modelDelegate.outputs) {
            for(EvaluatorInfo evaluatorInfo : output.computedBy) {
                def evaluator = getEvaluator(evaluatorInfo.name, modelDelegate)
                if(evaluator!=null) {
                    evaluatorInfo.evaluatorRef = evaluator
                    /*if(evaluator instanceof ExertionEvaluator) {
                        evaluatorInfo.deployArtifact = evaluator.deployArtifact
                        evaluatorInfo.interfaceClass = evaluator.interfaceClass
                    }
                    if(evaluator instanceof ExpressionEvaluator)
                        evaluatorInfo.expression = evaluator.expression*/
                }
            }
        }
        return new ModelConfiguration()
                .setEvaluators(modelDelegate.evaluators)
                .setInputs(modelDelegate.inputs)
                .setName(modelDelegate.name)
                .setOutputs(modelDelegate.outputs)
    }

    ModelConfiguration parseUsesFile(File uses) {
        ModelDelegate usesDelegate = new ModelDelegate()
        Script usesScript = new GroovyShell().parse(uses.text)
        usesScript.metaClass = createEMC(usesScript.class, { ExpandoMetaClass emc ->
            emc.evaluators { Closure cl ->
                cl.delegate = usesDelegate
                cl.resolveStrategy = DELEGATE_FIRST
                cl()
            }
        })
        usesScript.run()
        return new ModelConfiguration()
                .setEvaluators(usesDelegate.evaluators)
                .setInputs(usesDelegate.inputs)
    }

    private ExpandoMetaClass createEMC(Class clazz, Closure cl) {
        ExpandoMetaClass emc = new ExpandoMetaClass(clazz, false)
        cl(emc)
        emc.initialize()
        emc
    }

    private Evaluator getEvaluator(String name, ModelDelegate modelDelegate) {
        Evaluator evaluator  = null
        for(Evaluator e : modelDelegate.evaluators) {
            if(e.name==name) {
                evaluator = e
                break
            }
        }
        evaluator
    }

    static void main(String[] args) {
        new ModelParser().parse(new File("${System.getProperty("user.dir")}/src/test/myModel.groovy"))
    }
}