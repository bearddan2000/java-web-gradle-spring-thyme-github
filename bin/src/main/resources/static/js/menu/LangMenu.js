function body(id, data, parent) {
  var menuItem = '';
  menuItem += '<div class="accordion-item">';
  menuItem += '<h2 class="accordion-header" id="heading'+id+'">';
  menuItem += '<button';
  menuItem += ' class="accordion-button"';
  menuItem += ' type="button"';
  menuItem += ' data-mdb-toggle="collapse"';
  menuItem += ' data-mdb-target="#collapse'+id+'"';
  menuItem += ' data-mdb-parent="#'+parent+'"';
  menuItem += ' aria-expanded="false"';
  menuItem += ' aria-controls="collapse'+id+'">';
  menuItem += renderColumn(data);
  menuItem += '<span>&nbsp;['+data.count+']</span>';
  menuItem += '</button>';
  menuItem += '</h2>';
  return menuItem;
}
function header(id, data, parent) {
    var menuItem = '';
    if (data.count == 0) {
      return menuItem;
    }
    menuItem += body(id, data, parent+'-accordion');

    $.each(data.menu, function (k, v) {
      if (v.count > 0) {
        menuItem += '<div id="collapse'+id+'"';
        menuItem += ' class="accordion-collapse collapse show"';
        menuItem += ' aria-labelledby="heading'+id+'">';
        menuItem += '<div class="accordion-body">';
        menuItem += body(id+"-"+k, v, "heading"+id) + '</div>';
        menuItem += '</div>';
        menuItem += '</div>';
      }
    });

    menuItem += '</div>';

  //  menuItem += "<div id='collapse"+id+"' class='accordion-collapse collapse' "
    return menuItem;
}

class LangMenu{
  constructor(endpoint, parent) {
    $.ajax(endpoint, {
      success: function(data) {
  			var menuItem = '';
  			$.each(data, function (index, val) {
          menuItem += header("-"+index, val);
  			});
  			$('#'+parent+'-accordion').html(menuItem);
  		},
      error: function() {
        console.log("Error getting endpoint " + endpoint);
      }
  	});
  }

}
