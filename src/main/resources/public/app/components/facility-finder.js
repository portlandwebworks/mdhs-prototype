'use strict';

angular.module('mdhs').component('facilityFinder', {
  templateUrl: '/templates/app/components/facility-finder',
  controller: function (SearchOptionsService, FacilityService) {
    var controller = this;

    SearchOptionsService.getCities().then(function(cities){ controller.cities = cities });
    SearchOptionsService.getCounties().then(function(counties){ controller.counties = counties });
    controller.facilityTypes = SearchOptionsService.getProviderTypes();
    controller.genders = SearchOptionsService.getGenders();
    controller.ages = SearchOptionsService.getAges();

   initializeFilters();

    controller.childInfo = [
      { gender: null, age: null }
    ];

    controller.search = function(){
      _.forEach(controller.childInfo, function(child){
        if(!_.includes(controller.facilityFilters.genders,child.gender)){
          controller.facilityFilters.genders.push(child.gender);
        }
        if(!_.includes(controller.facilityFilters.ages,child.age)){
          controller.facilityFilters.ages.push(child.age);
        }
      });

      FacilityService.find(controller.facilityFilters)
        .then(function(){

        },function(){

        })
    };

    controller.reset = function(){
      initializeFilters();
      controller.childInfo = [{ gender: null, age: null }];
    }

    controller.addChild = function(){
      controller.childInfo.push({ gender: null, age: null });
    }

    function initializeFilters(){
      controller.facilityFilters = {
        withinDistance: '10',
        genders: [],
        ages: []
      };
    }
  }
});
