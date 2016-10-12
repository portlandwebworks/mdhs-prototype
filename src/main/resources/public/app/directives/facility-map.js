'use strict';

angular.module('mdhs').directive('facilityMap', function (FacilityService, uiGmapIsReady) {
  return {
    restrict: 'E',
    templateUrl: '/templates/app/directives/facility-map',
    controller: function ($scope) {

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
      $scope.map = angular.copy(defaultMapState);

      //Bind the facilities to the facilities service
      $scope.facilities = FacilityService.getCurrentFacilities();

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
        zoomToFacilities($scope.facilities);
      });


      //Set up click events for the markers
      $scope.mapClick = function (marker, eventName, model) {
        $scope.selectedFacility = marker.key;
        gMap.setZoom(16);
        gMap.setCenter(marker.getPosition());
      };

      $scope.map.events = {
        click: $scope.mapClick
      };

      //Watch the facilities for changes, and zoom to the bounds of the new results
      $scope.$watchCollection('facilities', function(newVal, oldVal){
        zoomToFacilities($scope.facilities);
      });
    }
  }
});
