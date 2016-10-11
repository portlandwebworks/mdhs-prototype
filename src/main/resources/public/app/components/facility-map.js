'use strict';

angular.module('mdhs').component('facilityMap', {
  templateUrl: '/templates/app/components/facility-map',
  controller: function (FacilityService) {
    var controller = this;

    //Initialize the map
    var defaultMapState = {
      center: {
        latitude: 32.611462,
        longitude: -89.834235
      },
      zoom: 7
    };

    controller.map = angular.copy(defaultMapState);

    controller.facilities = FacilityService.getCurrentFacilities();


  }
});
