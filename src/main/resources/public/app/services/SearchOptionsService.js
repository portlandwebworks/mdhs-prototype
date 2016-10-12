angular.module('mdhs').service('SearchOptionsService', function searchOptionsFactory($http) {
  var service = this;

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
    var types = [
      { value: 2, label: 'Slot Contractor'},
      { value: 3, label: 'Group Home'},
      { value: 4, label: 'Center'},
      { value: 5, label: 'Non-Relative In-Home'},
      { value: 6, label: 'Relative In-Home'},
      { value: 7, label: 'Non-Relative Out-of-Home'},
      { value: 8, label: 'Relative Out-of-Home'},
    ];

    return types;
  };

  service.getGenders = function(){
    return [
      { value: 'BOTH', label: 'Both'},
      { value: 'MALE', label: 'Boy'},
      { value: 'FEMALE', label: 'Girl'}
    ];
  };

  service.getAges = function(){
    return [
      { value: 'INFANT', label: '0-2 years'},
      { value: 'TODDLER', label: '3-6 years'},
      { value: 'CHILD', label: '6-10 years'},
      { value: 'PRETEEN', label: '11-14 years'},
      { value: 'TEEN', label: '15-18 years'},
    ];
  };

  service.getFacilitySizes = function() {
    return [
      { value: 1, label: 'Small (0-15 Children)'},
      { value: 2, label: 'Medium (16-50 Children)'},
      { value: 3, label: 'Large (51+ Children)'},
    ]
  }
});
