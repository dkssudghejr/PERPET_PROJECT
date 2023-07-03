function bindDomEvent() {
	$(".custom-file-input").on("change", function() {

		//분할한 마지막 부분을 선택하여 파일이름으로 추출
		var fileName = $(this).val().split("\\").pop();  //이미지 파일명

		//파일 이름에서 마지막 점의 위치를 찾아서 확장자를 추출함
		var fileExt = fileName.substring(fileName.lastIndexOf(".") + 1); // 확장자 추출
		fileExt = fileExt.toLowerCase(); //소문자 변환

		if (fileExt != "jpg" && fileExt != "jpeg" && fileExt != "gif" && fileExt != "png" && fileExt != "bmp") {
			alert("이미지 파일만 등록이 가능합니다.");
			return;
		}

		$(this).siblings(".custom-file-label").html(fileName);
	});
}

var cateChange = function(value) {
	console.log("값변경 테스트 : " + value);
	$("#cate").val(value);
}

var onlineBuyChange = function(value) {
	console.log("값변경 테스트 : " + value);
	$("#onlineBuy").val(value);
}