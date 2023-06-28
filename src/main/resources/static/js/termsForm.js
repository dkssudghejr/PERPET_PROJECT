window.onload = function() {
	const checkAll = document.getElementById('chkAll');
	const chks = document.querySelectorAll('input[name="chk"]');
	const chkBoxLength = chks.length;

	checkAll.addEventListener('click', function(event) {
		if (event.target.checked) {
			chks.forEach(function(value) {
				value.checked = true;
			})
		} else {
			chks.forEach(function(value) {
				value.checked = false;
			})
		}
	});

	for (let chk of chks) {
		chk.addEventListener('click', function() {
			let count = 0;
			chks.forEach(function(value) {
				if (value.checked == true) {
					count++;
				}
			})
			if (count !== chkBoxLength) {
				checkAll.checked = false;
			} else {
				checkAll.checked = true;
			}
		})
	}
}

function handleJoin() {
	var chk1 = document.getElementById("chk1");
	var chk2 = document.getElementById("chk2");

	if (!chk1.checked || !chk2.checked) {
		alert("필수 항목 모두 동의해주세요.");
		return;
	} else {
		if (location.href.includes('/members/newm')) {
			location.href = '/members/newm/join';
		} else if (location.href.includes('/members/newc')) {
			location.href = '/members/newc/join';
		}
	}

}


