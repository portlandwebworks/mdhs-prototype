'use strict';

angular.module('mdhs').component('facilityFinder', {
  templateUrl: '/templates/app/components/facility-finder',
  controller: function ($scope, FacilityService) {
    var controller = this;

    FacilityService.getCities().then(function(cities){ controller.cities = cities; });
    FacilityService.getCounties().then(function(counties){ controller.counties = counties; });
    controller.facilityTypes = FacilityService.getProviderTypes();
    controller.genders = FacilityService.getAllowedGenders();
    controller.ages = FacilityService.getAgeRanges();
    controller.facilitySizes = FacilityService.getFacilitySizes();

    initializeFilters();

    controller.childInfo = [
      { gender: null, age: null }
    ];

    controller.search = function(){
      var criteria = angular.copy(controller.facilityFilters);
	    criteria.genders = [];
	    criteria.ageRanges = [];
      criteria.capacityMinimum = controller.facilitySizes[controller.facilityFilters.size].minimumSize;
      criteria.capacityMaximum = controller.facilitySizes[controller.facilityFilters.size].maximumSize;
      _.forEach(controller.childInfo, function(child){
        if(child.gender !== null){
          criteria.genders.push(child.gender);
        }
        if(child.age !== null){
          var age = controller.ages[child.age];
          criteria.ageRanges.push({ minumumAge: age.minumumAge, maximumAge: age.maximumAge });
        }
      });

      FacilityService.find(criteria)
        .then(function(){

        },function(){

        });
    };

    controller.reset = function(){
      initializeFilters();
      controller.childInfo = [{ gender: null, age: null }];
    };

    controller.addChild = function(){
      controller.childInfo.push({ gender: null, age: null });
    };

    function initializeFilters(){
      controller.facilityFilters = {
        withinDistance: '10'
      };
    }

    $scope.$on('event:facilities:find', function(){
	  controller.search();
    });
  }
});
