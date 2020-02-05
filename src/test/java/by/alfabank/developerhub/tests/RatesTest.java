package by.alfabank.developerhub.tests;

import by.alfabank.developerhub.helpers.BasePaths;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RatesTest extends BaseTest{

    @Test
    public void ratesTest() {
        RestAssured.given().
                param("currencyCode", "840").
                when().
                get(BasePaths.BASE_URI + "rates").
                then().
                assertThat().
                statusCode(200);
        Response responseRates = RestAssured.given().
                when().
                get(BasePaths.BASE_URI + "rates");
        JsonPath jsonPathValidator = responseRates.jsonPath();

        List<Object> listOfSellRates = jsonPathValidator.getList("rates.sellRate");
        List<Object> listOfBuyRates = jsonPathValidator.getList("rates.buyRate");
        for (int s = 0; s < listOfBuyRates.size(); s++) {
            Object buyRates = listOfBuyRates.get(s);
            Object sellRates = listOfSellRates.get(s);
                Double buyRatesTypeDouble = new Double(buyRates.toString());
                Double sellRatesTypeDouble = new Double(sellRates.toString());
                Boolean ratesBalanceTrue;
                if (buyRatesTypeDouble > sellRatesTypeDouble) {
                    ratesBalanceTrue = true;
                } else {
                    ratesBalanceTrue = false;
                }
                    Assert.assertTrue(ratesBalanceTrue);
        }
    }
}
