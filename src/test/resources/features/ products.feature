Feature: Product management on Fake Store API
  As a consumer of the Fake Store API
  I want to validate CRUD operations on products
  So that I can ensure the catalog works correctly

  Scenario: List all available products
    Given the Fake Store API service is available
    When I request the full list of products
    Then the response status code should be 200
    And the number of returned products should be greater than 0

  Scenario: Get an existing products by its identifier
    Given the Fake Store API service is available
    When I request the product with id 1
    Then the response status code should be 200
    And the returned product should have a non-empty title
    And the returned product should have a price greater than 0

  Scenario Outline: Get multiple products by different identifiers
    Given the Fake Store API service is available
    When I request the product with id <productId>
    Then the response status code should be 200
    And the returned product should have the category "<expectedCategory>"
    And the returned product should have a price greater than <minPrice>

    Examples:
    | productId | expectedCategory | minPrice |
    | 1         | men's clothing   | 40       |
    | 2         | men's clothing   | 20       |
    | 15        | women's clothing | 10       |

