package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenShoot {

    public static void takeShootAllScreen(){
        LocalDateTime date=LocalDateTime.now();
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("YYMMddHHmmss");
        String dates = date.format(dtf);
        TakesScreenshot ts= (TakesScreenshot) Driver.getDriver();
        File file=new File("target/AllScreenShoot/shoots"+dates+".jpeg");//goruntuyu kaydedecegimiz yer
        File temp=ts.getScreenshotAs(OutputType.FILE);//aldigimiz ekran goruntusu butun ekran
        try {
            FileUtils.copyFile(temp,file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void takeShootWebElement(WebElement we){
        LocalDateTime date=LocalDateTime.now();
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("YYMMddHHmmss");
        String dates = date.format(dtf);
        File file=new File("target/AllScreenShoot/shoots"+dates+".jpeg");//goruntuyu kaydedecegimiz yer
        File temp2=we.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(temp2,file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
