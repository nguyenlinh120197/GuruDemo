Feature: login

  @login
  Scenario Outline: check login feature pass
    Given browser is open
    And run test case: "<testCase>"
    And user is on login page
    When user enters username and password
      | userName   | passWord   |
      | <userName> | <passWord> |
    And user click on login button as "<KEY>"
      | KEY |
      | 01  |
      | 02  |
      | 03  |
      | 04  |
      | 05  |
      | 06  |
      | 07  |
    And click on reset button as "<KEY>"
      | KEY |
      | 10  |
    Then user is navigated to the home page as "<KEY>"
      | KEY | welcomeToManager'sPage |
      | 01  | Manger Id : <userName> |
      | 02  | Manger Id : <userName> |
      | 03  | Manger Id : <userName> |
      | 04  | Manger Id : <userName> |
      | 05  | Manger Id : <userName> |
      | 06  | Manger Id : <userName> |
    And check error message when username and password invalid as "<KEY>"
      | KEY | message                       |
      | 07  | User or Password is not valid |
    And check validate all field as "<KEY>"
      | KEY | Type     | message                    |
      | 08  | username | User-ID must not be blank  |
      | 09  | password | Password must not be blank |
    And check reset function work as "<KEY>"
      | KEY |
      | 10  |
    And close browser
    Examples:
      | KEY | testCase                                                  | userName   | passWord |
      | 01  | Login success                                             | mngr393487 | UpUqYra  |
      | 02  | Login success                                             | mngr47659  | 1@       |
      | 03  | Login success                                             | mngr416828 | yhAmYgA  |
      | 04  | Login success                                             | mngr421418 | guvUvuz  |
      | 05  | Login success                                             | mngr421419 | jevYryz  |
      | 06  | Login success                                             | mngr421420 | nerEpUh  |
      | 07  | Login not success with username and password invalid      | aaaa       | aaaa     |
      | 08  | Check validate message on UserID field when empty field   |            | UpUqYra  |
      | 09  | Check validate message on Password field when empty field | mngr393487 |          |
      | 10  | Check reset function                                      | mngr393487 | UpUqYra  |

