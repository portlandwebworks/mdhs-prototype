'use strict';

angular.module('mdhs').component('facilityFinder', {
  templateUrl: '/templates/app/components/facility-finder',
  controller: function (SearchOptionsService, FacilityService) {
    var controller = this;

    SearchOptionsService.getCities().then(function(cities){ controller.cities = cities; });
    SearchOptionsService.getCounties().then(function(counties){ controller.counties = counties; });
    controller.facilityTypes = SearchOptionsService.getProviderTypes();
    controller.genders = SearchOptionsService.getGenders();
    controller.ages = SearchOptionsService.getAges();
    controller.facilitySizes = SearchOptionsService.getFacilitySizes();

   initializeFilters();

    controller.childInfo = [
      { gender: null, age: null }
    ];

    controller.search = function(){
      var criteria = angular.copy(controller.facilityFilters);
	  criteria.genders = [];
	  criteria.ageRanges = [];
      _.forEach(controller.childInfo, function(child){
        if(child.gender !== null){
          criteria.genders.push(child.gender);
        }
        if(child.age !== null){
          criteria.ageRanges.push(child.age);
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
  }
});
