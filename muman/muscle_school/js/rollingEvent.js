window.onload = function() {
    var swiper0 = new Swiper('.main_visual_wrap', {
        direction: 'horizontal' // 슬라이드 진행방향은 수평(vertical하면 수직으로 움직임)
        , loop: true
        , speed: 500
        , slidesPerView: 1 // 한번에 보이는 슬라이드 갯수
        , navigation: {
            nextEl: '.arr_btn .right',
            prevEl: '.arr_btn .left',
        }
    });

    $(window).resize(function () {
        if ($(window).width() > 768) {
            $(".card_style").css("opacity", "1")
        }
    })
}