angular.module('mdhs').component('home', {
	templateUrl: '/templates/app/pages/home/index',
	controller: function (FacilityService) {
		var ctrl = this;
		ctrl.message = 'Hello MDHS!';
		FacilityService.find().then(function (facilities) {
			ctrl.facilities = facilities;
		}, function(){
			ctrl.facilities = [];
		});
	}
});
