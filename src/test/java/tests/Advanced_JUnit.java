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

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)//
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(TestResultLogger.class)
public class Advanced_JUnit  {
    DemoQa dmq=new DemoQa();
    Actions action=new Actions(Driver.getDriver());
    JavascriptExecutor js=(JavascriptExecutor)Driver.getDriver();

    @Test
    @Order(1)
    public void gittiGidiyor_001() {

    }

}