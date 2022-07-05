package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class GittiGidiyor {
    //aktif ettigimiz pageFactory class'ini constructor icinde aktif edip Junit FindBy notasyonuyla locater'larimizi
    //test package'i icindeki test case'imizi execute ettigimiz class'ta GittiGidiyor sayfasinin objesini kullarak cagıracagiz
    public GittiGidiyor() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//input[@data-cy=\"header-search-input\"]")
    public WebElement textBox;
    @FindBy(xpath = "//*[@id=\"wis-l-212757\"]/div[1]/div/img")
    public WebElement close;
    @FindBy(xpath = "//span[contains(text(), 'Kapat')]")
    public WebElement cookies;
    @FindBy
    public WebElement getPage;
    @FindBy(xpath = "//*[@id=\"2\"]/li/article/div[2]/a/div/div[2]/header/hgroup/h2")
    public List<WebElement> allProductsSeconPage;
    @FindBy(xpath = "//*[@id=\"catalog-info-details\"]/div[2]/div/table/tbody/tr")
    public List<WebElement> productFields;
    @FindBy(xpath = "//button[@id=\"add-to-basket\"]")
    public WebElement addToChart;
    @FindBy(linkText = "Sepete Git")
    public WebElement goToChart;
    @FindBy(xpath = "//select[@class=\"amount\"]")
    public WebElement increaseProduct;
    @FindBy(xpath = "(//a[@title=\"Sil\"]/i)[1]")
    public WebElement removeProducts;
    @FindBy(xpath = "//*[@id=\"empty-cart-container\"]/div[1]/div[1]/div/div[2]/h2")
    public WebElement emptyBox;
    @FindBy(xpath = "//div[@class=\"text-box\"]/a/h2")
    public WebElement productFieldsInChart;
    public WebElement selectPage(int sayfa){
        getPage=Driver.getDriver()
                .findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div/div/div[2]/div/div[3]/div[4]/nav/ul/li["+(sayfa+1)+"]/a/span"));
        return getPage;
    }
}