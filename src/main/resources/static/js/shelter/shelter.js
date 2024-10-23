$(document).ready(function () {
    $('.location').click(function () {
        let location = $(this).data('location');
        navigateToPage("shelter/" + location);
    });





    $('.isShelterLike').click(function () {
        let isShelterLike = $(this).text() === '♥';
        let shelterKey = $shelterKey;

        let data = {
            shelterKey: shelterKey,
            isShelterLike: isShelterLike
        };

        sendAjax('/api/shelter/updateShelterLike', data)
            .then(response => {
                if (response.status) {
                    $('.isShelterLike').text(response.message);
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
});