angular.module('mdhs').service('FacilitySortService', function ($http, $q) {
  var service = this;

  var sortOrders = {
    LOCATION_NAME_ASCENDING: 'SORT_LOCATION_NAME_ASCENDING',
    RATING_DESCENDING: 'SORT_RATING_DESCENDING',
    SIZE_ASCENDING: 'SORT_SIZE_ASCENDING',
    SIZE_DESCENDING: 'SORT_SIZE_DESCENDING'
  };

  var sortSettings = {
    sortBy: sortOrders.LOCATION_NAME_ASCENDING
  };

  var filterSettings = {
    hasSlots: false,
    hasNoHistory: false,
    hasLicense: false
  };

  service.getSortSettings = function(){
    return sortSettings;
  };

  service.getFilterSettings = function(){
    return filterSettings;
  };

  service.getSortOrders = function(){
    return sortOrders;
  };

});
