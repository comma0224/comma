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
}
