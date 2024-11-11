package StepDefinition;

import Page.FormularioPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

public class FormularioDefinition {

    public WebDriver driver;
    public FormularioPage page = new FormularioPage(driver);
    @Given("ingreso a pagina web")
    public void ingresoPaginaWeb() {
        page.ingresarPaginaWeb();
    }
    @When("se ingresa texto {string} en campo full name")
    public void seIngresaTextoEnCampoFullname(String text) {
        page.sendKeysName(text);
    }
}
