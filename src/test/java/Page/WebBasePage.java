package Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.openqa.selenium.By.tagName;
public class WebBasePage {

    final WebDriver driver;
    private final WebDriverWait wait;

   // private String sistemaOperativo = System.getProperty("browserstack.os").toLowerCase();

    public WebBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    public WebBasePage(WebDriver driver, int timeOutSec) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    public WebBasePage(WebDriver driver, int timeOutSec, int pollingSec) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToDisappear(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitForTextToDisappear(WebElement element, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, text)));
    }

    protected boolean isVisible(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isVisible(By MobileElement) {
        try {
            return getDriver().findElement(MobileElement).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    protected boolean isInvisible(WebElement element) {
        try {
            return !element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isInvisible(By MobileElement) {
        try {
            return !getDriver().findElement(MobileElement).isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled(WebElement webElement) {
        try {
            return webElement.isEnabled();
        } catch (Exception e){
            return false;
        }
    }

    protected void waitFor(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException ignored) {

        }
    }

    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public boolean buscarMensajeNonThrow(String mensaje) {
        By selector = By.xpath("//*[contains(.,'" + mensaje + "')]");
        waitUntilElementIsVisibleNonThrow(selector, 40);
        WebElement element = getDriver().findElement(selector);
        return isVisible(element);
    }

    private void waitUntilElementIsVisibleNonThrow(By selector, int i) {
    }

    public boolean buscarMensajeErrorNonThrow(String mensajeError) {
        By selector = By.xpath("//*[contains(@class,'mat-error ng-tns-c84-11 ng-star-inserted')and contains(text(),'"+mensajeError+"')]");
        waitUntilElementIsVisibleNonThrow(selector, 10);
        WebElement element = getDriver().findElement(selector);
        return isVisible(element);

    }
        public void ClearBrowserCache() {
            driver.manage().deleteAllCookies();
        }

    public void scrollUp() {
        scrollUp(2);
    }

    public void scrollUp(int times) {
        try {
            for (int i = 0; i < times; i++) {
                ((JavascriptExecutor) driver).executeScript("scroll(0,-500)");
            }
        } catch (Exception ignored) {

        }
    }

    public void scrollDown() {
        Dimension size = this.getDriver().manage().window().getSize();
        int startPoint = (int)((double)size.getHeight() * 0.7D);
        int endPoint = (int)((double)size.getHeight() * 0.4D);
        ((JavascriptExecutor) driver).executeScript("scroll("+startPoint+","+endPoint+")");
    }

    public void scrollDown2() {
        Dimension size = this.getDriver().manage().window().getSize();
        int startPoint = (int)((double)size.getHeight() * 0.11D);
        int endPoint = (int)((double)size.getHeight() * 0.7D);
        ((JavascriptExecutor) driver).executeScript("scroll("+startPoint+","+endPoint+")");
    }

    public boolean estaDescargadoInformeEnBrowserStack() {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        boolean existFile = (boolean) jse.executeScript("browserstack_executor: {\"action\": \"fileExists\"}");
        waitFor(5);
        return existFile;
    }

    public boolean estaDescargadoInformePDFEnBrowserStack(String nombreArchivo) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        return (boolean) (Boolean) jse.executeScript("browserstack_executor: {\"action\": \"fileExists\", " +
                "\"arguments\": {\"fileName\":\"" + nombreArchivo + ".pdf\"}}");
    }

    public void scrollDownToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollDownToElementResponsive(WebElement element) {
        try {
            int x = element.getLocation().getX();
            int y = element.getLocation().getY();
            ((JavascriptExecutor)driver).executeScript("window.scroll("+x+","+y+")");
        } catch (Exception ignored){}
    }

    public void scrollDownBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public WebElement getShadowRoot(WebElement root) {
        JavascriptExecutor js =(JavascriptExecutor) driver;
        WebElement shadowRoot =
                (WebElement) js.executeScript("return arguments[0].shadowRoot", root);
        return shadowRoot.findElement(tagName("embed"));
    }

    public void switchNewWindow() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void moveToElement(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }


    public boolean isClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTextJS(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return (String) js.executeScript("return arguments[0].value;", element);
    }

    public boolean buscarMensajeAdvertenciaNonThrow(String mensajeAdvertencia) {
        By selector = By.xpath("//*[contains(@class,'warning') " +
                "or contains(@class,'sweet-alert')]//*[contains(text(),'" + mensajeAdvertencia + "')]");
        waitUntilElementIsVisibleNonThrow(selector,20);
        return isVisible(selector);
    }

    public boolean estaDescargadoArchivoNombreEnBrowserStack(String nombreArchivo) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        boolean existFile = (Boolean) jse.executeScript("browserstack_executor: {\"action\": \"fileExists\", " +
                "\"arguments\": {\"fileName\":\"" + nombreArchivo + "\"}}");
        waitFor(3);
        return existFile;
    }

    public boolean estaDescargadoInformeExcelEnBrowserStack(String nombreArchivo) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        return (boolean) (Boolean) jse.executeScript("browserstack_executor: {\"action\": \"fileExists\", " +
                "\"arguments\": {\"fileName\":\"" + nombreArchivo + ".pdf\"}}");
    }

    public void subirArchivoManual(String archivo, WebElement elemento){
        JavascriptExecutor JavascriptExecutor = ((JavascriptExecutor)getDriver());
        JavascriptExecutor.executeScript("document.evaluate('//*[@id=\"body-portal\"]/label', " +
                "document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;");
        ((RemoteWebDriver) getDriver()).setFileDetector(new LocalFileDetector());
        Path root = Paths.get(".").normalize().toAbsolutePath();
        waitUntilElementIsVisibleNonThrow((By) elemento, 10);
        String filePath = root+"/src/test/resources/Files/"+archivo;
        elemento.sendKeys(filePath);
    }

    public boolean buscarTextoNonThrow(String mensaje){
        By selector = By.xpath("//*[contains(text(),'" + mensaje + "')]");
        waitUntilElementIsVisibleNonThrow(selector,20);
        WebElement element = getDriver().findElement(selector);
        scrollDownToElement(element);
        return isVisible(element);
    }

    public void actualizarPage() {
        getDriver().navigate().refresh();
    }

    public void getArchivoDescargadoBrowserStackNombre(String nombreArchivo, String testCaseKey) throws IOException {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        String base64EncodedFile = (String) jse.executeScript("browserstack_executor: {\"action\": \"getFileContent\", " +
                "\"arguments\": {\"fileName\": \"" + nombreArchivo + "\"}}");
        byte[] data = Base64.getDecoder().decode(base64EncodedFile);
        File folder = new File(System.getProperty("user.dir") + File.separator + "target" +
                File.separator + "descargas-" + testCaseKey);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        OutputStream stream = Files.newOutputStream(Paths.get(System.getProperty("user.dir") + File.separator +
                "target" + File.separator + "descargas-" + testCaseKey + File.separator + nombreArchivo));
        stream.write(data);
        stream.close();
    }

    public void getArchivoDescargadoBrowserStackExtension(String extension, String testCaseKey) throws IOException {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        String base64EncodedFile = (String) jse.executeScript("browserstack_executor: {\"action\": \"getFileContent\"}");
        byte[] data = Base64.getDecoder().decode(base64EncodedFile);
        File folder = new File(System.getProperty("user.dir") + File.separator + "target" +
                File.separator + "descargas-" + testCaseKey);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        OutputStream stream = Files.newOutputStream(Paths.get(System.getProperty("user.dir") + File.separator +
                "target" + File.separator + "descargas-" + testCaseKey + File.separator + "browserStackDownloadedFile." + extension));
        stream.write(data);
        stream.close();
    }

    public String getTextViaJavascript(WebElement element) {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) getDriver());
        Object stringRetorno = javascriptExecutor.executeScript("return arguments[0].value;", element);
        return String.valueOf(stringRetorno);
    }

    public void sendKeysToPage(CharSequence keys) {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(keys).perform();
    }

    public boolean validarPage(String seccionUrl){
        WebBasePage DriverFactory = null;
        String urlPaginaActual = DriverFactory.getDriver().getCurrentUrl();
        if(urlPaginaActual.equals(seccionUrl)){
            return true;
        }else{
            return false;
        }
    }

    public void cambiarFocoASegundaPestanaNavegador() {
        ArrayList<String> windows = new ArrayList<String> (getDriver().getWindowHandles());
        getDriver().switchTo().window(windows.get(1));
        waitUntilPageIsLoaded();
    }

    private void waitUntilPageIsLoaded() {
    }

    public void cambiarFocoPestanaNavegador(int pestana) {
        ArrayList<String> windows = new ArrayList<String> (getDriver().getWindowHandles());
        getDriver().switchTo().window(windows.get(pestana));
    }

    public void clickEnLaPantalla(){
        ((JavascriptExecutor)driver).executeScript("document.elementFromPoint(10, 10).click();");
    }

    public void scrollDown3() {
        try {
            ((JavascriptExecutor)driver).executeScript("scroll(500,1500)");
            ((JavascriptExecutor)driver).executeScript("scroll(500,1500)");
        } catch (Exception ignored){}
    }

    public int obtenerDiferenciaDeDiasEntreUnaFechaYLaFechaActual(String fecha){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaACompararConActual = LocalDate.parse(fecha, formatoFecha);
        return (int)DAYS.between(fechaACompararConActual,fechaActual);
    }

    public String getTextElementP(String text){
        WebElement elementP = getDriver().findElement(By.xpath("//p[text()=' "+text+" ']"));
        waitUntilElementIsVisible(elementP);
        return elementP.getText();
    }

    void waitUntilElementIsVisible(WebElement elementP) {
    }

    public String getH2ElementText(String text){
        By selector = By.xpath("//h2[text()=' "+text+" ']");
        waitUntilElementIsVisible((WebElement) selector);
        WebElement h2TextElement = getDriver().findElement(selector);
        return h2TextElement.getText();
    }

    public String getH3ElementText(String text){
        By selector = By.xpath("//h3[text()=' "+text+" ']");
        waitUntilElementIsVisible((WebElement) selector);
        WebElement h3TextElement = getDriver().findElement(selector);
        return h3TextElement.getText();
    }

    public String getSpanElementText(String text){
        By selector = By.xpath("//span[text()='"+text+"']");
        waitUntilElementIsVisible((WebElement) selector);
        WebElement spanTextElement = getDriver().findElement(selector);
        scrollDown();
        return spanTextElement.getText();
    }

    public void clickBodyPortal(){
        By selector = By.id("body-portal");
        waitUntilElementIsVisibleNonThrow(selector,10);
        WebElement globalTextElement = getDriver().findElement(selector);
        globalTextElement.click();
    }

    public void clickConJavascript(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void scrollDownParameter(String valor) {
        try {
            ((JavascriptExecutor)driver).executeScript("scroll(0," + valor + ")");
            ((JavascriptExecutor)driver).executeScript("scroll(0," + valor + ")");
        } catch (Exception ignored) {

        }
    }
}