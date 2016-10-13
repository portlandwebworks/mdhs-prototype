Feature: Homepage
	As a user
	I want to see a homepage when I navigate to the site

	@prod
	Scenario: Page elements appear as expected
		Given I am on the "home" page
		Then I should see the class "site-header__logo"
		And I should see the link "CHILDCARE PROVIDER FINDER"
		And I should see the link "ABOUT"
		And I should see the class "home__container"
		And the text of class "home__greeting" should be "Welcome"
		And the text of class "home__message" should be "MDHS is dedicated to serving others while providing a wide range of public assistance programs, social services and support for children, low-income individuals and families."
		And I should see the class "home__button"
		And I should see the class "site-footer__logo"
		And the text of class "site-footer__mdhs" should be "Mississippi Department of Human Services"
		And the text of class "site-footer__address" should be "750 North State Street, Jackson, MS 39202"
		And the text of class "site-footer__phone" should be "800-345-6347"
		And the text of class "site-footer__report" should be "Call 800-222-8000 to report child abuse or neglect."
		And the text of class "site-footer__copyright" should be "©2016 Mississippi Department of Human Services. All rights reserved."
		And I should see the class "site-footer__social a[href='https://www.facebook.com/PortlandWebworks/']"
		And I should see the class "site-footer__social a[href='https://twitter.com/portlandwebwork']"
