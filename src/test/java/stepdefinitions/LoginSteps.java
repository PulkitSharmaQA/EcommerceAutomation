package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    @Given("User launches the application")
    public void user_launches_application() {

        System.out.println("Application launched");
    }

    @When("User enters valid email and password")
    public void user_enters_valid_email_and_password() {

        System.out.println("Entered credentials");
    }

    @Then("User should login successfully")
    public void user_should_login_successfully() {

        System.out.println("Login successful");
    }
}