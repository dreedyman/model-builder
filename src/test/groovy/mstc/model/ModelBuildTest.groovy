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

import mstc.model.build.OutputBuilder
import mstc.model.build.VarBuilder
import mstc.model.parser.ModelParser
import mstc.model.parts.Input
import mstc.model.parts.Output
import org.junit.Test

/**
 *
 * @author Dennis Reedy
 */
class ModelBuildTest {
    @Test
    public void testBuild() {
        VarBuilder inputBuilder = new VarBuilder<>();
        println inputBuilder
                      .var("areaV", "Double", "2.0*14530", "0.5*2.0*14350.0", "1.5*2.0*14350.0")
                      .addToList("inputVarList").build()
        inputBuilder.clear()
        println inputBuilder
                      .var("quoteV", "String", "The quick brown fox jumps over the lazy dog")
                      .addToList("inputVarList").build()
        inputBuilder.clear()
        println inputBuilder.var("lougleV", "URL", "http://google.com").addToList("outputVarList").build()

        inputBuilder.clear()
        println inputBuilder.var(new Input("tOverChordRootV", "Double", "8.0, 2.0, 20.0", "/input/value/why?")).addToList("outputVarList").build()

        VarBuilder outputBuilder = new VarBuilder()
        println outputBuilder.var(new Output("analysisOutputContext", "sorcer.service.Context", "/output/thing")).build()
    }

    @Test
    public void testOutput() {
        File model = new File("${System.getProperty("user.dir")}/src/test/myModel.groovy")
        ModelParser modelParser = new ModelParser()
        ModelConfiguration modelConfiguration = modelParser.parse(model)
        modelConfiguration.outputs.each { o ->
            OutputBuilder outputBuilder = new OutputBuilder()
            outputBuilder.output(o)
        }
    }
}
