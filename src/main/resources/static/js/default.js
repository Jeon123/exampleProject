$(window).on("load", function() {
	if ($('.nav-end').hasClass('no-more-prev') == true) {
		$('.no-more-prev').removeAttr("href");
	} else if ($('.nav-end').hasClass('no-more-next') == true) {
		$('.no-more-next').removeAttr("href");
	}

	$(".aside-button").click(function() {
		if ($('.sideBar').hasClass('is-open') == false) {
			$('.sideBar').addClass('is-open');
			var close = '';
			close += '<svg width="16px" height="16px" viewBox="0 0 16 16">';
			close += '	<rect x="-2.775" y="7.461"';
			close += '		transform="matrix(0.7071 -0.7071 0.7071 0.7071 -3.3139 8.0002)"';
			close += '		width="21.549" height="1.078"></rect>';
			close += '	<rect x="-2.775" y="7.461"';
			close += '		transform="matrix(0.7071 0.7071 -0.7071 0.7071 7.9995 -3.3139)"';
			close += '		width="21.549" height="1.078"></rect>';
			close += '	</svg>';
			$('.aside-button').html(close);
		} else {
			$('.sideBar').removeClass('is-open');
			var open = '';
			open += '<svg width="18px" height="15px" viewBox="0 0 18 14">';
			open += 	'<g>';
			open += 		'<rect width="18" height="1"></rect>';
			open += 		'<rect y="14" width="18" height="1"></rect>';
			open += 		'<rect y="7" width="18" height="1"></rect>';
			open += 	'</g>';
			open += '</svg>';
			$('.aside-button').html(open);
		}
	});
});

$(document).on("submit", "#search", function(event) {
	event.preventDefault();
	if ($("#searchText").val() == "") {
		alert("Please enter your search word.");
	} else {
		$(location).attr('href',"/search/" + $("#searchText").val());
	}
});