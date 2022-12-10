class BaseFilter {
  constructor(){}

  includesTest(index, data, value) {
    return data[inex].includes(value);
  }

  renderFilterIndicator(index, value, indicator, color) {
    $('.color-filter:eq('+index+')').find('input').val(value);
    $('.filter-indicator:eq('+index+')').text(indicator);
    $('.filter-indicator:eq('+index+')').css('background-color', color);
    if (indicator == 'Unfiltered') {
      $('.filter-indicator:eq('+index+')').css('color', 'white');
    } else {
      $('.filter-indicator:eq('+index+')').css('color', 'black');
    }
  }

  columnFilterChange(index, data){
    let value = $('input:hidden:eq('+index+')').val();
    console.log(value);
    if (value == '<-->') {
      return  true;
    } else if (index == 4) {
      console.log(value);
      return includesTest(index+2, data, value);
    } else {
      return (data[index] == value);
    }
  }

  wireFilter(columnNumber, table) {
    $('.select-filter:eq('+columnNumber+')').on( 'change keyUp', function () {
      var result = true;
      var filterValue = $(this).val();
      console.log(columnNumber);
      if (filterValue == '<-->') {
        //filterValue = '';
        while($.fn.dataTable.ext.search.length > 0) {
          $.fn.dataTable.ext.search.pop();
        }

        renderFilterIndicator(columnNumber, filterValue, 'Unfiltered', 'red');

        $.fn.dataTable.ext.search.push(
          function( settings, data, dataIndex ) {
            result &= columnFilterChange(0, data);
            result &= columnFilterChange(1, data);
            result &= columnFilterChange(2, data);
            result &= columnFilterChange(3, data);
            result &= columnFilterChange(4, data);
            return result;
          }
        );

      } else if(columnNumber == 4) {

      $.fn.dataTable.ext.search.push(
          function( settings, data, dataIndex ) {
            return includesTest(columnNumber+2, data, filterValue);
          }
        );

        renderFilterIndicator(columnNumber, filterValue, 'Filtered', 'lime');

      } else {

        $.fn.dataTable.ext.search.push(
          function( settings, data, dataIndex ) {
            return (data[columnNumber] == filterValue);
          }
        );

        renderFilterIndicator(columnNumber, filterValue, 'Filtered', 'lime');

      }
      //table.column(0).orderdata({0, 0});
      table.draw();
    } );
  }
}
