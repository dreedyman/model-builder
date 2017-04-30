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

import mstc.model.parts.Input
import mstc.model.parts.Output

/**
 *
 * @author Dennis Reedy
 */
class VarBuilder extends BaseBuilder {
    String name

    VarBuilder var(Input input) {
        def values = []
        if("," in input.value) {
            def parts = input.value.split(",")
            parts.each { part ->
                parts << part.trim()
            }
        } else {
            values << input.value
        }
        var(input.name, input.type, values as String[])
    }

    VarBuilder var(Output output) {
        var(output.name, output.type)
    }

    VarBuilder var(String name, String type, String... values) {
        this.name = name
        StringBuilder b = new StringBuilder()
        b.append("Var<${type}> $name = new Var<>(\"${name}\"")
        if (values.length > 0) {
            b.append(",")
            boolean quoteValues = type.equals("String") ||
                                  type.equals(String.class.name) ||
                                  type=="URL" ||
                                  type==URL.class.name

            for (int i = 0; i < values.length; i++) {
                String v = quoteValues ? "\"${values[i]}\"" : values[i]
                b.append(v)
                if (values.length - i > 1)
                    b.append(", ")
            }
        }
        b.append(");\n")
        content.append(b.toString())
        this
    }

    VarBuilder addToList(String listName) {
        content.append("${listName}.add($name);\n")
        this
    }
}
