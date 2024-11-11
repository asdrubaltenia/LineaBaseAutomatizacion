package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.File;
import java.time.Duration;

public class FormularioPage extends WebBasePage {
    final WebDriver driver =  new ChromeDriver();

    public FormularioPage(WebDriver driver) {
        super(driver);
    }

//    @FindBy(xpath = "//*[@placeholder = 'Full Name']")
//    public WebElement inputFullName;

    public void bloquearPublicidadWeb() {
        String adBlockPath = "C:/Users/asdru/IdeaProjects/LineaBaseAutomatizacion/src/test/resources/Files/ublock.crx"; // Actualiza con la ruta correcta
        // Configura ChromeOptions para añadir la extensión
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(adBlockPath));
    }
    public void ingresarPaginaWeb() {
        WebDriverManager.chromedriver().setup();
        // Abre una página web
        driver.get("https://trytestingthis.netlify.app/");
        // Imprime el título de la página
        System.out.println("Título de la página: " + driver.getTitle());
    }

    public void sendKeysName(String text){
        WebElement inputFullName = driver.findElement(By.xpath("//input[@placeholder = 'Text' and @id = 'fname']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(inputFullName));
        inputFullName.click();
        inputFullName.sendKeys(text);
    }
}

