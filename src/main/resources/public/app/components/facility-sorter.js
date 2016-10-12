'use strict';

angular.module('mdhs').component('facilitySorter', {
  templateUrl: '/templates/app/components/facility-sorter',
  controller: function(FacilitySortService){
    var controller = this;

    controller.sortSettings = FacilitySortService.getSortSettings();

    controller.filterSettings = FacilitySortService.getFilterSettings();

    controller.sortOrders = FacilitySortService.getSortOrders();

    controller.filterUiSettings = {
      isFiltersPanelOpen: false
    };

    // Presentational and component-specific. Stays here.
    controller.sortOrderLabels = {
      LOCATION_NAME_ASCENDING: 'Provider Name',
      RATING_DESCENDING: 'Rating',
      SIZE_DESCENDING: 'Size (large - small)',
      SIZE_ASCENDING: 'Size (small - large)'
    };

    controller.toggleFilters = function(){
      controller.filterUiSettings.isFiltersPanelOpen = !controller.filterUiSettings.isFiltersPanelOpen;
    };

  }
});
