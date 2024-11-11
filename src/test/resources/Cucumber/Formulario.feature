#utf-8
@formulario @coding @paraguay
Feature: Formulario

  @regresiva
  Scenario: Validar flujo de formulario
    Given ingreso a pagina web
    When se ingresa texto "Juan Rafael" en campo full name
#    And se presiona boton
#    Then se valida ingreso de texto en campo full name