$(document).ready(function() {

    $('#signup-btn').click(async function() {

        try {
            const isValid = await formValidity($('#signup'));
            if (!isValid) {
                return;
            }
        } catch (validationError) {
            errorBox(validationError, "폼 유효성 검사 중 오류가 발생했습니다. 다시 시도해 주세요.");
            return;
        }

        let data = {
            userId: $('#signup-userId').val().trim(),
            password: $('#signup-password').val().trim(),
            nickname: $('#signup-nickname').val().trim()
        };

        sendAjax('/api/user/signup', data)
            .then(function(response) {
                if(response.status) {
                    navigateToPageWithAlert('', response.message);
                }else {
                    errorBox(response.message);
                }
            })
            .catch(function(ajaxError) {
                errorBox(ajaxError, "로그인 과정에서 오류가 발생했습니다. 다시 시도해 주세요.");
            });

    });
});