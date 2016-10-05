
angular.module('mdhs').service('FacilityService', function ($http, $q) {

	var service = this;

	service.find = function (criteria) {
		return $http.get('/api/facilities', {params: criteria}).then(function (resp) {
			return $q.resolve(resp.data);
		}, function (resp) {
			return $q.reject(resp);
		});
	};

});
