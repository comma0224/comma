$(document).ready(function () {
    currentPageGroup = Math.floor($page / 10);

    createPagination($countPosts);
    if ($type) {
        $('#type').val($type);
        $('#text').val($text);
    }

    $('.tag').click(function () {

        if ($(this).data('tagkey') == 0) {
            location.href = location.pathname;
        }else {
            let tagKey = "tagKey="+$(this).data('tagkey');
            location.href = location.pathname + '?' + tagKey;
        }

    });

    $('#searchButton').click(function () {

        let tagKey = $tagKey ? "tagKey="+$tagKey+"&" : '';
        let type = "type="+$('#type').val()+"&";
        let text = "text="+$('#text').val();

        let params = tagKey + type + text;

        location.href = location.pathname + '?' + params;
    });



});

var currentPageGroup = 0;
function createPagination(totalPages) {
    var pagesDiv = $('#pages');
    pagesDiv.empty();

    var startPage = currentPageGroup * 10 + 1;
    var endPage = Math.min(startPage + 9, totalPages);

    var prevButton = $('<button class="page-btn">\< 이전</button>');
    if (currentPageGroup > 0) {
        prevButton.click(function () {
            currentPageGroup--;
            createPagination(totalPages);
        });
    }
    pagesDiv.append(prevButton);

    for (var i = startPage; i <= endPage; i++) {
        var pageButton = $('<button class="page-btn"></button>');
        pageButton.text(i);
        pageButton.attr('data-page', i);
        pageButton.click(function () {
            let tagKey = $tagKey? "tagKey="+$tagKey+"&" : '';
            let type = $type? "type="+$type+"&" : '';
            let text = $text? "text="+$text+"&" : '';
            let page = 'page='+$(this).data('page');

            let params = tagKey + type + text + page;

            location.href = location.pathname + '?' + params;

        });
        pagesDiv.append(pageButton);
    }

    var nextButton = $('<button class="page-btn">다음 ></button>');
    if (currentPageGroup < Math.floor(totalPages / 10)) {
        nextButton.click(function () {
            currentPageGroup++;
            createPagination(totalPages);
        });
    }
    pagesDiv.find('button[data-page="' + $page + '"]').addClass('text-danger');
    pagesDiv.append(nextButton);
}


