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
  });
