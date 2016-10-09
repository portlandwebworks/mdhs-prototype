'use strict';

angular.module('mdhs').component('search', {
  templateUrl: '/templates/app/components/search',
  controller: function (FacilityService) {

    // Do an initial blank search.
    FacilityService.find();
  }
});
