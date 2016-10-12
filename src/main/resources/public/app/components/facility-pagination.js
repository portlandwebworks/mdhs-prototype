'use strict';

angular.module('mdhs').component('facilityPagination', {
  templateUrl: '/templates/app/components/facility-pagination',
  controller: function (FacilityService) {
    var controller = this;
    var paginationSettings = FacilityService.getPaginationSettings();

    var pagesToLookAhead = 3;
    var pagesToLookBehind = 3;

    controller.paginationSettings = paginationSettings;

    /**
     * Generates a page range.
     *
     * @returns {array} An array of numbers representing zero-indexed page
     * numbers.
     */
    controller.generatePageRange = function(){
      // If we have no pages, then no pagination.
      if(!paginationSettings.totalPages) [];

      // Everything is zero-based.

      var currentPage = paginationSettings.page;

      var firstPage = 0;

      // Because the returned value is actually a count, not an index.
      var lastPage = paginationSettings.totalPages - 1;

      var calculatedFirstPage = currentPage - pagesToLookBehind;
      var calculatedLastPage = currentPage + pagesToLookAhead;

      var actualFirstPage = calculatedFirstPage < firstPage ? firstPage : calculatedFirstPage;
      var actualLastPage = calculatedLastPage > lastPage ? lastPage : calculatedLastPage;

      // Compensate for including the current page.
      var pageCount = (actualLastPage - actualFirstPage) + 1;

      var pageRange = Array.apply(null, Array(pageCount)).map(function (element, index) {
        return index + actualFirstPage;
      });

      return pageRange;
    };

    /**
     * Navigate to a given page.
     *
     * @param {number} page
     */
    controller.goToPage = function(page){
      FacilityService.getPage(page);
    };

  }
});
