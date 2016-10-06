angular.module('mdhs').factory('SearchOptionsFactory', function searchOptionsFactory($http) {
  //TODO: switch these out with calls to backend service

  var getCounties = function(){
    //return $http.get('/api/counties').then(function (response) {
    //  return response.data;
    //});
    return [
      'Chunky',
      'Booneville',
      'Alligator'
    ]
  }

  var getCities = function(){
    //return $http.get('/api/cities').then(function (response) {
    //  return response.data;
    //});

    return [
      'Jasper',
      'Granada',
      'Jones'
    ]
  }

  var getProviderTypes = function(){
    //return $http.get('/api/provider-types').then(function (response) {
    //  return response.data;
    //});

    var types = [
      { id: 1, label: 'Test'},
      { id: 2, label: 'Slot Contractor'},
      { id: 3, label: 'Group Home'},
      { id: 4, label: 'Center'},
      { id: 5, label: 'Non-Relative In-Home'},
      { id: 6, label: 'Relative In-Home'},
      { id: 7, label: 'Non-Relative Out-of-Home'},
      { id: 8, label: 'Relative Out-of-Home'},
    ];

    return types;
  }
  return {
    getCounties:      getCounties,
    getCities:        getCities,
    getProviderTypes: getProviderTypes
  };
});
