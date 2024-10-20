$(document).ready(function () {
    $('#post-save').click(function() {
       let data = {
           title: $('#post-title').val().trim(),
           content: $('#post-content').val().trim()
       };

       sendAjax('/api/shelter/post-save', data)
           .then(function(response) {
               if(response.status) {
                   navigateToPageWithAlert('shelter', response.message);
               }else {
                   errorBox(response.message);
               }
           })
           .catch(function(ajaxError) {
               errorBox(ajaxError, "글 작성 중 오류가 발생했습니다. 다시 시도해 주세요.");
           });
    });
});