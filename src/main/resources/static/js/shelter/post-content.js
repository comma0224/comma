$(document).ready(function () {
   $('.comment-btn').click(function () {
         let content = $(this).closest('.commentArea').find('.content').val();
         let parentCommentKey = $(this).closest('.commentArea').find('.content').data('key');
            let data = {
                content: content,
                postKey: $postKey,
                parentCommentKey: parentCommentKey
            };

            sendAjax('/api/shelter/comment-save', data)
                .then(function(response) {
                    if(response.status) {
                        alertBox(response.message);
                        location.reload();
                    }else {
                        errorBox(response.message);
                    }
                })
                .catch(function(ajaxError) {
                    errorBox(ajaxError, "댓글 작성 중 오류가 발생했습니다. 다시 시도해 주세요.");
                });

   });

    $('.isPostLike').click(function () {
        let isPostLike = $(this).text() === '♥';
        let postKey = $postKey;

        let data = {
            postKey: postKey,
            isPostLike: isPostLike
        };
        sendAjax('/api/shelter/updatePostLike', data)
            .then(response => {
                if (response.status) {
                    $('.isPostLike').text(response.message);
                    if (response.message === '♥') {
                        alertBox("좋아요 !");
                    } else {
                        alertBox("좋아요 취소 !");
                    }

                } else {
                    navigateToPageWithAlert('login', response.message);
                }
            });
    });

    $('.isCommentLike').click(function () {
        let isCommentLike = $(this).text() === '♥';
        let commentKey = $(this).closest('.comment').data('key');

        let data = {
            commentKey: commentKey,
            isCommentLike: isCommentLike
        };
        sendAjax('/api/shelter/updateCommentLike', data)
            .then(response => {
                if (response.status) {
                    $(this).text(response.message);
                    if (response.message === '♥') {
                        alertBox("좋아요 !");
                    } else {
                        alertBox("좋아요 취소 !");
                    }

                } else {
                    navigateToPageWithAlert('login', response.message);
                }
            });
    });

    $('.report').click(function () {
        var popup = window.open('/report', '',  'width=400, height=600');

        // Ensure the popup remains in focus
        if (popup) {
            popup.focus();
        }

    });

    $('.commentReply').click(function () {

        $(this).closest('.comment').find('.commentArea').toggle();
    });

});