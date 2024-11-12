package StepDefinition;

import Page.FormularioPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class FormularioDefinition {

    public WebDriver driver;
    public FormularioPage page = new FormularioPage(driver);
    @Given("ingreso a pagina web")
    public void ingresoPaginaWeb() {
        page.ingresarPaginaWeb();
    }
    @When("se ingresa nombre {string} en campo first name")
    public void seIngresaNombreEnCampoFirstname(String nombre) {
        page.sendKeysName(nombre);
    }
    @And("se ingresa apellido {string} en campo last name")
    public void seIngresaApellidoEnCampoLastname(String apellido) {
        page.sendKeysApellido(apellido);
    }
    @Then("se valida valor ingresado en campo first name")
    public void seValidaValorIngresadoEnCampoFirstName() {
        String nameEsperado = "Haidet";
        assertEquals(nameEsperado, page.getValueNombre());
    }
    @And("se valida valor ingresado en campo last name")
    public void seValidaValorIngresadoEnCampoLastName() {
        String lastNameEsperado = "Navarro";
        assertEquals(lastNameEsperado, page.getValueApellido());
    }
}
