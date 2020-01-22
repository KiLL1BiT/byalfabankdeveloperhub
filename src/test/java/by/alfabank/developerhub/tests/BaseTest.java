package by.alfabank.developerhub.tests;

import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    SoftAssertions softAssertions;

    @Before
    public void setUp() {
        softAssertions = new SoftAssertions();
    }

    @After
    public void tearDown() {
        softAssertions.assertAll();
    }
}
