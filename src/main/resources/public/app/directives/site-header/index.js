'use strict';

angular.module('mdhs').directive('siteHeader', function () {
  return {
    restrict: 'C',
    templateUrl: '/templates/app/directives/site-header/index',
    controller: function($scope, messagesFactory, Account){
      $scope.messageCount = null;
      $scope.user = null;

      messagesFactory.getMessages().then(function(messages){
        $scope.messageCount = messages.length;
      });

      Account.getUserAccount().then(function(account){
        $scope.user = account.firstName;
      });

    }
  };
});
