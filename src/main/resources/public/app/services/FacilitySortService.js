angular.module('mdhs').service('FacilitySortService', function ($rootScope) {
  var service = this;

  var sortOrders = {
    DISTNACE: {sortBy: 'withinDistance', sortDir: 'ASC', label: 'Distance'},
    LOCATION_NAME_ASCENDING: {sortBy: 'name', sortDir: 'ASC', label: 'Provider Name'},
    RATING_DESCENDING: {sortBy: 'qualityRating', sortDir: 'DESC', label: 'Rating'},
    SIZE_ASCENDING: {sortBy: 'capacity', sortDir: 'DESC', label: 'Size, largest to smallest'},
    SIZE_DESCENDING: {sortBy: 'capacity', sortDir: 'ASC', label: 'Size, smallest to largest'}
  };
  
  var _sortBy = sortOrders.LOCATION_NAME_ASCENDING;
  var _hasSlots = false;
  var _noHistory = false;
  var _licensed = false;
  
  var updateResults = function(){
	  $rootScope.$broadcast('event:facilities:find');
  };
  
  var sortSettings = {
    sortBy: function(sort){
		if(arguments.length > 0){
			_sortBy = sort;
			updateResults();
		}else{
			return _sortBy;
		}
	}
  };

  var filterSettings = {
    hasSlots: function(hasSlots) {
		if(arguments.length > 0){
			_hasSlots = hasSlots;
			updateResults();
		}else{
			return _hasSlots;
		}
	},
    hasNoHistory: function(noHistory){
		if(arguments.length > 0){
			_noHistory = noHistory;
			updateResults();
		}else{
			return _noHistory;
		}
	},
    hasLicense: function(licensed){
		if(arguments.length > 0){
			 _licensed = licensed;
			updateResults();
		}else{
			return _licensed;
		}
	}
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
