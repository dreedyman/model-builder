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

import com.github.javaparser.JavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.ObjectCreationExpr
import com.github.javaparser.ast.stmt.ExpressionStmt
import com.github.javaparser.ast.visitor.VoidVisitorAdapter

/**
 *
 * @author Dennis Reedy
 */
class JavaModelParser {
    File javaSource

    JavaModelParser(File javaSource) {
        this.javaSource = javaSource
    }

    def parse() {
        FileInputStream fin = new FileInputStream(javaSource)
        CompilationUnit cu = JavaParser.parse(fin);
        new MyVisitor().visit(cu, null)

    }

    class MyVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(ObjectCreationExpr n, Object arg) {
            //System.out.println(n.toString())
            super.visit(n, arg);
        }

        @Override
        void visit(AssignExpr n, Object arg) {
            System.out.println(n.toString())
            super.visit(n, arg)
        }

        @Override
        void visit(ExpressionStmt n, Object arg) {
            /*if(n.comment==null)
                System.out.println(n.toString())*/
            super.visit(n, arg)
        }

        @Override
        public void visit(MethodCallExpr n, Object arg) {
            //System.out.println(n.toString())
            super.visit(n, arg);
        }
    }
}
