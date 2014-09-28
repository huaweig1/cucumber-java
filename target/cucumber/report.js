$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("hellocucumber.feature");
formatter.feature({
  "id": "hello-car",
  "description": "",
  "name": "Hello Car",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "hello-car;car-can-drive",
  "description": "",
  "name": "Car can drive",
  "keyword": "Scenario",
  "line": 3,
  "type": "scenario"
});
formatter.step({
  "name": "I have a car",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "I add 4 wheels",
  "keyword": "When ",
  "line": 5
});
formatter.step({
  "name": "It can drive",
  "keyword": "Then ",
  "line": 6
});
formatter.match({
  "location": "CarTestStepDefinitions.i_have_a_car()"
});
formatter.result({
  "duration": 267819641,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "4",
      "offset": 6
    }
  ],
  "location": "CarTestStepDefinitions.i_add_wheels(int)"
});
formatter.result({
  "duration": 1673814,
  "status": "passed"
});
formatter.match({
  "location": "CarTestStepDefinitions.it_can_drive()"
});
formatter.result({
  "duration": 1399045,
  "status": "passed"
});
formatter.uri("restfulservice.feature");
formatter.feature({
  "id": "hello-web-service",
  "description": "",
  "name": "Hello Web Service",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "hello-web-service;get-google.com",
  "description": "",
  "name": "GET google.com",
  "keyword": "Scenario",
  "line": 3,
  "type": "scenario"
});
formatter.step({
  "name": "I have a restful client",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "I connect google.com",
  "keyword": "When ",
  "line": 5
});
formatter.step({
  "name": "return 200OK",
  "keyword": "Then ",
  "line": 6
});
formatter.match({
  "location": "RestfulServiceTestStepDefinitions.i_have_a_restful_client()"
});
formatter.result({
  "duration": 168226886,
  "status": "passed"
});
formatter.match({
  "location": "RestfulServiceTestStepDefinitions.i_connect_google_com()"
});
formatter.result({
  "duration": 2413095845,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "200",
      "offset": 7
    }
  ],
  "location": "RestfulServiceTestStepDefinitions.return_OK(int)"
});
formatter.result({
  "duration": 18846750,
  "status": "passed"
});
});