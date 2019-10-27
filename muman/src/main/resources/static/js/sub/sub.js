$(function(){
	AOS.init();
	
    var height = document.body.clientHeight;
    var footerHeight = $(".footer_wrap").innerHeight();
    var contentHeight = height - footerHeight;
    $("#excercise_main").height(contentHeight);
})