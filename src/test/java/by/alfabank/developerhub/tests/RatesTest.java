package by.alfabank.developerhub.tests;

import by.alfabank.developerhub.helpers.BasePaths;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RatesTest {

    @Test
    public void ratesTest() {
        RestAssured.given().
                param("currencyCode", "840").
                when().
                get(BasePaths.BASE_URI + "rates").
                then().
                assertThat().
                statusCode(200);
        Response response = RestAssured.given().
                when().
                get(BasePaths.BASE_URI + "rates");
        JsonPath jsonPathValidator = response.jsonPath();

        List<Object> listOfSellRates = jsonPathValidator.getList("rates.sellRate");
        List<Object> listOfBuyRates = jsonPathValidator.getList("rates.buyRate");
        for (int s = 0; s < listOfBuyRates.size(); s++) {
            Object j = listOfBuyRates.get(s);
            Object i = listOfSellRates.get(s);
                Double jk = new Double(j.toString());
                Double ik = new Double(i.toString());
                Boolean a;
                if (jk > ik) {
                    a = true;
                } else {
                    a = false;
                }
                    Assert.assertTrue(a);
        }
    }
}
