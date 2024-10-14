$(document).ready(function() {

    // 각 인풋박스에 onChange 이벤트를 추가
    $('input').on('change', function() {
        inputValidity($(this));
    });

});

