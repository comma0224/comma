$(document).ready(function() {
    // .click() 메소드 사용
    $('.oauth-kakao').click(function() {
        alert("카카오 로그인(구현 예정)");
    });

    $('.oauth-naver').click(function() {
        alert("네이버 로그인(구현 예정)");
    });

    $('#login-btn').click(async function() {

        try {
            const isValid = await formValidity($('#login'));
            if (!isValid) {
                errorBox("폼 유효성 검사에 실패했습니다. 입력 내용을 확인해 주세요.");
                return;
            }
        } catch (validationError) {
            errorBox(validationError, "폼 유효성 검사 중 오류가 발생했습니다. 다시 시도해 주세요.");
            return;
        }

        let data = {
            userId: $('#login-userId').val().trim(),
            password: $('#login-password').val().trim()
        };


        sendAjax('/api/user/login', data)
            .then(function(response) {
                if(response.status) {
                    historyBackWithAlert(response.message);

                }else {
                    errorBox(response.message);
                }
            })
            .catch(function(ajaxError) {
                errorBox(ajaxError, "로그인 과정에서 오류가 발생했습니다. 다시 시도해 주세요.");
            });
    });
});