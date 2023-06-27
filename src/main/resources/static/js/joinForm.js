var emailDuplicated = false;
var registnumDuplicated = false;

function checkEmailDuplicate() {
	var email = document.getElementById("email").value;
	var error0 = document.getElementById("error0");
	var error1 = document.getElementById("error1");
	var error2 = document.getElementById("error2");
	var error3 = document.getElementById("error3");
	var error4 = document.getElementById("error4");
	var error5 = document.getElementById("error5");

	if (email.trim() === "") {
		error0.style.display = "block";
		return;
	} else {
		error0.style.display = "none";
	}

	// 서버로 이메일 중복 확인 요청을 보냄
	$.ajax({
		url: "/members/emailChk",
		type: "GET",
		data: { email: email },
		success: function(response) {
			// 중복 여부에 따라 처리 로직 작성
			if (response.duplicate) {
				error1.style.display = "block";
				error4.style.display = "none";

				/*alert("중복된 이메일입니다.");*/
			} else {
				error1.style.display = "none";
				error2.style.display = "block";
				error4.style.display = "none";
				/*alert("사용 가능한 이메일입니다.");*/
				emailDuplicated = true;
			}
		},
		error: function(xhr, status, error) {
			// 에러 처리 로직 작성
			alert("이메일 중복 확인 중 오류가 발생했습니다.");
		}
	});
}

function validateForm() {
	error2.style.display = "none";
	if (!emailDuplicated) {
		error4.style.display = "block";
		return false;
	} else if (error0.style.display === "block" || error1.style.display === "block" || error3.style.display === "block" || error4.style.display === "block" || error5.style.display === "block" || error6.style.display === "block" || error6.style.display === "block") {
		return false;
	}
	return true;



}

function checkPw() {
	var pw = document.getElementById("pw").value;
	var pw2 = document.getElementById("pw2").value;

	if (pw !== pw2) {
		error3.style.display = "block";
		return false;
	}

	error3.style.display = "none";
}

function checkPwValidity() {
	var pw = document.getElementById("pw").value;
	var pwRegex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,12}$/;

	if (!pwRegex.test(pw)) {
		error5.style.display = "block";
	} else {
		error5.style.display = "none";
	}

}

function checkTel() {
	var error6 = document.getElementById("error6");
	var tel = document.getElementById("tel").value;
	var telRegex = /^\d{2,3}-\d{3,4}-\d{4}$/;

	if (!telRegex.test(tel)) {
		error6.style.display = "block";
	} else {
		error6.style.display = "none";
	}
}

function checkRegistnumDuplicate() {
	var registnum = document.getElementById("registnum").value;
	var error7 = document.getElementById("error7");
	var error8 = document.getElementById("error8");
	var registnumRegex = /^\d{3}-\d{2}-\d{5}$/;

	if (!registnumRegex.test(registnum)) {
		error8.style.display = "block";
	} else {
		error8.style.display = "none";
		$.ajax({
			url: "/members/registnumChk",
			type: "GET",
			data: { registnum: registnum },
			success: function(response) {
				// 중복 여부에 따라 처리 로직 작성
				if (response.duplicate) {
					error7.style.display = "block";

				} else {
					error7.style.display = "none";
					registnumDuplicated = true;
				}
			},
			error: function(xhr, status, error) {
				// 에러 처리 로직 작성
				alert("사업자 등록 번호 확인 중 오류가 발생했습니다.");
			}
		});
	}






}
