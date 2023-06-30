$(document).ready(function(){
	var errorMessage = /*[[${errorMessage}]]*/ "";
	if(errorMessage != null){
		alert(errorMessage);
	}
	bindDomEvent();
})

function bindDomEvent(){
	$(".custom-file-input").on("change", function(){
		var fileName = $(this).var().split("\\").pop();
		
		var fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
		fileExt = fileExt.toLowerCase();
		
		if(fileExt != "jpg" && fileExt != "jpeg" && fileExt != "gif" && fileExt != "png" && fileExt != "bmp"){
			alert("이미지 파일만 등록이 가능합니다.");
			return;
		}
		
		$(this).siblings(".custom-file-label").html(fileName);
	});
}
