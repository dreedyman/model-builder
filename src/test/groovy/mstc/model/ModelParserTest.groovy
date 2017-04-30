package mstc.model

import mstc.model.parser.ModelParser
import org.junit.Test

class ModelParserTest {
    @Test
    public void testParse() {
        File model = new File("${System.getProperty("user.dir")}/src/test/myModel.groovy")
        ModelParser modelParser = new ModelParser()
        ModelConfiguration modelConfiguration = modelParser.parse(model)
        println modelConfiguration.name
        modelConfiguration.inputs.each { input ->
            println "\t$input"
        }

        println ""
        modelConfiguration.outputs.each { output ->
            println "\t$output"
        }

        modelConfiguration.evaluators.each { eval ->
            println "\t$eval"
        }
    }
}