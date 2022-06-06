package Collection;

import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.populationResponsepojo;
import utility.ExcelReader;
import java.io.IOException;
import static org.junit.Assert.*;
import static utility.RestCalls.GET_200;
import static utility.Utility.isNumeric;

public class population_Test extends TestBase {

    @DataProvider(name = "data-set")
    public static Object[][] DataSet() throws Exception {
        ExcelReader excel = new ExcelReader();
        excel.setExcelFile("./Config/testData.xlsx", "Years");
        Object[][] obj = excel.to2DArray();
        return obj;
    }

    @Test(dataProvider = "data-set", priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Assert data is returned for a specific date range 2012-2020")
    public void TC_001(String Year) throws InterruptedException, IOException {

        Response response = GET_200("/api/data?drilldowns=Nation&measures=Population&year=" + Year);
        response.prettyPeek().then().spec(responseSpec_200);

        populationResponsepojo d = response.getBody().as(populationResponsepojo.class);

        assertFalse("No data was returned for the year " + Year,d.getData().isEmpty());
        assertEquals(d.getData().get(0).getIDYear().toString(), d.getData().get(0).getYear());
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Assert data is returned for a year 2013")
    public void TC_002() throws InterruptedException, IOException {
        Response response = GET_200("/api/data?drilldowns=Nation&measures=Population&year=2013");
        response.prettyPeek().then().spec(responseSpec_200);

        populationResponsepojo d = response.getBody().as(populationResponsepojo.class);

        assertEquals("No Data was returned for ID Nation for 2013", d.getData().get(0).getIDNation(), "01000US");
        assertEquals("No Data was returned for Nation for 2013", d.getData().get(0).getNation(), "United States");
        assertEquals("No Data was returned for Slug Nation for 2013", d.getData().get(0).getSlugNation(), "united-states");
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Assert that ID Nation, Nation and Slug Nation returns value")
    public void TC_003() throws InterruptedException, IOException {
        Response response = GET_200("/api/data?drilldowns=Nation&measures=Population&year=2013");
        response.prettyPeek().then().spec(responseSpec_200);

        populationResponsepojo d = response.getBody().as(populationResponsepojo.class);

        assertTrue("Population is not a number", isNumeric(d.getData().get(0).getPopulation().toString()));
    }
}
