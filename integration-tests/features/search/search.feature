Feature: Search for Results
  As a caseworker or parent
  I want to be able to search for child care providers

    @prod
    Scenario: Finder page elements appear as expected
      Given I am on the "search" page
      Then the text of class "search__header-text" should be "CHILDCARE PROVIDER FINDER"
      And the model "$ctrl.facilityFilters.city" should exist
      And the model "$ctrl.facilityFilters.withinDistance" should exist
      And the model "$ctrl.facilityFilters.description" should exist
      And the model "$ctrl.facilityFilters.size" should exist
      And I should see the class "facility-finder__search-button"
      And I should see the class "facility-finder__reset-button"
      And the text of class "form__sub-title-text" should be "Child Information (Optional)"
      And the model "child.gender" should exist
      And the model "child.age" should exist
      And I should see the class "facility-finder__add-child"
      And I should see the class "facility-map"
      And I should see the class "facility-sorter__sort"
      And the model "$ctrl.filterSettings.hasSlots" should exist
      And the model "$ctrl.filterSettings.hasNoHistory" should exist
      And the model "$ctrl.filterSettings.hasLicense" should exist
      And I should see the class "facility-listing"
      And I should see the class "site-footer__pww"

    @prod
    Scenario: Search by City
      Given I am on the "search" page
      When I click on "ARCOLA" in the dropdown "$ctrl.facilityFilters.city"
      And I click on "Within 10 miles" in the dropdown "$ctrl.facilityFilters.withinDistance"
      And I click on "Provider Type" in the dropdown "$ctrl.facilityFilters.description"
      And I click on "Provider Size" in the dropdown "$ctrl.facilityFilters.size"
      And I click the "facility-finder__search-button" class in the "facility-finder" class
      And I click on "Rating" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Ana Briseno Childcare"

    @prod
    Scenario: Search by Distance
      Given I am on the "search" page
      When I click on "ARCOLA" in the dropdown "$ctrl.facilityFilters.city"
      And I click on "Within 25 miles" in the dropdown "$ctrl.facilityFilters.withinDistance"
      And I click on "Provider Type" in the dropdown "$ctrl.facilityFilters.description"
      And I click on "Provider Size" in the dropdown "$ctrl.facilityFilters.size"
      And I click the "facility-finder__search-button" class in the "facility-finder" class
      And I click on "Rating" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Belinda Moore"

    @prod
    Scenario: Search by Provider Type
      Given I am on the "search" page
      When I click on "ARCOLA" in the dropdown "$ctrl.facilityFilters.city"
      And I click on "Within 10 miles" in the dropdown "$ctrl.facilityFilters.withinDistance"
      And I click on "Non-Relative In-Home" in the dropdown "$ctrl.facilityFilters.description"
      And I click on "Provider Size" in the dropdown "$ctrl.facilityFilters.size"
      And I click the "facility-finder__search-button" class in the "facility-finder" class
      And I click on "Rating" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Lucrecia Angelica Alvarez"

    @prod
    Scenario: Search by Provider Size
      Given I am on the "search" page
      When I click on "ARCOLA" in the dropdown "$ctrl.facilityFilters.city"
      And I click on "Within 25 miles" in the dropdown "$ctrl.facilityFilters.withinDistance"
      And I click on "Provider Type" in the dropdown "$ctrl.facilityFilters.description"
      And I click on "Small (0-15 Children)" in the dropdown "$ctrl.facilityFilters.size"
      And I click the "facility-finder__search-button" class in the "facility-finder" class
      And I click on "Rating" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Blessings From Heaven Learning Center"

    @prod
    Scenario: Search with Child Information
      Given I am on the "search" page
      When I click on "ARCOLA" in the dropdown "$ctrl.facilityFilters.city"
      And I click on "Within 25 miles" in the dropdown "$ctrl.facilityFilters.withinDistance"
      And I click on "Provider Type" in the dropdown "$ctrl.facilityFilters.description"
      And I click on "Small (0-15 Children)" in the dropdown "$ctrl.facilityFilters.size"
      And I click on "Female" in the dropdown "child.gender" in row "0" of repeater "child in $ctrl.childInfo"
      And I click on "0 - 2 years" in the dropdown "child.age" in row "0" of repeater "child in $ctrl.childInfo"
      And I click the "facility-finder__search-button" class in the "facility-finder" class
      And I click on "Rating" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Maria Reyes Ibarra"

    @prod
    Scenario: Search with Multiple Child Information
      Given I am on the "search" page
      When I click on "ARCOLA" in the dropdown "$ctrl.facilityFilters.city"
      And I click on "Within 25 miles" in the dropdown "$ctrl.facilityFilters.withinDistance"
      And I click on "Provider Type" in the dropdown "$ctrl.facilityFilters.description"
      And I click on "Small (0-15 Children)" in the dropdown "$ctrl.facilityFilters.size"
      And I click on "Female" in the dropdown "child.gender" in row "0" of repeater "child in $ctrl.childInfo"
      And I click on "0 - 2 years" in the dropdown "child.age" in row "0" of repeater "child in $ctrl.childInfo"
      And I click the "facility-finder__add-child" class in the "facility-finder__filters" class
      And I click on "Male" in the dropdown "child.gender" in row "1" of repeater "child in $ctrl.childInfo"
      And I click on "3 - 6 years" in the dropdown "child.age" in row "1" of repeater "child in $ctrl.childInfo"
      And I click the "facility-finder__search-button" class in the "facility-finder" class
      And I click on "Rating" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Maria Antonia Sauceda"

    @prod
    Scenario: Filters - Slots Available
      Given I am on the "search" page
      When I click on "ARCOLA" in the dropdown "$ctrl.facilityFilters.city"
      And I click on "Within 25 miles" in the dropdown "$ctrl.facilityFilters.withinDistance"
      And I click on "Non-Relative In-Home" in the dropdown "$ctrl.facilityFilters.description"
      And I click on "Provider Size" in the dropdown "$ctrl.facilityFilters.size"
      And I click on "Male" in the dropdown "child.gender" in row "0" of repeater "child in $ctrl.childInfo"
      And I click on "3 - 6 years" in the dropdown "child.age" in row "0" of repeater "child in $ctrl.childInfo"
      And I click the "facility-finder__search-button" class in the "facility-finder" class
      And I click on "Rating" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Lottie Williams"
      And I click the model "$ctrl.filterSettings.hasSlots"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Lashamwaya Williams"

    @prod
    Scenario: Filters - Without History
      Given I am on the "search" page
      When I click on "ARCOLA" in the dropdown "$ctrl.facilityFilters.city"
      And I click on "Within 25 miles" in the dropdown "$ctrl.facilityFilters.withinDistance"
      And I click on "Center" in the dropdown "$ctrl.facilityFilters.description"
      And I click on "Provider Size" in the dropdown "$ctrl.facilityFilters.size"
      And I click on "Female" in the dropdown "child.gender" in row "0" of repeater "child in $ctrl.childInfo"
      And I click on "3 - 6 years" in the dropdown "child.age" in row "0" of repeater "child in $ctrl.childInfo"
      And I click the "facility-finder__search-button" class in the "facility-finder" class
      And I click on "Provider Name" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Abacus School of Austin"
      And I click the model "$ctrl.filterSettings.hasNoHistory"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Aransas Child Care"

    @prod
    Scenario: Filters - Without History
      Given I am on the "search" page
      When I click on "ARCOLA" in the dropdown "$ctrl.facilityFilters.city"
      And I click on "Within 10 miles" in the dropdown "$ctrl.facilityFilters.withinDistance"
      And I click on "Provider Type" in the dropdown "$ctrl.facilityFilters.description"
      And I click on "Provider Size" in the dropdown "$ctrl.facilityFilters.size"
      And I click the "facility-finder__search-button" class in the "facility-finder" class
      And I click on "Provider Name" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "1" of repeater "facility in $ctrl.facilities" should be "Amber Kelley"
      And I click the model "$ctrl.filterSettings.hasLicense"
      Then the text of class "facility-detail-large__name" in row "1" of repeater "facility in $ctrl.facilities" should be "Ana Briseno Childcare"

    @prod
    Scenario: Sorting
      Given I am on the "search" page
      When I click on "ARCOLA" in the dropdown "$ctrl.facilityFilters.city"
      And I click on "Within 100 miles" in the dropdown "$ctrl.facilityFilters.withinDistance"
      And I click on "Provider Type" in the dropdown "$ctrl.facilityFilters.description"
      And I click on "Provider Size" in the dropdown "$ctrl.facilityFilters.size"
      And I click the "facility-finder__search-button" class in the "facility-finder" class
      And I click on "Provider Name" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "1.2..me Kidz"
      And I click on "Distance" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "A Habitat for Learning"
      And I click on "Rating" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Barbara Ann Parks"
      And I click on "Size, largest to smallest" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Angela Goforth"
      And I click on "Size, smallest to largest" in the dropdown "$ctrl.sortSettings.sortBy"
      Then the text of class "facility-detail-large__name" in row "0" of repeater "facility in $ctrl.facilities" should be "Patricia Rayne Paez"
