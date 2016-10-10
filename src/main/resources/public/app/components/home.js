'use strict';

angular.module('mdhs').component('home', {
  templateUrl: '/templates/app/components/home',
  controller: function () {
    var controller = this;

    var imagesPath = '/app/assets/home'
    var images = [
      '/bg01.jpg',
      '/bg02.jpg',
      '/bg03.jpg',
      '/bg04.jpg'
    ];

    controller.greeting = 'Welcome';
    controller.message = 'Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Proin eget tortor risus. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sapien massa, convallis a pellentesque nec, egestas non nisi. Quisque velit nisi, pretium ut lacinia in, elementum id enim. Cras ultricies ligula sed magna dictum porta. Pellentesque in ipsum id orci porta dapibus. Curabitur aliquet quam id dui posuere blandit. Quisque velit nisi, pretium ut lacinia in, elementum id enim.';
    controller.image = imagesPath + images[Math.floor(Math.random() * images.length)];

  }
});
