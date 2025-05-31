Feature: Hello endpoint

  Scenario: GET /hello
    Given url 'http://localhost:8080/hello'
    When method get
    Then status 200
    And match response == 'Hello World!'
