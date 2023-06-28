function approveCompany() {
	var csrfToken = document.querySelector('input[name="_csrf"]').value;
	var email = document.getElementById("companyEmail").textContent;

    fetch('/admin/approve/' + email, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        }
    })
    .then(response => response.text())
    .then(message => alert(message))
    .catch(error => console.error(error));
}

function deleteCompany() {
	var csrfToken = document.querySelector('input[name="_csrf"]').value;
	var email = document.getElementById("companyEmail").textContent;

    fetch('/admin/dltCompany/' + email, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        }
    })
    .then(response => response.text())
    .then(message => alert(message))
    .catch(error => console.error(error));
}
