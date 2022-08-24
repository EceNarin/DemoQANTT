package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class DemoQa {
    //aktif ettigimiz pageFactory class'ini constructor icinde aktif edip Junit FindBy notasyonuyla locater'larimizi
    //test package'i icindeki test case'imizi execute ettigimiz class'ta GittiGidiyor sayfasinin objesini kullarak cagıracagiz
    public DemoQa() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "//div[@class=\"category-cards\"]/div[2]")
    public WebElement form;
    @FindBy(xpath = "//div[@class=\"accordion\"]/div[2]//li")
    public WebElement practiceForm;
    @FindBy(xpath = "//input[@id=\"firstName\"]")
    public WebElement firstname;
    @FindBy(xpath = "//input[@id=\"userEmail\"]")
    public WebElement userMail;
    @FindBy(xpath = " //*[@id=\"genterWrapper\"]/div[2]/div")
    public List<WebElement> selectGender;
    @FindBy(xpath = "//input[@id=\"lastName\"]")
    public WebElement lastName;
    @FindBy
    public WebElement elements;
    @FindBy(xpath = "//input[@id=\"userNumber\"]")
    public WebElement phoneNumber;
    @FindBy(xpath = "//input[@id=\"dateOfBirthInput\"]")
    public WebElement dateOfBirth;
    @FindBy(xpath = "//input[@id=\"subjectsInput\"]")
    public WebElement subjects;
    @FindBy(xpath = "//*[@id=\"hobbiesWrapper\"]/div[2]/div")
    public List<WebElement> hobbies;

    @FindBy(xpath = "//label[@for=\"uploadPicture\"]")
    public WebElement uploadFile;

    @FindBy (xpath = "//input[@id=\"dateOfBirthInput\"]")
    public WebElement inputDob;

    @FindBy (xpath = "//select[@class=\"react-datepicker__month-select\"]")
    public WebElement selectMonth;

    @FindBy (xpath = "//select[@class=\"react-datepicker__year-select\"]")
    public WebElement selectYear;

    @FindBy (xpath = "//*[@id=\"dateOfBirth\"]//div[@role=\"option\"]")
    public List<WebElement> listDays;
    @FindBy(xpath = "//textarea[@id=\"currentAddress\"]")
    public WebElement currentAdress;
    @FindBy(xpath = "(//div[@class=\" css-tlfecz-indicatorContainer\"])[1]")
    public WebElement selectState;

    @FindBy(xpath = "//input[@id=\"react-select-4-input\"]")
    public WebElement selectCity;

    @FindBy(xpath = "//button[@id=\"submit\"]")
    public WebElement submit;

    @FindBy(xpath = "//div[@id=\"example-modal-sizes-title-lg\"]")
    public WebElement submitVerifyText;
    @FindBy(xpath = "//div[@class=\"modal-content\"]")
    public WebElement popUp;

    @FindBy(xpath = "//button[@id=\"closeLargeModal\"]")
    public WebElement closePopUp;



    public WebElement getFromFields(List<WebElement> list,String str){
        for(int i=0;i< list.size();i++){
            if(list.get(i).getText().equalsIgnoreCase("str")){
              elements=  list.get(i);
            }
        }
        return elements;
    }
}