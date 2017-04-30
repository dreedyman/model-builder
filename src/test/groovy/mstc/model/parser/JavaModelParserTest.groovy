package mstc.model.parser

import org.junit.Test
/**
 * @author Dennis Reedy
 */
class JavaModelParserTest {
    @Test
    public void parse() throws Exception {
        File f = new File("${System.getProperty("user.dir")}/../mstc-engineering-open/products/AstrosNastranMultifidelityModel-mdl/src/main/java/mil/afrl/mstc/open/astrosnastranmultifidelitymodel/AstrosNastranMultifidelityModel2.java")
        JavaModelParser modelParser = new JavaModelParser(f)
        modelParser.parse();
    }

}