package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pages.DemoQa;
import testlogger.TestResultLogger;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.MyReusableMethods;
import utilities.ScreenShoot;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)//
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(TestResultLogger.class)
public class AdvancedJunit_MultipleTestCases {
    DemoQa dmq=new DemoQa();
    Actions action=new Actions(Driver.getDriver());
    JavascriptExecutor executor=(JavascriptExecutor)Driver.getDriver();

    @Test
    @Order(1)
    public void navigateToWebSite_001(){
        //user navigate to Demoga.com
        Driver.getDriver().get(ConfigReader.getProperty("demoqaURL"));
        //user verify to mainPage
        String actual=Driver.getDriver().getTitle();
        String expected="ToolsQA";
        Assertions.assertEquals(expected,actual);
    }
    @Test
    @Order(2)
    public void click_practiceForm_002(){
        //user click form link
        dmq.form.click();
        //user click on top menu "pratice form"
        dmq.practiceForm.click();
    }
    @Test
    @Order(3)
    public void clickRandomlyProduct_003(){
        //user take from csv file name fields and input fistname textbox
       String str= MyReusableMethods.csvReader(1,0);
       dmq.firstname.sendKeys(str);
       //user take from csv file lastname and input lastname into textbox
       dmq.lastName.sendKeys(MyReusableMethods.csvReader(1,1));
       // user take from csv file email and input email into textbox
        dmq.userMail.sendKeys(MyReusableMethods.csvReader(1,2));
        //we will select ramdomly gender radio button
        int size=dmq.selectGender.size();
        Random rnd=new Random();
        int random= rnd.nextInt(size);//we are determine to random numbers bounds genders radiobuttons size
        //user select gender radio button randomly
        dmq.selectGender.get(random).click();
        //user take from csv file phonenumber and input phonenumber into textbox
        dmq.phoneNumber.sendKeys(MyReusableMethods.csvReader(1,3));
        //fill the BOD
            //1. click bod webelement
        dmq.dateOfBirth.click();
        WebElement ddmMonth = dmq.selectMonth;
        Select selectMonth = new Select(ddmMonth);

        selectMonth.selectByVisibleText("July"); // CSV dosyasından alınacak doğum tarihi split edilerek.

        WebElement ddmYear = dmq.selectYear;
        Select selectYear = new Select(ddmYear);

        selectYear.selectByValue("1992"); // CSV dosyasından alınacak doğum tarihi split edilerek.

        List<WebElement> days = dmq.listDays;

        System.out.println(days.get(1).getText());

        int add = 0;

        for (int i = 0; i < days.size(); i++) {
            if (days.get(i).getText().equals("1")) {
                add = i-1;
                break;
            }
        }
        int day = 23; // CSV dosyasından alınacak doğum tarihi split edilerek.

        days.get(day+add).click();
    }


        @Test
        @Order(4)
        public void fillBlanks_ () {

            MyReusableMethods.waitFor(4);
            //user selects min 2 click button as hobbies
            for(int i=0;i<dmq.hobbies.size();i++){
                if(dmq.hobbies.get(i).isSelected()){
                    continue;
                }else {
                    dmq.hobbies.get(i).click();
                }
            }

        }

    @Test
    @Order(5)
    void posttImageFileViaRobot_004() throws AWTException {
        // User clicks "Dosya Seçin" button
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        js.executeScript("window.scrollBy(0,200)");
        //robot ve javaScript kodlari olasi windows'a gecisi onlemek adina. risksizdir
        dmq.uploadFile.click();
        MyReusableMethods.waitFor(3);
        Robot rb = new Robot();
        StringSelection str = new StringSelection("\"C:\\Users\\himer\\OneDrive\\Masaüstü\\hasortman.jpg\"");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        //user take from csv file "Current adress "and input Current adress  into textbox
        dmq.currentAdress.sendKeys(MyReusableMethods.csvReader(1,5));
        //user select state
        //burada dev tools'ta olan select class'iyla dropdown menusu select tagıyla yapılmadıgı icin secim yapamıyoruz
        //
        action.sendKeys(Keys.PAGE_DOWN).perform();
        //
        executor.executeScript("arguments[0].click();", dmq.selectState);
    }
    @Test
    @Order(6)
    public void submit_Screenshot_006 () {

        //user click to submit button
        MyReusableMethods.waitFor(2);
        WebElement submit= dmq.submit;
        executor.executeScript("arguments[0].click();", submit);
        //user wait for 5 sec to POP_UP
        MyReusableMethods.waitForPageToLoad(5);
        //user verify to see "Thanks for submitting the form"
        String actualText=dmq.submitVerifyText.getText();
        String expectedText="Thanks for submitting the form";
        Assertions.assertEquals(expectedText,actualText);
        //take pop_up screenShot
        // screnShot dosyasi path'i target/AllScreenShoot  target klasoru altinda
        ScreenShoot.takeShootWebElement(dmq.popUp);
        //close the popUp
        dmq.closePopUp.click();
    }

}
