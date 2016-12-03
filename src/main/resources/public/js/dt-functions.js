/**
 * Format data to a list of links for actions to be displayed in Datatables
 * @param data {code,action,clazz,icon,id}
 * @param type
 * @param full
 */
function toActions(data, type, full) {
	var html = '';
	if (data != null) {	
		html += "<div class=\"btn-group\">";
		for(i = 0; i < data.length; i++) {
			var actionData = data[i];
			if(actionData.action == 'edit') {
				html += '<a class=\"btn default btn-xs ' + actionData.style + ' tooltips\" '
				+ 'data-placement=\"bottom\" data-original-title=\"' + actionData.altText + '\" '
				+ 'href=\"/' + actionData.base + '/' + actionData.action+ '/' + actionData.objectId + '/\">'
				+ '<i class=\"' + actionData.icon + '\"></i></a>';
			} else  if(actionData.action == 'delete') {
				html += '<a class=\"btn default btn-xs ' + actionData.style + ' tooltips\" '
				+ 'data-placement=\"bottom\" data-original-title=\"' + actionData.altText + '\" >'
				+ '<i class=\"' + actionData.icon 
				+ ' object\"></i></a>' + '<input type=\"hidden\" value=\"/' 
				+ actionData.base + '/' + actionData.action + '/' + actionData.objectId + '/\">';

			}
		}
		html += "</div>";
	}
	return html;
}

/**
 * Format numbers to percentage for DataTables
 * @param data
 * @param type
 * @param full
 * @returns
 */
function toPercentage(data, type, full) {
	data = data * 100;
	data = data.toFixed(2);
	return data.toString() + ' %';
}

/**
 * to read only view
 * @param data
 * @param type
 * @param full
 */
function toLink(data, type, full){
	var html = '';
	//locaiton name is not unique , find event by batch id and get location code
	if(data != null){
		if(data.id != undefined){
			html += '<a href=\"/' + data.base + '/' + data.url  + '/' + data.id + '/\">'+ data.data + '</a>';
		}else if(data.data != undefined){
			return data.data;
		}
		
	}
	return html;
}

/**
 * to convert from database date to local date depends on the timezone
 * @param data
 * @param type
 * @param full
 */
function toLocalDate(data, type,full){
	var localDate = new Date();
	var offset = localDate.getTimezoneOffset() / 60;
	var newDate = new Date(data);
	newDate.setHours(newDate.getHours() - offset);
	return moment(newDate).format('YYYY-MM-DD HH:mm:ss');
}
