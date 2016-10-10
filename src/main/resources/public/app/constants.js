angular.module('mdhs')
  .constant('allowedGender', {
    MALE: 'Boys',
    FEMALE: 'Girls',
    BOTH: 'Girls, Boys'
  })
  .constant('ageRange', {
    INFANT: {
      minimumAge: 0,
      maximumAge: 2
    },
    TODDLER: {
      minimumAge: 3,
      maximumAge: 6
    },
    CHILD: {
      minimumAge: 7,
      maximumAge: 10
    },
    PRETEEN: {
      minimumAge: 11,
      maximumAge: 14
    },
    TEEN: {
      minimumAge: 15,
      maximumAge: 18
    }
  })
  .constant('licenseType', {
    LICENSED: 'Licensed',
    UNLICENSED: 'Unlicensed'
  })
  // In an ideal world, these would belong to a translations file.
  .constant('homeGreeting', 'Welcome')
  .constant('homeMessage', 'Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Proin eget tortor risus. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sapien massa, convallis a pellentesque nec, egestas non nisi. Quisque velit nisi, pretium ut lacinia in, elementum id enim. Cras ultricies ligula sed magna dictum porta. Pellentesque in ipsum id orci porta dapibus. Curabitur aliquet quam id dui posuere blandit. Quisque velit nisi, pretium ut lacinia in, elementum id enim.');
