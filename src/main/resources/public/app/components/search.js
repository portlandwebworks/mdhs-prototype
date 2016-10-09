'use strict';

angular.module('mdhs').component('search', {
  templateUrl: '/templates/app/components/search',
  controller: function (FacilityService) {
    var ctrl = this;

    // Bind facilities array to this controller
    ctrl.facilities = FacilityService.getCurrentFacilities();

    // Do an initial blank search.
    FacilityService.find();
  }
});
