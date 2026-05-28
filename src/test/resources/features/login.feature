Feature: Login Functionality

  @smoke

  Scenario: Valid Login

    Given User launches the application
    When User enters valid email and password
    Then User should login successfully