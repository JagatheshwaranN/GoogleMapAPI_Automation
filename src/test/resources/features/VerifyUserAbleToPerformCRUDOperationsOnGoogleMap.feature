@googleMap
Feature: Google Map CRUD Operations

  @post
  Scenario Outline: Verify user able to create place details
    Given user construct post request with "<key>" and request payload "<request>"
    Then user verifies post response with "<status>" and "<response>"

    Examples: 
      | key        | request      | status      | response      |
      | search.key | post.request | status.code | post.response |

  @get
  Scenario Outline: Verify user able to read place details
    Given user construct get request with "<place>" and "<key>"
    Then user verifies get response with "<status>" and "<response>"

    Examples: 
      | place        | key        | status      | response     |
      | get.place.id | search.key | status.code | get.response |

  @put
  Scenario Outline: Verify user able to update place details
    Given user construct put request with "<place>" and "<key>" and request payload "<request>"
    Then user verifies put response with "<status>" and "<response>"

    Examples: 
      | place        | key        | request     | status      | response     |
      | put.place.id | search.key | put.request | status.code | put.response |

  @delete
  Scenario Outline: Verify user able to delete place details
    Given user construct delete request with "<key>" and request payload "<request>"
    Then user verifies delete response with "<status>" and "<response>"

    Examples: 
      | key        | request        | status      | response        |
      | search.key | delete.request | status.code | delete.response |
