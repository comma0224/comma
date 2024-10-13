$(document).ready(function () {
    const $toggler = $('.navbar-toggler');
    const $offcanvas = $('.offcanvas');
    const $offcanvasBackdrop = $('.offcanvas-backdrop');
    const $closeButton = $offcanvas.find('.btn-close');

    $toggler.on('click', function () {
        $offcanvas.toggleClass('show');
        $offcanvasBackdrop.toggleClass('show');
    });

    $closeButton.on('click', function () {
        $offcanvas.removeClass('show');
        $offcanvasBackdrop.toggleClass('show');
    });

    $offcanvasBackdrop.on('click', function () {
        $offcanvas.removeClass('show');
        $offcanvasBackdrop.toggleClass('show');
    });
});
