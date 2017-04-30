/* Evaluator info file - should be generated */

evaluators {
    evaluator(name: "astros", type: "ExertionEvaluator") {
        providerName "Engineering-Astros"
        interfaceClass "mil.afrl.mstc.open.astros.AstrosRemoteInterface"
        deployArtifact "mil.afrl.mstc.open:astros-provider:config:3.1-develop"
        operations "executeAstros", "computeSpanLift", "computeFlutterDamping", "computeInducedDrag"
    }

    evaluator(name: "nastran", type: "ExertionEvaluator") {
        providerName "Engineering-Nastran"
        interfaceClass "mil.afrl.mstc.open.nastran.NastranRemoteInterface"
        deployArtifact "mil.afrl.mstc.open:nastran-provider:config:3.1-develop"
        operations "executeNastran", "executeNastranPunchParser"
    }

    evaluator(name: "mstc-geom", type: "ExertionEvaluator") {
        providerName "Engineering-MstcGeom"
        interfaceClass "mil.afrl.mstc.open.mstcgeom.MstcGeomRemoteInterface"
        deployArtifact "mil.afrl.mstc.open:mstcgeom-provider:config:LATEST"
        operations "doMstcGeomService"
    }
}