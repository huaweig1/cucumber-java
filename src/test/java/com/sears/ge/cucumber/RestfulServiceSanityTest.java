package com.sears.ge.cucumber;

import com.sears.ge.rest.util.HttpHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Q
 */
public class RestfulServiceSanityTest {

    private static Logger logger = LoggerFactory.getLogger(RestfulServiceSanityTest.class);
    private String response;

    @Given("^I have a restful client$")
    public void i_have_a_restful_client() throws Throwable {
        HttpHelper.setBaseUrl("http://www.google.com");
    }

    @When("^I connect google\\.com$")
    public void i_connect_google_com() throws Throwable {
        response = HttpHelper.invokeGet("");
    }

    @Then("^return (\\d+)OK$")
    public void return_OK(int status) throws Throwable {
        logger.info("GET method response: " + response);
        Assert.assertTrue(response.contains("Google Search"));
        Assert.assertTrue(response.contains("I'm Feeling Lucky"));
    }
}
