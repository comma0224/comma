$(document).ready(function() {
    $('.Upload-Badge').click(function () {
        let name = $('#name').val();
        let price = $('#price').val();
        let tier = $('#tier').val();
        let file = $('#file')[0].files[0];

        // let data = {
        //     name: name,
        //     price: price,
        //     tier: tier,
        //     file: file
        // };
        let formData = new FormData();
        formData.append('name', name);
        formData.append('price', price);
        formData.append('tier', tier);
        formData.append('file', file);
        $.ajax({
                type: "POST",
                url: "/api/admin/badge-save",
                data: formData,
                contentType: false,
                processData: false,
                success: function (response) {
                    if (response.status) {
                        alertBox(response.message);
                    } else {
                        errorBox(response.message);
                    }
                }
            }
        );
    });
});

function previewImage(event) {
    const reader = new FileReader();
    reader.onload = function(){
        const output = document.getElementById('badgePreview');
        output.src = reader.result;
        output.style.display = 'block';
    };
    reader.readAsDataURL(event.target.files[0]);
}