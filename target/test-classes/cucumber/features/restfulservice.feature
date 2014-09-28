Feature: Hello Web Service

Scenario: GET google.com
    Given I have a restful client
    When I connect google.com
    Then return 200OK