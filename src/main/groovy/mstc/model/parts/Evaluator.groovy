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
class Evaluator {
    String name

    Evaluator(String name) {
        this.name = name
    }

    boolean equals(o) {
        if (this.is(o))
            return true
        if (!(o instanceof Evaluator))
            return false

        Evaluator evaluator = (Evaluator) o
        if (name != evaluator.name)
            return false
        return true
    }

    int hashCode() {
        return name.hashCode()
    }
}
