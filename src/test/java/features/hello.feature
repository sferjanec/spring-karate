Feature: Hello endpoint

  Scenario: Informal hello
    Given url 'http://localhost:8080/hello/informal'
    When method get
    Then status 200
    And match response == 'Hey there!'

  Scenario: Formal hello
    Given url 'http://localhost:8080/hello/formal'
    When method get
    Then status 200
    And match response == 'Hello World!'

  Scenario: Test custom status code 200
    Given url 'http://localhost:8080/hello/status/200'
    When method get
    Then status 200
    And match response == 'Everything is fine'

  Scenario: Test custom status code 404
    Given url 'http://localhost:8080/hello/status/404'
    When method get
    Then status 404
    And match response == 'Not found'

  Scenario: Test custom status code 500
    Given url 'http://localhost:8080/hello/status/500'
    When method get
    Then status 500
    And match response == 'Something went wrong'

  Scenario: Post echo message
    Given url 'http://localhost:8080/hello/echo'
    And request { message: 'hello karate' }
    When method post
    Then status 200
    And match response == 'Echo: hello karate'
