#utf-8
@formulario @coding @paraguay
Feature: Formulario

  @1 @codingdojo @regresiva
  Scenario: Validar cajas de texto nombre y apellido correctamente
    Given ingreso a pagina web
    When se ingresa nombre "Haidet" en campo first name
    And se ingresa apellido "Navarro" en campo last name
    Then se valida valor ingresado en campo first name
    And se valida valor ingresado en campo last name

    asasas