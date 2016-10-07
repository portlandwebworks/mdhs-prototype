
angular.module('mdhs').service('FacilityService', function ($http, $q) {

	var service = this;

  service.currentFacilities = [];

	service.find = function (criteria) {
		return $http.get('/api/facilities', {params: criteria}).then(function (resp) {
      service.currentFacilities = resp.data;
			return $q.resolve();
		}, function (resp) {
      service.currentFacilities = [];
			return $q.reject(resp);
		});
	};

  service.getCurrentFacilities = function(){
    return service.currentFacilities;
  }

});
