package rahulshettyacademy;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups= {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.gotoCheckout();
        checkoutPage.SelectCountry("india");
        ConformationPage conformationPage = checkoutPage.SubmitOrder();


        String confirmMessage = conformationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApplication("monika.zaczek96@gmail.com", "Miki1996!");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));


    }
    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        //HashMap<String,String> map = new HashMap<String,String>();
        //map.put("email","monika.zaczek96@gmail.com");
       // map.put("password", "Miki1996!" );
       // map.put("product","ZARA COAT 3" );

       // HashMap<String,String> map1 = new HashMap<String,String>();
       // map1.put("email","monika.zaczek961@gmail.com");
       // map1.put("password", "Miki1996!" );
       // map1.put("product","ADIDAS ORIGINAL" );
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
        return new Object[] [] { {data.get(0)}, {data.get(1)} };


    }


}

