(function ($) {
    'use strict';


    $(document).ready(function() {
// Sticky Header on Scroll
        var menu = $('#top-header');
        var origOffsetY = menu.offset().top;

        function scroll() {
            if ($(window).scrollTop() >= origOffsetY) {
                menu.addClass('sticky');
            } else {
                menu.removeClass('sticky');
            }
        }

        document.onscroll = scroll;

        $('#get-started-button').smoothScroll({offset: -40});
        $('#top-header a').smoothScroll();

    });

})(jQuery);