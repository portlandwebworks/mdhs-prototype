'use strict';


angular.module('mdhs').directive('facilityFinder', function (SearchOptionsFactory, FacilityService) {
  return {
    restrict: 'E',
    templateUrl: '/templates/app/directives/facility-finder/index',
    controller: function ($scope) {
      $scope.cities         = SearchOptionsFactory.getCities();
      $scope.counties       = SearchOptionsFactory.getCounties();
      $scope.facilityTypes  = SearchOptionsFactory.getProviderTypes();
      $scope.genders        = SearchOptionsFactory.getGenders();
      $scope.ages           = SearchOptionsFactory.getAges();

      $scope.test = [];

      $scope.facilityFilters = {
        distance: '10'
      }

      $scope.search = function(){
        FacilityService.find($scope.facilityFilters)
          .then(function(){

          },function(){

          })
      }
    }
  }});
