package com.sears.ge.cucumber;

import com.sears.ge.dto.Car;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

/**
 *
 * @author Q
 */
public class CarTest {

    private Car car;

    @Given("^I have a car$")
    public void i_have_a_car() throws Throwable {
        car = new Car();
    }

    @When("^I add (\\d+) wheels$")
    public void i_add_wheels(int wheels) throws Throwable {
        car.setWheels(wheels);
    }

    @Then("^It can drive$")
    public void it_can_drive() throws Throwable {
        Assert.assertTrue(car.canDrive());
    }
}
