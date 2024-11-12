package Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

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
        driver.manage().window().maximize();
        // Abre una página web
        driver.get("https://trytestingthis.netlify.app/");
        // Imprime el título de la página
        System.out.println("Título de la página: " + driver.getTitle());
    }

    public void sendKeysName(String text){
        WebElement inputName = driver.findElement(By.xpath("//input[@placeholder = 'Text' and @id = 'fname']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(inputName));
        inputName.click();
        inputName.sendKeys(text);
    }
    public void sendKeysApellido(String text){
        WebElement inputApellido = driver.findElement(By.xpath("//input[@placeholder = 'Text' and @id = 'lname']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(inputApellido));
        inputApellido.click();
        inputApellido.sendKeys(text);
    }
    public String getValueNombre() {
        WebElement inputName = driver.findElement(By.xpath("//input[@placeholder = 'Text' and @id = 'fname']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(inputName));
        return inputName.getAttribute("value"); // Usar "value" en lugar de getText()
    }
    public String getValueApellido() {
        WebElement inputApellido = driver.findElement(By.xpath("//input[@placeholder = 'Text' and @id = 'lname']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(inputApellido));
        return inputApellido.getAttribute("value"); // Usar "value" en lugar de getText()
    }
}

