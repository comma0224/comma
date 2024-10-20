$(document).ready(function() {
    const message = localStorage.getItem('alertMessage');
    if (message) {
        alertBox(message);
        localStorage.removeItem('alertMessage');
    }

});

/**
 * @description
 * 페이지 이름을 인자로 받아 해당 페이지로 이동합니다.
 * @param {string} page 이동할 페이지 이름
 * @return {void}
 * @Author SunJae Lee
 * @since 2024-10-13
 * @version 1.0
 * @example
 * navigateToPage('login');
 */
function navigateToPage(page) {
    window.location.href = '/' + page;
};

/**
 * @description
 * 인자를 받아 서버에 POST 요청을 보내는 함수입니다.
 * @param {string} url 요청을 보낼 URL
 * @param {object} data 요청에 포함할 데이터
 */
function sendAjax(url, data) {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: url,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                resolve(response);
            },
            error: function(xhr, status, error) {
                reject(new Error("An error occurred: " + error));
            }
        });
    });
}

// 쿠키 생성
function setCookie(name, value, days) {
    const date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    const expires = "expires=" + date.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
}

// 쿠키 가져오기
function getCookie(name) {
    const nameEQ = name + "=";
    const ca = document.cookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

// 공통 createBox 생성 함수
function createBox(message, type) {
    // div 요소를 생성하고 클래스와 텍스트를 설정합니다.
    var box = $('<div></div>').addClass('alert alert-' + type).text(message);

    // 닫기 버튼을 생성하고 클래스와 내용을 설정합니다.
    var closeButton = $('<button></button>').addClass('close-btn').html('&times;');

    // 닫기 버튼 클릭 시 실행될 함수입니다.
    closeButton.on('click', function() {
        box.css('opacity', '0');
        setTimeout(function() {
            box.remove();
        }, 500);
    });

    // 닫기 버튼을 box에 추가합니다.
    box.append(closeButton);

    // box를 alert-container에 추가합니다.
    $('#alert-container').append(box);

    // 3초 후에 box를 자동으로 닫습니다.
    setTimeout(function() {
        box.css('opacity', '0');
        setTimeout(function() {
            box.remove();
        }, 500);
    }, 3000);
}

// alertBox 생성 함수
function alertBox(message) {
    createBox(message, 'success');
}

// errorBox 생성 함수
function errorBox(message) {
    createBox(message, 'danger');
}

// infoBox 생성 함수
function infoBox(message) {
    createBox(message, 'info');
}

// warningBox 생성 함수
function warningBox(message) {
    createBox(message, 'warning');
}

function navigateToPageWithAlert(name, message) {
    localStorage.setItem('alertMessage', message);
    window.location.href = '/' + name;
}

function historyBackWithAlert(message) {
    localStorage.setItem('alertMessage', message);
    if ('referrer' in document) {
        window.location = document.referrer;
    } else {
        window.history.back();
    }
}

function logout() {
    sendAjax('/api/user/logout', {})
        .then(response => {
            if (response.status) {
                navigateToPageWithAlert('', response.message);
            } else {
                errorBox(response.message);
            }
        })
        .catch(error => {
            errorBox(error.message);
        });
}
