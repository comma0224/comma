$(document).ready(function() {

    $('#signup-btn').click(function() {
        let form = $('#signup');

        formValidity(form)
            .then(isValid => {
                if (isValid) {
                    sendAjax('/signup', {
                        userId: $('#login-userId').val().trim(),
                        password: $('#login-password').val().trim()
                    })
                        .then(response => response.status?
                            navigateToPageWithAlert('home', response.message)
                            :errorBox(response.message))
                        .catch(error => {
                            errorBox(error.message);
                        });
                }else {

                }
            })
            .catch(error => {
                console.error("Error validating form:", error);
            });
    });
});