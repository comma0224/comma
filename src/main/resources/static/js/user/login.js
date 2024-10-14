$(document).ready(function() {


    // .click() 메소드 사용
    $('.oauth-kakao').click(function() {
        alert("카카오 로그인(구현 예정)");
    });

    $('.oauth-naver').click(function() {
        alert("네이버 로그인(구현 예정)");
    });

    $('#login-btn').click(function() {
        var form = $('#login');

        formValidity(form)
            .then(isValid => {
            })
            .catch(error => {
                console.error("Error validating form:", error);
            });
    });
});

function formValidity(form) {
    var inputs = form.find('input');
    var promises = [];

    inputs.each(function() {
        promises.push(inputValidity($(this)));
    });

    return Promise.all(promises).then(results => {
        return results.every(isValid => isValid);
    });
}

function inputValidity(input) {
    var inputId = input.attr('id');

    if (typeof window[inputId] === 'function') {
        var isValid = window[inputId](input);
        return isValid;
    } else {
        console.error("Validation function for input ID '" + inputId + "' not found.");
        return false;
    }
}

function userId(input) {
    var value = input.val();
    var feedback = input.closest('.input-area').find('.feedback');

    input.removeClass('is-valid is-invalid');
    feedback.removeClass('valid-feedback invalid-feedback');

    feedback.hide();

    var isValid = value.length >= 5; // Example validation: userId must be at least 5 characters long
    if (isValid) {
        input.addClass('is-valid');
        feedback.addClass('valid-feedback').text("Valid user ID").show();
    } else {
        input.addClass('is-invalid');
        feedback.addClass('invalid-feedback').text("User ID must be at least 5 characters long").show();
    }

    return isValid;
}

function password(input) {
    var value = input.val();
    var feedback = input.closest('.input-area').find('.feedback');

    input.removeClass('is-valid is-invalid');
    feedback.removeClass('valid-feedback invalid-feedback');

    feedback.hide();

    var isValid = value.length >= 8; // Example validation: password must be at least 8 characters long
    if (isValid) {
        input.addClass('is-valid');
        feedback.addClass('valid-feedback').text("Valid password").show();
    } else {
        input.addClass('is-invalid');
        feedback.addClass('invalid-feedback').text("Password must be at least 8 characters long").show();
    }

    return isValid;
}