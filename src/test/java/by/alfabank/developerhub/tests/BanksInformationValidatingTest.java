package by.alfabank.developerhub.tests;

import by.alfabank.developerhub.helpers.BasePaths;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


public class BanksInformationValidatingTest extends BaseTest {

    private SoftAssertions softAssertions;

    @Test
    public void banksTest() {
        RestAssured.given().
                param("type", "ru").
                when().
                get(BasePaths.BASE_URI + "banks").
                then().
                assertThat().
                statusCode(200);

        Response responseBanks = RestAssured.given().
                param("type", "ru").
                when().
                get(BasePaths.BASE_URI + "banks");
        softAssertions = new SoftAssertions();
        JsonPath jsonPathValidator = responseBanks.jsonPath();
        List<Object> listOfBic = jsonPathValidator.getList("banks.bic");
        for (Object i : listOfBic) {
            softAssertions.assertThat(i != null);
            softAssertions.assertAll();
        }
        List<Object> listOfNames = jsonPathValidator.getList("banks.name");
        for (Object j : listOfNames) {
            assertThat(j != null);
            softAssertions.assertAll();
        }
        List<Object> listOfAddresses = jsonPathValidator.getList("banks.address");
        for (Object k : listOfAddresses) {
            assertThat(k != null);
            softAssertions.assertAll();
        }
    }
}
