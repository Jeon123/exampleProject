	function page(pageNum) {
  		location.href = "/search/"+ $("#searchText").val() +"?pageNum=" + pageNum;
  	}
  	
  	function nextPage(pageNum) {
  		var num = parseInt(pageNum) + 1
  		location.href = "/search/"+ $("#searchText").val() +"?pageNum=" + num;
  	}
  	
  	function prevPage(pageNum) {
  		var num = parseInt(pageNum) - 1
  		location.href = "/search/"+ $("#searchText").val() +"?pageNum=" + num;
  	}
  	
  	function getPost(freeId) {
  		location.href = "/" + freeId;
  	}
  	
  	$(document).on("submit", "#search", function(event){
  		event.preventDefault();
  		if($("#searchText").val()=="") {
  			alert("검색어를 입력해 주세요.");
  		}else {
	  		location.href = "/search/" + $("#searchText").val();
  		}
  	});