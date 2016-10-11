angular.module('mdhs').service('FacilityService', function ($http, $q) {
  var service = this;

  var currentFacilities = [];

  service.find = function (criteria) {
    return $http.get('/api/facilities', {params: criteria}).then(function (response) {

      // We're binding to the array. Insert data without replacing array.
      currentFacilities.length = 0;
      Array.prototype.push.apply(currentFacilities, response.data);

      return $q.resolve(response.data);
    }, function (response) {

      currentFacilities.length = 0;
      return $q.reject(response);

    });
  };

  service.getCurrentFacilities = function () {
    return currentFacilities;
  };

  service.getAllowedGenders = function(){
    return {
      MALE: 'MALE',
      FEMALE: 'FEMALE',
      BOTH: 'BOTH'
    };
  };

  service.getAgeRanges = function(){
    return {
      INFANT: {
        minimumAge: 0,
        maximumAge: 2
      },
      TODDLER: {
        minimumAge: 3,
        maximumAge: 6
      },
      CHILD: {
        minimumAge: 7,
        maximumAge: 10
      },
      PRETEEN: {
        minimumAge: 11,
        maximumAge: 14
      },
      TEEN: {
        minimumAge: 15,
        maximumAge: 18
      }
    };
  };

  service.getLicenseTypes = function(){
    return {
      LICENSED: 'LICENSED',
      UNLICENSED: 'LICENSED'
    };
  }

});
