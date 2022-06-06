package Base;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;
import static utility.RestCalls.*;
import static utility.Utility.fetchvalue;

public class TestBase {

    public static ResponseSpecification responseSpec_200;
    public static ResponseSpecification responseSpec_400;
    public static ResponseSpecification responseSpec_401;

    @BeforeMethod(alwaysRun = true)
    protected void setUpConfiguration() {
        RestAssured.baseURI = fetchvalue("BaseURL");

        responseSpec_200 = new ResponseSpecBuilder().
                expectStatusCode(RESPONSE_STATUS_CODE_200).
                expectContentType(ContentType.JSON).expectStatusLine(StatusLine_200)
                .expectResponseTime(lessThan(15L), TimeUnit.SECONDS).
                build();

        responseSpec_401 = new ResponseSpecBuilder().
                expectStatusCode(401).
                build();
    }

    @BeforeMethod(alwaysRun = true)
    protected void Waiter() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
    }
}