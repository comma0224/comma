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
// 회원가입 동의 체크박스 전체 동의하기
$(document).ready(function() {
    $('#check-all').change(function () {
        // alert("체크박스 상태 변경됨");
        if (this.checked) {
            $('.check-item').prop('checked', true);
        } else {
            $('.check-item').prop('checked', false);
        }
    });

    $('.check-item').change(function() {
        let allChecked = $('.check-item:not(:checked)').length == 0;
        $('#check-all').prop('checked', allChecked);
    });
});
// 필수 체크 박스가 모두 체크 되어야 다음 단계 진입 가능
$(document).ready(function(){
    $('#join-btn').click(function (){
        let check1 = $('#check-1:checked').length > 0;
        let check2 = $('#check-2:checked').length > 0;
        if ( check1 && check2 ){
           $('#signup-from').submit();
        }
        else {
            alert("필수 체크박스에 동의 하셔야 합니다.");
            return false;
        }
    });
});
