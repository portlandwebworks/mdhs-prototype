'use strict';

angular.module('mdhs').component('facilityListing', {
  templateUrl: '/templates/app/components/facility-listing',
  controller: function (FacilityService, FacilitySortService) {
    var controller = this;

    var sortSettings = FacilitySortService.getSortSettings();

    var filterSettings = FacilitySortService.getFilterSettings();

    var sortOrders = FacilitySortService.getSortOrders();

    var licenseTypes = FacilityService.getLicenseTypes();

    controller.facilities = FacilityService.getCurrentFacilities();

    /**
     * Handler for the Call button
     *
     * @param {number} id The facility ID.
     */
    controller.contactFacility = function(id){
      // TODO
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
