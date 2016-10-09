angular.module('mdhs').component('home', {
	templateUrl: '/templates/app/components/home',
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
