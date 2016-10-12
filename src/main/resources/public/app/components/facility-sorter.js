'use strict';

angular.module('mdhs').component('facilitySorter', {
  templateUrl: '/templates/app/components/facility-sorter',
  controller: function(FacilitySortService){
    var controller = this;

    controller.sortSettings = FacilitySortService.getSortSettings();

    controller.filterSettings = FacilitySortService.getFilterSettings();

    controller.sortOrders = FacilitySortService.getSortOrders();

    controller.filterSettings = {
      isFiltersOpen: false
    };

    // Presentational and component-specific. Stays here.
    controller.sortOrderLabels = {
      LOCATION_NAME_ASCENDING: 'Location Name',
      RATING_DESCENDING: 'Rating',
      SIZE_DESCENDING: 'Size, largest to smallest',
      SIZE_ASCENDING: 'Size, smallest to largest'
    };

    controller.toggleFilters = function(){
      controller.filterSettings.isFiltersOpen = !controller.filterSettings.isFiltersOpen
    }

  }
});
