package rahulshettyacademy.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.*;

import java.io.IOException;
import java.util.List;

public class StepDefinitionimpl extends BaseTest {

    public rahulshettyacademy.pageobject.landingPage landingPage;
    public ProductCatalogue productCatalogue;
    public  ConformationPage conformationPage;

    @Given(" I landed on Ecommerce Page")
    public void  I_landed_on_Ecommerce_Page() throws IOException {
       landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_ussername_and_password(String username, String password){
        ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);

    }
    @When("^I add product (.*) to Cart$")
    public void i_add_product_to_cart(String ProductName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(ProductName);
    }
    @When("^Checkout (.*) and submit the order$")
    public void checkout_submit_order(String ProductName){
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay("ProductName");
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.gotoCheckout();
        checkoutPage.SelectCountry("india");
        conformationPage = checkoutPage.SubmitOrder();


    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_ConfirmationPage(String string){
        String confirmMessage = conformationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("string"));
        driver.close();

    }
    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {

        Assert.assertEquals(strArg1, landingPage.getErrorMessage());
        driver.close();
    }

}
