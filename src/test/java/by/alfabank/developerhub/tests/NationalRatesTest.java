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

public class NationalRatesTest extends BaseTest {

    @Test
    public void ratesDateRelevantDate() {

        RestAssured.given().
                param("currencyCode", "840").
                when().
                get(BasePaths.BASE_URI + "nationalRates").
                then().
                assertThat().
                statusCode(200).
                body("rates[0].date", equalTo(getNowDate.getNowDate()));

        Response responseNationalRates = RestAssured.given().
                when().
                get(BasePaths.BASE_URI + "nationalRates");
        JsonPath jsonPathValidator = responseNationalRates.jsonPath();
        List<Object> listOfDates = jsonPathValidator.getList("rates.date");
        for (Object i : listOfDates) {
            softAssertions.assertThat(getNowDate.getNowDate()==i);
            softAssertions.assertAll();
        }

        List<Object> listOfRates = jsonPathValidator.getList("rates.rate");
        for (Object i : listOfRates) {
            softAssertions.assertThat(i!=null);
            softAssertions.assertAll();
        }
    }
}
