angular.module('mdhs').factory('SearchOptionsFactory', function searchOptionsFactory($http) {
  //TODO: switch these out with calls to backend service

  var getCounties = function(){
    return $http.get('/api/facilities/addresses/counties').then(function (response) {
      return response.data;
    });
  }

  var getCities = function(){
    return $http.get('/api/facilities/addresses/cities').then(function (response) {
      return response.data;
    });
  }

  var getProviderTypes = function(){
    //return $http.get('/api/provider-types').then(function (response) {
    //  return response.data;
    //});

    var types = [
      { value: 1, label: 'Test'},
      { value: 2, label: 'Slot Contractor'},
      { value: 3, label: 'Group Home'},
      { value: 4, label: 'Center'},
      { value: 5, label: 'Non-Relative In-Home'},
      { value: 6, label: 'Relative In-Home'},
      { value: 7, label: 'Non-Relative Out-of-Home'},
      { value: 8, label: 'Relative Out-of-Home'},
    ];

    return types;
  }

  var getGenders = function(){
    return [
      { value: 3, label: 'Both'},
      { value: 1, label: 'Boy'},
      { value: 2, label: 'Girl'}
    ]
  }

  var getAges = function(){
    return [
      { value: 1, label: '0-2 years'},
      { value: 2, label: '3-6 years'},
      { value: 3, label: '6-10 years'},
      { value: 4, label: '11-14 years'},
      { value: 5, label: '15-18 years'},
    ]
  }

  return {
    getCounties:          getCounties,
    getCities:            getCities,
    getProviderTypes:     getProviderTypes,
    getGenders:           getGenders,
    getAges:              getAges
  };
});
