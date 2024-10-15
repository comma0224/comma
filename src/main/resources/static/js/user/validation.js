$(document).ready(function() {

    $('input').change(function () {
        inputValidity($(this));
    });
});

const USERID_PATTERN = /^[a-zA-Z0-9]{6,}$/;
const PASSWORD_PATTERN = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;

async function formValidity(form) {
    let inputs = form.find('input');
    let promises = inputs.map(
        (index, input) => inputValidity($(input))
    ).get();

    let results = await Promise.all(promises);
    return results.every(isValid => isValid === 'valid');
}


function inputValidity(input) {
    let id = input.attr('id');
    let value = input.val();
    let feedback = input.closest('.input-area').find('.feedback');

    let [status, message] = validators[id](value);

    input.attr('class', `form-control input-${status}`);
    feedback.attr('class', `feedback feedback-${status}`).text(message);

    return status;
}

const validators = {
    "login-userId": function (value) {
        if (!value) {
            return ["invalid", '아이디를 입력해 주세요.'];
        }
        if (!USERID_PATTERN.test(value)) {
            return ["invalid", '아이디는 6자리 이상 영문과 숫자만 입력 가능합니다.'];
        }
        return ["valid", ''];
    },
    "login-password": function (value) {
        if (!value) {
            return ["invalid", '비밀번호를 입력해 주세요.'];
        }
        if (!PASSWORD_PATTERN.test(value)) {
            return ["invalid", '비밀번호는 8자리 이상 영문, 숫자, 특수문자를 모두 포함해야 합니다.'];
        }
        return ["valid", ''];
    }
};
