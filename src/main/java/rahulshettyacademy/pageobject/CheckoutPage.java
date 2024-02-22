package rahulshettyacademy.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(css = ".action__submit")
    private WebElement submit;
    @FindBy(css="[placeholder='Select Country']")
    private WebElement country;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
     private WebElement selectCountry;
     By results = By.cssSelector(".ta-results");

    public void SelectCountry(String CountryName){
        Actions a = new Actions(driver);
        a.sendKeys(country, CountryName).build().perform();
        waitForElementToAppear(By.cssSelector(".ta-results"));
        selectCountry.click();
    }
    public ConformationPage SubmitOrder(){
        submit.click();
        return new ConformationPage(driver);
    }

}
