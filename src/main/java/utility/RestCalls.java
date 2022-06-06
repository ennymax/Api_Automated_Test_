package utility;

import Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestCalls extends TestBase {

    public static int RESPONSE_STATUS_CODE_200 = 200;
    public static String StatusLine_200 = "HTTP/1.1 200 OK";

    public static Response GET_200(String uRI) {
        return RestAssured.given().headers("Content-Type",
                ContentType.JSON).when().get(uRI);
    }
}
