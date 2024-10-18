$(document).ready(function() {

    $('#signup-btn').click(async function() {

        try {
            const isValid = await formValidity($('#signup'));
            if (!isValid) {
                errorBox("폼 유효성 검사에 실패했습니다. 입력 내용을 확인해 주세요.");
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

        try {
            const response = await sendAjax('/api/user/signup', data);
            response.status ? alertBox(response.message) : errorBox(response.message);
        } catch (ajaxError) {
            errorBox(ajaxError, "로그인 과정에서 오류가 발생했습니다. 다시 시도해 주세요.");
        }
    });
});