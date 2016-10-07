angular.module('mdhs').service('FacilityService', function ($http, $q) {

  var service = this;

  service.currentFacilities = [];

  service.find = function (criteria) {
    return $http.get('/api/facilities', {params: criteria}).then(function (response) {
      // We're binding to the array. Insert data without replacing array.
      service.currentFacilities.length = 0;
      Array.prototype.push.apply(service.currentFacilities, response.data);

      return $q.resolve(response);
    }, function (response) {
      service.currentFacilities.length = 0;
      return $q.reject(response);
    });
  };

  service.getCurrentFacilities = function () {
    return service.currentFacilities;
  };

});
