$(document).ready(function() {
    // 각 인풋박스에 onChange 이벤트를 추가
    $('input').on('change', function() {
        inputValidity(this);
    });

    $('#signup-complete').on('submit', function(event) {
        event.preventDefault();

        formValidity(this).then(function(isValid) {
            if (isValid) {
                const userData = {
                    userId: $('#register-user-id').val(),
                    userPassword: $('#register-user-password').val(),
                    nickname: $('#register-nickname').val()
                };

                sendAjax('/register', userData)
                    .then(response => {
                        if (response.status) {
                            alertBox(response.message);
                            navigateToPage('login');
                        } else {
                            errorBox(response.message);
                        }
                    })
                    .catch(error => {
                        errorBox(error.message);
                    });
            } else {
                event.stopPropagation();
            }
        });
    });

    function formValidity(form) {
        var items = form.find('input[required]');

        items.each(function() {
            inputValidity(this);
        });

    }

    function inputValidity(input) {

    }

    function validateUserId(value) {
        var idPattern = /^[a-zA-Z0-9]{6,}$/;



        // 아이디 유효성 검사
        $('#register-user-id').on('input', function() {
            var value = $(this).val();
            var feedback = $('#register-user-id-feedback');
            var input = $(this);

            validateRegisterUserId(value, feedback, input, idPattern);
        });
    }
    function validateRegisterUserId(value, feedback, input, idPattern) {
        return new Promise((resolve) => {
            // 빈 값 체크
            if (!value) {
                feedback.text('아이디를 입력해 주세요.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            // 영문과 숫자 체크
            if (!idPattern.test(value)) {
                feedback.text('아이디는 6자리 이상 영문과 숫자만 입력 가능합니다.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            // 서버 측 유효성 검사
            sendAjax("/is-user-id-exists", { userId: value })
                .then(response => {
                    if (response.status) {
                        feedback.text('이미 사용 중인 아이디입니다.').addClass('invalid-feedback').show();
                        input.addClass('is-invalid');
                        resolve(false);
                    } else {
                        feedback.text('사용 가능한 아이디입니다.').addClass('valid-feedback').show();
                        input.addClass('is-valid');
                        resolve(true);
                    }
                })
                .catch(error => {
                    feedback.text('서버 에러가 발생했습니다.').addClass('invalid-feedback').show();
                    input.addClass('is-invalid');
                    resolve(false);
                });
        });
    }

    function validateRegisterUserPassword(value, feedback, input, passwordPattern) {
        return new Promise((resolve) => {
            // 빈 값 체크
            if (!value) {
                feedback.text('비밀번호를 입력해 주세요.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            // 비밀번호 유효성 체크
            if (!passwordPattern.test(value)) {
                feedback.text('비밀번호는 8자리 이상 영문, 숫자, 특수문자를 포함해야 합니다.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            input.addClass('is-valid');
            feedback.addClass('valid-feedback').hide();
            resolve(true);
        });
    }

    function validateRegisterConfirmUserPassword(value, feedback, input) {
        return new Promise((resolve) => {
            // 빈 값 체크
            if (!value) {
                feedback.text('비밀번호 확인을 입력해 주세요.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            // 비밀번호와 일치하는지 확인
            var userPassword = $('#register-user-password').val();
            if (value !== userPassword) {
                feedback.text('비밀번호가 일치하지 않습니다.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            input.addClass('is-valid');
            feedback.addClass('valid-feedback').hide();
            resolve(true);
        });
    }

    function validateLoginUserId(value, feedback, input, idPattern) {
        return new Promise((resolve) => {
            // 빈 값 체크
            if (!value) {
                feedback.text('아이디를 입력해 주세요.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            // 영문과 숫자 체크
            if (!idPattern.test(value)) {
                feedback.text('아이디는 6자 이상 영문과 숫자로만 적어주세요').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            input.addClass('is-valid');
            feedback.addClass('valid-feedback').hide();
            resolve(true);
        });
    }

    function validateLoginUserPassword(value, feedback, input, passwordPattern) {
        return new Promise((resolve) => {
            // 빈 값 체크
            if (!value) {
                feedback.text('비밀번호를 입력해 주세요.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            // 비밀번호 유효성 체크
            if (!passwordPattern.test(value)) {
                feedback.text('비밀번호는 8자리 이상 영문, 숫자, 특수문자를 포함해야 합니다.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            input.addClass('is-valid');
            feedback.addClass('valid-feedback').hide();
            resolve(true);
        });
    }

    function validateUpdateNickname(value, feedback, input) {
        return new Promise((resolve) => {
            // 빈 값 체크
            if (!value) {
                feedback.text('필수 항목입니다.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            // 서버 측 유효성 검사
            sendAjax("/is-nickname-exists", { nickname: value })
                .then(response => {
                    if (response.status) {
                        feedback.text('이미 사용 중인 닉네임입니다.').addClass('invalid-feedback').show();
                        input.addClass('is-invalid');
                        resolve(false);
                    } else {
                        feedback.text('사용 가능한 닉네임입니다.').addClass('valid-feedback').show();
                        input.addClass('is-valid');
                        resolve(true);
                    }
                })
                .catch(error => {
                    feedback.text('서버 에러가 발생했습니다.').addClass('invalid-feedback').show();
                    input.addClass('is-invalid');
                    resolve(false);
                });
        });
    }

    function validateModifyUserPassword(value, feedback, input, passwordPattern) {
        return new Promise((resolve) => {
            // 빈 값 체크
            if (!value) {
                feedback.text('비밀번호를 입력해 주세요.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            // 비밀번호 유효성 체크
            if (!passwordPattern.test(value)) {
                feedback.text('비밀번호는 8자리 이상 영문, 숫자, 특수문자를 포함해야 합니다.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            input.addClass('is-valid');
            feedback.addClass('valid-feedback').hide();
            resolve(true);
        });
    }

    function validateModifyConfirmUserPassword(value, feedback, input) {
        return new Promise((resolve) => {
            // 빈 값 체크
            if (!value) {
                feedback.text('비밀번호 확인을 입력해 주세요.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            // 비밀번호와 일치하는지 확인
            var password = $('#modify-user-password').val();
            if (value !== password) {
                feedback.text('비밀번호가 일치하지 않습니다.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            input.addClass('is-valid');
            feedback.addClass('valid-feedback').hide();
            resolve(true);
        });
    }

    function validateCheckOrDeleteUserPassword(value, feedback, input, passwordPattern) {
        return new Promise((resolve) => {
            // 빈 값 체크
            if (!value) {
                feedback.text('비밀번호를 입력해 주세요.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            // 비밀번호 유효성 체크
            if (!passwordPattern.test(value)) {
                feedback.text('비밀번호는 8자리 이상 영문, 숫자, 특수문자를 포함해야 합니다.').addClass('invalid-feedback').show();
                input.addClass('is-invalid');
                resolve(false);
                return;
            }

            input.addClass('is-valid');
            feedback.addClass('valid-feedback').hide();
            resolve(true);
        });
    }
});