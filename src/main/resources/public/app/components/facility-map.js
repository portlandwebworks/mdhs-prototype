'use strict';

angular.module('mdhs').component('facilityMap', {
  templateUrl: '/templates/app/components/facility-map',
  controller: function (FacilityService, uiGmapIsReady) {
    var controller = this;

    //Initialize the map
    var defaultMapState = {
      center: {
        latitude: 32.611462,
        longitude: -89.834235
      },
      zoom: 7,
      options: {
        scrollwheel: false
      }
    };

    //Set the default zoom and center of the map
    controller.map = angular.copy(defaultMapState);

    //Bind the facilities to the facilities service
    controller.facilities = FacilityService.getCurrentFacilities();

    //Initialize a gmap variable to communicate with the google map instance
    var gMap;

    //Add function to zoom to all facilities on the map
    var zoomToFacilities = function (facilities) {
      if (gMap && facilities && facilities.length > 0) {
        var bounds = new google.maps.LatLngBounds();
        _.each(facilities, function (facility) {
          bounds.extend(new google.maps.LatLng({lat: facility.lat, lng: facility.lng}));
        });
        gMap.fitBounds(bounds);
      }
    };

    //When the map is ready, zoom to the current facilities
    uiGmapIsReady.promise(1).then(function (instances) {
      gMap = instances[0].map;
      zoomToFacilities(controller.facilities);
    });


    //Set up click events for the markers
    controller.mapClick = function(marker, eventName, model) {
      controller.selectedFacility = marker.key;
      gMap.setZoom(16);
      gMap.setCenter(marker.getPosition());
    };

    controller.map.events = {
      click: controller.mapClick
    };

  }
});
