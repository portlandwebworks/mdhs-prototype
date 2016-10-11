'use strict';

angular.module('mdhs').component('facilityDetailLarge', {
  templateUrl: '/templates/app/components/facility-detail-large',
  bindings: {
    facility: '<'
  },
  controller: function (FacilityService) {
    var controller = this;

    var ageRanges = FacilityService.getAgeRanges();

    /**
     * Returns an object containing the smallest and largest ages supported by
     * the provided array of age enums.
     *
     * @param {array} ageConstants An array of age range enums.
     * @returns {Object} An object with the smallest and largest ages in the set.
     */
    function getAgeRangesFromAgeConstants(ageConstants){
      return ageConstants.map(function(ageConstant){
        return ageRanges[ageConstant];
      }).reduce(function(carry, ages){
        return {
          minimumAge: Math.min(carry.minimumAge, ages.minimumAge),
          maximumAge: Math.max(carry.maximumAge, ages.maximumAge)
        };
      });
    }

    /**
     * Handler for the Call button
     *
     * @param {number} id The facility ID.
     */
    controller.contactFacility = function(id){
      // TODO
    };

    /**
     * Returns an array with n length. Helper function for ng-repeat to loop
     * with a number.
     *
     * @param {number} n The expected length of the array.
     * @returns {Array} An array with n length.
     */
    controller.range = function(n){
      return new Array(n);
    };

    /**
     * Retrieves a human-friendly representation of the genders accepted.
     *
     * @param {String} genderConstant The enum representation of the gender accepted.
     * @returns {String} The human-friendly representation of the gender accepted.
     */
    controller.getGendersAcceptedLabel = function(genderConstant){
      return ({
        'FEMALE': 'Girls',
        'MALE': 'Boys',
        'BOTH': 'Girls, Boys'
      })[genderConstant];
    };

    /**
     * Retrieves the minimum age from an array of age range enums.
     *
     * @param {array} ageConstants An array of age range enums.
     * @returns {number} The minimum age of the set.
     */
    controller.getMinAgeAccepted = function(ageConstants){
      return getAgeRangesFromAgeConstants(ageConstants).minimumAge;
    };

    /**
     * Retrieves the maximum age from an array of age range enums.
     *
     * @param {array} ageConstants An array of age range enums.
     * @returns {number} The maximum age of the set.
     */
    controller.getMaxAgeAccepted = function(ageConstants){
      return getAgeRangesFromAgeConstants(ageConstants).maximumAge;
    };

    /**
     * Retrieves a human-friendly representation of a license type enum.
     *
     * @param {String} licenseConstant The enum representation of the license type.
     * @returns {String} The human-friendly representation of the license type.
     */
    controller.getLicenseLabel = function(licenseConstant){
      return ({
        LICENSED: 'Licensed',
        UNLICENSED: 'Unlicensed'
      })[licenseConstant];
    };

  }
});
