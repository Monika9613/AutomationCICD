package rahulshettyacademy.pageobject;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {
    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
            String productName = "ZARA COAT 3";
            landingPage.loginApplication("monika.zaczek96@gmail.com", "ki1996!");
            Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());


        }

        @Test
         public void ProductErrorValidation() throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("monika.zaczek96@gmail.com", "Miki1996!");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay("ZARA COAT33");
        Assert.assertFalse(match);
    }




    }

