angular.module('mdhs').component('search', {
  templateUrl: '/templates/app/pages/search/index',
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
