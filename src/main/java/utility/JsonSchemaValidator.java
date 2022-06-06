package utility;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class JsonSchemaValidator {

    @Test
    public static void JsonSchema(String url, String FilePath) {
        File schema = new File(FilePath);
        given().get(url).then().body(matchesJsonSchema(schema));
        System.out.println("************************************Schema Test Completed ****************************************\n");
    }
}