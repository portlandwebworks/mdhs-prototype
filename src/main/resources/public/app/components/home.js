'use strict';

angular.module('mdhs').component('home', {
  templateUrl: '/templates/app/components/home',
  controller: function () {
    var controller = this;

    controller.message = 'Hello MDHS!';
  }
});
