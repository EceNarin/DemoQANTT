package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pages.GittiGidiyor;
import testlogger.TestResultLogger;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.MyReusableMethods;

import java.util.Random;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)//
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(TestResultLogger.class)
public class AdvancedJunit_MultipleTestCases {
    GittiGidiyor gg=new GittiGidiyor();
    Actions action=new Actions(Driver.getDriver());
    JavascriptExecutor js=(JavascriptExecutor)Driver.getDriver();
    String randomProFields="";
    @Test
    @Order(1)
    public void navigateToWebSite_001(){
        //-  user navigate to www.gittigidiyor.com
        //confgreader properties'e key olarak yazdıgımız ggUrl yazısının value'una gider yani gittigdiyor.
        //configuration.properties file2ina bakabilirsiniz
        Driver.getDriver().get(ConfigReader.getProperty("ggUrl"));
        MyReusableMethods.waitFor(4);
        //user close photos
        if(gg.close.isDisplayed()){
            gg.close.click();
        }
        if(gg.cookies.isDisplayed()){
            gg.cookies.click();//user close to cookies
        }
        //- user input search text box "bilgisayar"
        //oop ile GittiGidiyor page sayfasindan olusturdugumuz "gg" objesi araciligiyla ilgili method webElementlist ve webelementleri cagiracagiz
        MyReusableMethods.waitFor(3);
    }
    @Test
    @Order(2)
    public void searchKeys_SelectPage_002(){
        gg.textBox.sendKeys("bilgisayar"+ Keys.ENTER);
        //- user navigate to 2. page from result of search page
        int sayfa=2;
        WebElement secondPage=gg.selectPage(sayfa);//gittiGidiyor class'indan cagirdigim reusable method alternatif icin olusturldu
        MyReusableMethods.waitFor(3); //3 sn bekletiyoruz( utilities icinde bulunan MyReusableMethods class'indan göz atabilirsiniz bu method'a
        secondPage.click();
        //- user verfiy to in second page
        action.sendKeys(Keys.PAGE_DOWN).perform();
        String actualSeconPage=secondPage.getText();
        //sayfamizim beklendigi 2. sayfada oldugunu kanitliyoruz
        Assertions.assertTrue(actualSeconPage.toLowerCase().contains("2"));
    }
    @Test
    @Order(3)
    public void clickRandomlyProduct_003(){
        //- user will select from one displayed product randomly
        Random rnd=new Random();
        //int turunden donducrecegimizin random'un bound'unu sayfada olan tum product sayisinin -1'i kadar donmesini saglayark cagirracagiz
        //-1 dedik cunku GittiGidiyor class'indan webElement list'e attigimiz butun product size'ini gecmesini istemiyoruz (boundException)
        int random=rnd.nextInt(gg.allProductsSeconPage.size()-1);
        WebElement randomProducts= gg.allProductsSeconPage.get(random);
        //67.Row'da buradaki string'in Assert'unu yapacagiz
        randomProFields=randomProducts.getText();//we will use this string for asserting after add this products fields in chart //67. row
        randomProducts.click();
    }

    @Test
    @Order(4)
    void printIntoTextProductFields_004() {
        //- user will print all which selected randomly products's datas in text.exe
        action.sendKeys(Keys.PAGE_DOWN).perform();
        if(gg.cookies.isDisplayed()){
            gg.cookies.click();//user close to cookies
        }
        //bekletmeler yazilmanin saglikli sekilde yapilmasi icin konmustur
        MyReusableMethods.waitFor(5);
        //reusable method araciligi ile icine attigimiz WeElemntList'inin txt'i icine yazilmasini sagliyoruz
        MyReusableMethods.writeToListINTOText(gg.productFields);
        MyReusableMethods.waitFor(3);
        //- user add to chart which selected random products
        js.executeScript("arguments[0].click()",gg.addToChart);
        //- user verify selected products datas's with which are added to chart products datas
        MyReusableMethods.waitFor(3);
        gg.goToChart.click();
        String actualProduct=gg.productFieldsInChart.getText();
        Assertions.assertTrue(actualProduct.toLowerCase().contains(randomProFields.toLowerCase()));
    }

    @Test
    @Order(5)
    public void IncreaseProductNum_RemoveProduct_005(){
        //- user increase numbers to 2, selected products which are in added to chart and verify them
        Select select=new Select(gg.increaseProduct);
        select.selectByVisibleText("2");
        Assertions.assertTrue(select.getFirstSelectedOption().getText().equals("2"));
        //- user will remove all products from chart
        gg.removeProducts.click();
        //and verify chart is empty
        MyReusableMethods.waitFor(3);
        String actualWord=gg.emptyBox.getText();
        String expectedWord="Sepetinizde ürün bulunmamaktadır.";
        MyReusableMethods.waitFor(3);
        Assertions.assertTrue(actualWord.equalsIgnoreCase(expectedWord));
    }
}
