Feature: Validating Place APIs

Scenario Outline: Verify if Place is being successfully added using AddPlaceApi
     Given Add place payload
     When user called "AddPaceAPI" using Post http call
     Then api call get success with status code as 200
     And "status" response code is "OK"
     And "scope" is response body is "APP"
     
Examples: 
     |name| language| address|
     |Rishav| English| world center|	