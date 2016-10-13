Feature: Navigation Tools
  As any user
  I want to view information about the site and navigate the site from the header and footer

  @prod
  Scenario: Header Links
    Given I am on the "home" page
    When I click the "CHILDCARE PROVIDER FINDER" link
    Then I should be on the "search" page
    When I click the "ABOUT" link
    Then I should be on the "about" page
    When I click the "site-header__logo-link" class in the "site-header__navigation" class
    Then I should be on the "home" page

  @prod
  Scenario: Footer Links
    Given I am on the "about" page
    When I click the "site-footer__logo" class in the "site-footer__pww" class
    Then I should be on the "home" page
    And the value of attribute "href" of class "site-footer__social a:nth-child(1)" should be "https://www.facebook.com/PortlandWebworks"
    And the value of attribute "href" of class "site-footer__social a:nth-child(2)" should be "https://twitter.com/portlandwebwork"
