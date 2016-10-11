'use strict';

angular.module('mdhs').component('facilityListing', {
  templateUrl: '/templates/app/components/facility-listing',
  controller: function (FacilityService, FacilitySortService) {
    var controller = this;

    var sortSettings = FacilitySortService.getSortSettings();

    var filterSettings = FacilitySortService.getFilterSettings();

    var sortOrders = FacilitySortService.getSortOrders();

    var allowedGender = FacilityService.getAllowedGenders();

    var ageRanges = FacilityService.getAgeRanges();

    var licenseTypes = FacilityService.getLicenseTypes();

    controller.facilities = FacilityService.getCurrentFacilities();

    /**
     * Returns an object containing the smallest and largest ages supported by
     * the provided array of age enums.
     *
     * @param {string} ageConstants An array of age range enums.
     * @returns {Object} An object with the smallest and largest ages in the set.
     */
    function getageRangesFromAgeConstants(ageConstants){
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
      return getageRangesFromAgeConstants(ageConstants).minimumAge;
    };

    /**
     * Retrieves the maximum age from an array of age range enums.
     *
     * @param {array} ageConstants An array of age range enums.
     * @returns {number} The maximum age of the set.
     */
    controller.getMaxAgeAccepted = function(ageConstants){
      return getageRangesFromAgeConstants(ageConstants).maximumAge;
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

    /**
     * Returns the property for sorting depending on the sort order chosen.
     *
     * @param {Object} facility A facility result instance.
     * @returns {*} Returns a value that will be used to by orderBy.
     */
    controller.facilitySorter = function(facility){
      switch(sortSettings.sortBy){
        case sortOrders.LOCATION_NAME_ASCENDING:
          return facility.facility.name;
          break;
        case sortOrders.RATING_DESCENDING:
          return facility.facility.qualityRating;
          break;
        case sortOrders.SIZE_ASCENDING:
          return facility.facility.capacity;
          break;
        case sortOrders.SIZE_DESCENDING:
          return facility.facility.capacity;
          break;
        default:
          return facility.facility.name;
      }
    };

    /**
     * Determines if the sorting is to be reversed based on the sort chosen.
     *
     * @returns {boolean} True if the sort is to be reversed.
     */
    controller.reverseBySortType = function(){
      return [
        sortOrders.RATING_DESCENDING,
        sortOrders.SIZE_DESCENDING
      ].indexOf(sortSettings.sortBy) > -1;
    };

    /**
     * Filters the facilities according to the availability of openings
     *
     * @param {Object} facility A facility result instance.
     *
     * @returns {boolean} True if either the filter is off or if the facility
     * has openings.
     */
    controller.slotsFilter = function(facility){
      var slotsFilterOff = !filterSettings.hasSlots;
      var facilityHasSlots = facility.facility.openings > 0;
      return slotsFilterOff || facilityHasSlots;
    };

    /**
     * Filters the facilities according to acceptance of convictions.
     *
     * @param {Object} facility A facility result instance..
     *
     * @returns {boolean} True if either the filter is off or if the facility
     * accepts only children without history.
     */
    controller.noHistoryFilter = function(facility){
      var noHistoryFilterOff = !filterSettings.hasNoHistory;
      var facilityHasNoHistory = !facility.facility.acceptsConvictions;
      return noHistoryFilterOff || facilityHasNoHistory;
    };

    /**
     * Filters the facilities according to license status.
     *
     * @param {Object} facility A facility result instance..
     *
     * @returns {boolean} True if either the filter is off or if the facility
     * is licensed.
     */
    controller.licenseFilter = function(facility){
      var licenseFilterOff = !filterSettings.hasLicense;
      var facilityHasLicense = facility.facility.licenseTypes === licenseTypes.LICENSED;
      return licenseFilterOff || facilityHasLicense;
    };

  }
});
