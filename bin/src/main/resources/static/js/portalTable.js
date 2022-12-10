$(document).ready( function () {

	var table = $('#dataTable').DataTable({
    // hides search box
    // searching: false,
			"sAjaxSource": "/all",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
          {
            "mData": "lang",
            render: function (data, type) {
              return renderColumn(data);
            }
          },
          {
            "mData": "platform",
            render: function (data, type) {
              return renderColumn(data);
            }
          },
          {
            "mData": "build",
            render: function (data, type) {
              return renderColumn(data);
            }
          },
          {
            "mData": "tech",
            render: function (data, type) {
              return renderColumn(data);
            }
          },
			    { "mData": "name"},
			    { "mData": "description"},
    			{
            "mData": "keywords",
            render: function (data, type) {
              var opt = '';
        			$.each(data, function (index, val) {
                opt += val.name + ',';
								if (index%3 == 0 && index != 0) {
									opt += '\n';
								}
        			});
              return opt;
            }
          }
			]
	 });

   new LangMenu('/menu/lang', 'lang');

	 new TechMenu('/menu/tech', 'tech');

   new ColorFilter(table, '/filter/lang', 0);

   new ColorFilter(table, '/filter/platform', 1);

   new ColorFilter(table, '/filter/build', 2);

	 new ColorFilter(table, '/filter/tech', 3);

   new KeywordsFilter(table, '/filter/keywords', 4);
});
