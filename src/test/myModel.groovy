
evaluatorOps = ["AstrosStructuralAnalysis" : "executeAstros",
                "NastranStructuralAnalysis": "executeNastran"]

evaluatorInputs = ["AstrosStructuralAnalysis"      : "structuralAnalysisInput",
                   "NastranStructuralAnalysis"     : "structuralAnalysisInput",
                   "BeamEquationStructuralAnalysis": "xSpan"]

using(evaluators: "evaluatorInfo.groovy")

model(name: "Multi-Fidelity Structural Analysis Model") {

    input(name: "xSpan", value: "145", type: "Double", path: "/in/xSpan")
    input(name: "camberLocWingV", values: "8.0, 2.0, 23.0", type: "Double", path: "/in/xSpan")

    output(name: "structuralAnalysisInput", type: "URL", path: "/out/1") {
        computedBy(evaluator: "structuralAnalysisInputEvaluator") {
            operation "doMstcGeomService"
            inputs "xSpan"
        }
    }

    output(name: "analysisOutputContext", type: "sorcer.service.Context", path: "/out/2") {
        ["AstrosStructuralAnalysis", "NastranStructuralAnalysis"].each { e ->
            computedBy(evaluator: e) {
                operation evaluatorOps.get(e)
                fidelity "${e}Fidelity"
                inputs evaluatorInputs.get(e)
            }
        }
        selectedFidelity "AstrosStructuralAnalysis"
    }

    output(name: "tipz_displacement", type: "Double", path: "/out/3") {
        ["AstrosStructuralAnalysis", "NastranStructuralAnalysis"].each { e ->
            computedBy(evaluator: e) {
                operation evaluatorOps.get(e)
                fidelity "${e}Fidelity"
                inputs evaluatorInputs.get(e)
            }
        }
    }

    evaluator(name: "BeamEquationStructuralAnalysis", type: "ExpressionEvaluator") {
        expression "1.8*(x_span*x_span*x_span)/1.0E5"
    }
    evaluator(ref: "astros",    as: "AstrosStructuralAnalysis")
    evaluator(ref: "nastran",   as: "NastranStructuralAnalysis")
    evaluator(ref: "mstc-geom", as: "structuralAnalysisInputEvaluator")
}
