angular.module('mdhs').service('FacilityService', function ($http, $q, FacilitySortService) {
  var service = this;

  var currentFacilities = [];

  service.find = function (criteria) {
	var filterSettings = FacilitySortService.getFilterSettings();
	var postedCriteria = angular.copy(criteria);
	if(postedCriteria === undefined){
		postedCriteria = {};
	}

	if(filterSettings.hasSlots()){
		postedCriteria.availableOpenings = true;
	}

	if(filterSettings.hasNoHistory()){
		postedCriteria.noConvictions = true;
	}

	if(filterSettings.hasLicense()) {
		postedCriteria.licensed = true;
	}

	postedCriteria.sortBy = FacilitySortService.getSortSettings().sortBy().sortBy;
	postedCriteria.sortDir = FacilitySortService.getSortSettings().sortBy().sortDir;

    return $http.get('/api/facilities', {params: postedCriteria}).then(function (response) {
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
      MALE: 'Male',
      FEMALE: 'Female',
      BOTH: 'Both'
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
      UNLICENSED: 'UNLICENSED'
    };
  };

  service.getCounties = function(){
    return $http.get('/api/facilities/addresses/counties').then(function (response) {
      return response.data;
    });
  };

  service.getCities = function(){
    return $http.get('/api/facilities/addresses/cities').then(function (response) {
      return response.data;
    });
  };

  service.getProviderTypes = function(){
    return $http.get('/api/facilities/provider-types').then(function (response) {
      return response.data;
    });
  };

  service.getFacilitySizes = function() {
    return {
      SMALL: {
        label:'Small (0-15 Children)',
        minimumSize: 0,
        maximumSize: 15
      },
      MEDIUM: {
        label: 'Medium (16-50 Children)',
        minimumSize: 16,
        maximumSize: 50,
      },
      LARGE: {
        label: 'Large (51+ Children)',
        minimumSize: 51
      }
    };
  };
});
