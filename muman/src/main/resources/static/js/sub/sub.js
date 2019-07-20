function log(e) {
    console.log(e);
}

$(function(){
    var height = document.body.clientHeight;
    var footerHeight = $(".footer_wrap").innerHeight();
    var contentHeight = height - footerHeight;
    log(height);
    log(footerHeight);
    log(contentHeight);
    $("#excercise_main").height(contentHeight);
})