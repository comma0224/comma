$(document).ready(function() {

    $('input').change(function () {
        inputValidity($(this));
    });
});

// const USERID_PATTERN = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z0-9]{6,}$/;
// const PASSWORD_PATTERN = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
// const NICKNAME_PATTERN = /^(?=.*[a-zA-Z가-힣])(?=.*\d)?(?=.{4,}(?:[가-힣]{2}|[a-zA-Z\d]))[a-zA-Z가-힣\d]+$/;

const USERID_PATTERN = /^[a-zA-Z]+[0-9]*$/;
const PASSWORD_PATTERN = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]+$/;
const NICKNAME_PATTERN = /^[a-zA-Z가-힣\d]+$/;

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

// function inputValidity(input) {
//     let id = input.attr('id');
//     let value = input.val();
//     let feedback = input.closest('.input-area').find('.feedback');
//
//     let [status, message] = validators[id](value);
//
//     input.attr('class', `form-control input-${status}`);
//     feedback.attr('class', `feedback feedback-${status}`).text(message);
//
//     return status;
// }

const validators = {
    "login-userId": async function (value) {
            if (!value) {
                return ["invalid", '아이디를 입력해 주세요.'];
            }
            if (!USERID_PATTERN.test(value)) {
                return ["invalid", '아이디는 6자리 이상 영문과 숫자만 입력 가능합니다.'];
            }
            return ["valid", ''];
        },
        "login-password": async function (value) {
            if (!value) {
                return ["invalid", '비밀번호를 입력해 주세요.'];
            }
            if (!PASSWORD_PATTERN.test(value)) {
                return ["invalid", '비밀번호는 8자리 이상 영문, 숫자, 특수문자를 모두 포함해야 합니다.'];
            }
            return ["valid", ''];
        },
    "signup-userId": async function (value) {
        if (!value) {
            return ["invalid", '아이디를 입력해 주세요.'];
        }
        if (!USERID_PATTERN.test(value)) {
            return ["invalid", '아이디는 6자리 이상 영문과 숫자만 입력 가능합니다.'];
        }
        try {
            const response = await $.ajax({
                url: "/api/user/check-duplicate-userId",
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({userId: value})
            });

            if (!response.status) {
                return ["invalid", response.message];
            } else {
                return ["valid", response.message];
            }
        } catch (error) {
            return ["invalid", '서버 오류가 발생했습니다.'];
        }
    },
    "signup-password": async function (value) {
        if (!value) {
            return ["invalid", '비밀번호를 입력해 주세요.'];
        }
        if (!PASSWORD_PATTERN.test(value)) {
            return ["invalid", '비밀번호는 8자리 이상 영문, 숫자, 특수문자를 모두 포함해야 합니다.'];
        }
        return ["valid", ''];
    },
    "signup-confirmPassword": async function (value) {
        let password = $('#signup-password').val();
        if (password !== value) {
            return ["invalid", '비밀번호가 일치하지 않습니다.'];
        }
        return ["valid", ''];
    },
    "signup-nickname": async function (value) {
        if (!value) {
            return ["invalid", '닉네임을 입력해 주세요.'];
        }
        if (!NICKNAME_PATTERN.test(value)) {
            return ["invalid", '닉네임은 2자리 이상 한글 또는 4자리 이상 영문만 입력 가능합니다.'];
        }

        try {
            let result = await sendAjax("/api/user/check-duplicate-nickname", {nickname: value});

            if (result.status) {
                return ["valid", result.message];
            } else {
                return ["invalid", result.message];
            }
        } catch (error) {
            return ["invalid", '서버 오류가 발생했습니다.'];
        }
    }
};