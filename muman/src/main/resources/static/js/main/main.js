$(function(){
	AOS.init();
	
    setViewport();
    $("#moblie_menu").on("click", function() {
        var memnu = $("#moblie_menu_wrap");
        if(memnu.hasClass("active")) {
            memnu.removeClass("active");
        } else {
            memnu.addClass("active");
        }
    });
    
    $("#x_btn").on("click", function() {
        var memnu = $("#moblie_menu_wrap");
        memnu.removeClass("active");
    });
})


function setViewport() {
    if( $(window).width() < 480 ) {
        var ww = ( document.documentElement.clientWidth < window.screen.width ) ? $(window).width() : window.screen.width;
        var mw = 480;
        var ratio = ww / mw;
        $('meta[name="viewport"]').attr( 'content', 'width=' + mw + ', initial-scale=' + ratio + ', user-scalable=no' );
    } else {
        $('meta[name="viewport"]').attr( 'content', 'width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no' );
    }
}

function checkMobileSize() {
    if ($(window).width() >= 992) {
        return false;
    } else {
        return true;
    }
};