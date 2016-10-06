'use strict';


angular.module('mdhs').directive('facilityFinder', function (SearchOptionsFactory, FacilityService) {
  return {
    restrict: 'E',
    templateUrl: '/templates/app/directives/facility-finder/index',
    controller: function ($scope) {
      $scope.cities = SearchOptionsFactory.getCities();
      $scope.counties = SearchOptionsFactory.getCounties();
      $scope.facilityTypes = SearchOptionsFactory.getProviderTypes();
    }
  }});
