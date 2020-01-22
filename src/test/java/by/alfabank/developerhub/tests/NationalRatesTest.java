package by.alfabank.developerhub.tests;

import by.alfabank.developerhub.helpers.BasePaths;
import by.alfabank.developerhub.helpers.getNowDate;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class NationalRatesTest {

    @Test
    public void ratesDateRelevant() {

        RestAssured.given().
                param("currencyCode", "840").
                when().
                get(BasePaths.BASE_URI + "nationalRates").
                then().
                assertThat().
                statusCode(200).
                body("rates[0].date", equalTo(getNowDate.getNowDate()));


        Response res = RestAssured.given().
                when().
                get(BasePaths.BASE_URI + "nationalRates");
        JsonPath jsonPathValidator = res.jsonPath();
        List<Object> listOfDates = jsonPathValidator.getList("rates.date");
        for (Object i : listOfDates) {
            Assert.assertEquals(getNowDate.getNowDate(), i);
        }
        List<Object> listOfRates = jsonPathValidator.getList("rates.rate");
        for (Object i : listOfRates) {
            Assert.assertNotNull(i);
        }
    }
}
