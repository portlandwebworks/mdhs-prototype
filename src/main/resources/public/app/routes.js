angular.module('mdhs').config(function ($routeProvider) {
  $routeProvider
    .when('/', {
      template: '<home></home>'
    })
    .otherwise({
      redirectTo: '/'
    });
});
