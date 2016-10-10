angular.module('mdhs').service('FacilityService', function ($http, $q) {
  var service = this;

  var currentFacilities = [];

  var sortSettings = {
    sortBy: ''
  };

  var filterSettings = {
    hasSlots: true,
    hasNoHistory: false,
    hasLicense: false
  };

  service.find = function (criteria) {
    return $http.get('/api/facilities', {params: criteria}).then(function (response) {

      // We're binding to the array. Insert data without replacing array.
      currentFacilities.length = 0;
      Array.prototype.push.apply(currentFacilities, response.data);

      return $q.resolve(response);
    }, function (response) {

      currentFacilities.length = 0;
      return $q.reject(response);

    });
  };

  service.getCurrentFacilities = function () {
    return currentFacilities;
  };

  service.getSortSettings = function(){
    return sortSettings;
  };

  service.getFilterSettings = function(){
    return filterSettings;
  };

});
