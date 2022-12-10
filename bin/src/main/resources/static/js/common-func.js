function renderColumn(data) {
    var x = '';
    x += "<span class='box'><span class='color-id' ";
    x += "style='background-color:"+data.color+";'></span>";
    x += data.name+"</span>";
    return x;
}
function renderSelectFilter(data) {
  var opt = '';
  opt += "<option ";
  opt += "value='"+data.name+"'>";
  opt += renderColumn(data);
  opt += "</option>";
  return opt;
}
