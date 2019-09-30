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
setViewport();

function checkMobileSize() {
    if ($(window).width() >= 992) {
        return false;
    } else {
        return true;
    }
};