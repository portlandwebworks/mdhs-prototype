'use strict';

angular.module('mdhs').directive('multiselect', function ($timeout) {
  return {
    restrict: 'E',
    templateUrl: '/templates/app/directives/multiselect/index',
    scope: {
      options: '=',
      title: '='
    },
    transclude: true,
    link: function($scope){
      $timeout(applyMultiselect,0);
      function applyMultiselect(){
        angular.element('.multiselect').multiselect({
          buttonText: $scope.title,
          includeSelectAllOption: false,
          buttonWidth: '100%'
        });

      }
    }
  };
});
