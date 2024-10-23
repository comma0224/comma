$(document).ready(function() {

    $('input').change(function () {
        inputValidity($(this));
    });
});

const USERID_PATTERN = /^(?=.*[a-zA-Z])[a-zA-Z0-9]{6,}$/;
const PASSWORD_PATTERN = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$/;
const NICKNAME_PATTERN = /^(?=.*[가-힣]{2,}|[a-zA-Z]{4,})[a-zA-Z가-힣0-9]+$/;
//(?=.*[가-힣])[가-힣a-zA-Z]{3,}|

async function formValidity(form) {
    let inputs = form.find('input');
    let promises = inputs.map(
        (index, input) => inputValidity($(input))
    ).get();

    let results = await Promise.all(promises);
    return results.every(isValid => isValid === 'valid');
}

async function inputValidity(input) {
    let id = input.attr('id');
    let value = input.val();
    let feedback = input.closest('.input-area').find('.feedback');

    let [status, message] = await validators[id](value);

    input.attr('class', `form-control input-${status}`);
    feedback.attr('class', `feedback feedback-${status}`).text(message);

    return status;
}

const validators = {
    "login-userId": async function (userId) {
            if (!userId) {
                return ["invalid", '아이디를 입력해 주세요.'];
            }
            if (!USERID_PATTERN.test(userId)) {
                return ["invalid", '아이디는 6자리 이상 영문과 숫자만 입력 가능합니다.'];
            }
            return ["valid", ''];
        },
        "login-password": async function (password) {
            if (!password) {
                return ["invalid", '비밀번호를 입력해 주세요.'];
            }
            if (!PASSWORD_PATTERN.test(password)) {
                return ["invalid", '비밀번호는 8자리 이상 영문, 숫자, 특수문자를 모두 포함해야 합니다.'];
            }
            return ["valid", ''];
        },
    "signup-userId": async function (userId) {
        if (!userId) {
            return ["invalid", '아이디를 입력해 주세요.'];
        }
        if (!USERID_PATTERN.test(userId)) {
            return ["invalid", '아이디는 6자리 이상 영문과 숫자만 입력 가능합니다.'];
        }
        const response = await sendAjax("/api/user/check-duplicate-userId", {userId: userId});

            if (!response.status) {
                return ["invalid", response.message];
            } else {
                return ["valid", response.message];
            }
    },
    "signup-password": async function (password) {
        if (!password) {
            return ["invalid", '비밀번호를 입력해 주세요.'];
        }
        if (!PASSWORD_PATTERN.test(password)) {
            return ["invalid", '비밀번호는 8자리 이상 영문, 숫자, 특수문자를 포함해야 합니다.'];
        }
        return ["valid", '사용 가능한 비밀번호입니다.'];
    },
    "signup-confirmPassword": async function (confirmPassword) {
        let password = $('#signup-password').val();
        if (password !== confirmPassword) {
            return ["invalid", '비밀번호와 일치하지 않습니다.'];
        }
        return ["valid", '비밀번호와 일치합니다.'];
    },
    "signup-nickname": async function (nickname) {
        if (!nickname) {
            return ["invalid", '별명을 입력해 주세요.'];
        }
        if (!NICKNAME_PATTERN.test(nickname)) {
            return ["invalid", '별명은 한글 2자 이상, 영문 4자 이상이어야 합니다.'];
        }

        const response = await sendAjax("/api/user/check-duplicate-nickname", {nickname: nickname});
        if (response.status) {
            return ["valid", response.message];
        } else {
            return ["invalid", response.message];
        }

    }
};
//     눈깜빡임 버튼
$(document).ready(function(){
    $('.toggle-password').click(function (){
        const passInput = $(this).siblings('input');
        const passType = passInput.attr('type');

        if (passType === 'password'){
            passInput.attr('type', 'text');
            $(this).hide();
            $(this).siblings('.toggle-open-eye').show()
        }
        else {
            passInput.attr('type','password');
            $(this).siblings('.toggle-close-eye').show();
            $(this).hide();
        }
        passInput.val(currentValue);
    });
});