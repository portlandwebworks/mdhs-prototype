'use strict';

angular.module('mdhs').component('home', {
  templateUrl: '/templates/app/components/home',
  controller: function (homeMessage, homeGreeting) {
    var controller = this;

    var imagesPath = '/app/assets/home'
    var images = [
      '/bg01.jpg',
      '/bg02.jpg',
      '/bg03.jpg',
      '/bg04.jpg'
    ];

    controller.greeting = homeGreeting;
    controller.message = homeMessage;
    controller.image = imagesPath + images[Math.floor(Math.random() * images.length)];

  }
});
