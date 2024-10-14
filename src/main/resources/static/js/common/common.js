$(document).ready(function() {

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