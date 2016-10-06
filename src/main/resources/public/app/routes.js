angular.module('mdhs').config(function ($routeProvider) {
  $routeProvider
    .when('/', {
      template: '<home></home>'
    })
    .when('/search', {
      template: '<search></search>'
    })
    .otherwise({
      redirectTo: '/'
    });
});
