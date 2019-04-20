var global_commentId;

$(window).on("load", function(){
	$('.comment-content').disabled=true;
	
	CKEDITOR.replace('content', {readOnly:true});
	
	CKEDITOR.on('instanceReady', function (ev) {
	   ev.editor.document.on('drop', function (ev) {
	      ev.data.preventDefault(true);
	   });
	});
	
	showComments();
});

function showComments() {
	var freeId = $('.comment-boardId').val();
	var loginUser = $('.comment-writer').val();
	
	$.ajax({
		url: '/commentList/' + freeId,
		type: 'GET',
		datetype: 'json',
		success: function(data){
			var commentList ='';
			data.forEach(function(comment_data){
				var diffDays = getdate(new Date(), new Date(comment_data.createTime));
				
				commentList += '<div class="comment">';
				commentList += '<div class="thumbnail">';
				commentList += '<img class="" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">';
				commentList += '</div><!-- /thumbnail -->';
				
				commentList += '<div class="panel">';
				commentList += '<div class="panel-heading">';
				commentList += '<span class="text-muted"><strong>'+ comment_data.writer +'</strong> commented ' + diffDays + ' days ago</span>';
				if(comment_data.writer==loginUser) {
					commentList += '<div class="iconOut_' + comment_data.commentId + '">';
					
					commentList += '<div class="panel-icon resetarea">';
					commentList += '<a class="resetarea" href="javascript:cmtUpdateForm('+ comment_data.commentId + ')">';
					commentList += '<span class="resetarea" uk-icon="icon: pencil"></span>';
					commentList += '<span class="tooltiptext">Update</span>';
					commentList += '</a>';
					commentList += '</div><!-- /panel-icon -->';
					
					commentList += '<div class="panel-icon">';
					commentList += '<a href="/commentDelete/' + freeId + '/' + comment_data.commentId + '" uk-icon="trash">';
					commentList += '<span class="tooltiptext">Delete</span>';
					commentList += '</a>';
					commentList += '</div><!-- /panel-icon -->';
					
					commentList += '</div><!-- /iconOut_ -->';
				}
				commentList += '</div>';
				
				commentList += '<div class="panel-body">';
				commentList += '<textarea readonly="readonly" disabled="disabled" class="cmt_UpdateForm_'+ comment_data.commentId + ' resetarea" id="cmt_Update_'+ comment_data.commentId + '" style="width: 100%; border: 0; resize: none;">'+ comment_data.content +'</textarea>';
				commentList += '<input class="cmt_Update_' + comment_data.commentId + ' resetarea" onclick="javascript:cmtUpdate('+ freeId + ',' + comment_data.commentId +')" type="button" style="float: right; display: none;" value="Update">';
				commentList += '<input class="cmt_Update_' + comment_data.commentId + ' resetarea" onclick="javascript:cmtUpdate('+ freeId + ',' + comment_data.commentId +')" type="button" style="float: right; display: none;" value="Cancel">';
				commentList += '</div><!-- /panel-body -->';
				
				commentList += '</div><!-- /panel panel-default -->';
				commentList += '</div><!-- /row -->';
			});
			$('.comments').html(commentList);
		},
		error: function(){
			alert("Error");
		}
	});
}



$(document).on("submit", ".comment-insert", function(event){
	event.preventDefault();
	
	if($('.comment-writer').val()==null) {
		alert("Login plz.");
		location.href="/login";
	} else {
  		commentInsert();
	}
});

$(document).on("click", ".comment-content", function(){
	if($('.comment-writer').val()==null) {
		alert("Login plz!!!!");
	}
})

function commentInsert() {
	var comment = {
		boardId : $('.comment-boardId').val(),
		writer : $('.comment-writer').val(),
		content : $('.comment-content').val()
	}
	$.ajax({
		url: '/commentInsert',
		type: 'POST',
		dataType: 'json',
		contentType : "application/json; charset=UTF-8",
		data: JSON.stringify(comment),
		success: function(data){
			showComments();
		},
		error: function(){
			alert("Error");
			showComments();
			}
		});
	}
   
function freeBoardDelete(freeId) {
	$(location).attr("href", "/boardDelete/" + freeId)
}

function freeBoardUpdate(freeId) {
	$(location).attr("href", "/boardUpdate/" + freeId)
}

function getdate(nowdate, setdate) {
	var diffDate_1 = new Date(nowdate.getFullYear(), nowdate.getMonth()+1, nowdate.getDate());
    var diffDate_2 = new Date(setdate.getFullYear(), setdate.getMonth()+1, setdate.getDate());

	var diff = Math.abs(diffDate_1 - diffDate_2);
	var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
	 
	return Math.ceil(diff/currDay);
}

function cmtUpdateForm(commentId) {
	var activeName = $('.cmt_UpdateForm_' + commentId);
	var updateName = $('.cmt_Update_' + commentId);
	var screenOutIcon = $('.iconOut_' + commentId);
	
	if(activeName.attr('readonly')) {
		activeName.attr('readonly', false);
		activeName.attr('disabled', false);
		activeName.css('border', 1);
		updateName.css('display', 'block');
		screenOutIcon.addClass('screen_out');
		activeName.focus();
	}
}

$('html').click(function(e) {
	if(!$(e.target).hasClass("resetarea")) {
  		showComments();
	}
}); 

function cmtUpdate(boardId, commentId) {
	var upComment = {
			boardId : boardId,
			commentId : commentId,
			writer : $('.comment-writer').val(),
			content : $('.cmt_UpdateForm_' + commentId).val()
		}
		$.ajax({
			url: '/commentUpdate/' + boardId + '/' + commentId,
			type: 'POST',
			dataType: 'json',
			contentType : "application/json; charset=UTF-8",
			data: JSON.stringify(upComment),
			success: function(data){
				console.log(data);
				showComments();
			},
			error: function(){
				alert("Error");
				showComments();
			}
		});
}