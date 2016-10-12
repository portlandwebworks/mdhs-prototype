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

    controller.toggleFilters = function(){
      controller.filterUiSettings.isFiltersPanelOpen = !controller.filterUiSettings.isFiltersPanelOpen;
    };

  }
});
