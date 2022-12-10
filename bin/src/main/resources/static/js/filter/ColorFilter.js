class ColorFilter extends BaseFilter{
  constructor(table, endpoint, columnNumber) {
    super();
    $.ajax(endpoint, {
      success: function(data) {
        var selectFilter = '<option value="<-->"><--></option>';
  			$.each(data, function (index, val) {
          if(val.count > 0)
            selectFilter += renderSelectFilter(val);
  			});
  			$('.select-filter:eq('+columnNumber+')').html(selectFilter);
        // BaseFilter.renderFilterIndicator(columnNumber, '<-->', 'Unfiltered', 'red');
  		},
      error: function() {
        console.log("Error getting endpoint " + endpoint);
      }
  	});
    super.wireFilter(columnNumber, table);
  }
}
