'use strict';


angular.module('mdhs').directive('facilityFinder', function (SearchOptionsFactory, FacilityService) {
  return {
    restrict: 'E',
    templateUrl: '/templates/app/components/facility-finder',
    controller: function ($scope) {
      SearchOptionsFactory.getCities().then(function(cities){ $scope.cities = cities });
      SearchOptionsFactory.getCounties().then(function(counties){ $scope.counties = counties });
      $scope.facilityTypes = SearchOptionsFactory.getProviderTypes();
      $scope.genders = SearchOptionsFactory.getGenders();
      $scope.ages = SearchOptionsFactory.getAges();

     initializeFilters();

      $scope.childInfo = [
        { gender: null, age: null }
      ];

      $scope.search = function(){
        _.forEach($scope.childInfo, function(child){
          if(!_.includes($scope.facilityFilters.genders,child.gender)){
            $scope.facilityFilters.genders.push(child.gender);
          }
          if(!_.includes($scope.facilityFilters.ages,child.age)){
            $scope.facilityFilters.ages.push(child.age);
          }
        });

        FacilityService.find($scope.facilityFilters)
          .then(function(){

          },function(){

          })
      };

      $scope.reset = function(){
        initializeFilters();
        $scope.childInfo = [{ gender: null, age: null }];
      }

      $scope.addChild = function(){
        $scope.childInfo.push({ gender: null, age: null });
      }

      function initializeFilters(){
        $scope.facilityFilters = {
          withinDistance: '10',
          genders: [],
          ages: []
        };
      }
    }
  }});
