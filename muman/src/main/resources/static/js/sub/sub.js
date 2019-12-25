//yyy-mm-dd 형태로 변환
function dateForm (setStartDate){
    return setStartDate.getFullYear() + "-" + ("00" + (setStartDate.getMonth() + 1)).slice(-2) + "-" + ("00" + setStartDate.getDate()).slice(-2);
}

//오늘 날짜를 구하는 포멧터
function today(currentDay) {
    return new Date(currentDay.split("-")[0], currentDay.split("-")[1] - 1, currentDay.split("-")[2]);
}

//값을 가지고 일자 셋팅 시 포멧터
function newDateForm (value) {
    return new Date( value.split("-")[0], ( value.split("-")[1] - 1), value.split("-")[2] );
}

//date를 가지고 일자 셋팅
function withDateNewForm(date) {
    return new Date(date.getFullYear(), date.getMonth(), date.getDate())
}

//숫자 비교를 위해 날짜를 int로 묶는 함수
function intToDate(date) {
    date = date + "";
    return date.slice(0,4) + "-" + date.slice(4,6) + "-" + date.slice(6,8);
}

function dateToInt(intValue) {
    return intValue.slice(0,4) + "" + intValue.slice(5,7) + "" + intValue.slice(8,10);
}

//요일 한글 변환 포멧터
function korDay(day) {
    if(day == 0) { return "일"; } else if(day == 1) { return "월"; } else if(day == 2) { return "화"; }
    else if(day == 3) { return "수"; } else if(day == 4) { return "목"; } else if(day == 5) { return "금"; }
    else if(day == 6) { return "토"; } else { return "error";}
}

//오늘 날짜를 가져 오는 포멧터
function nowDay() {
    var today = new Date();
    return dateForm(today);
}

var Progress = {
    on : function() {
        var html = '\t<div style="position:fixed; width:100%; height:100vh; display:flex; z-index: 9; background-color: rgba(0,0,0,0.5);\n' +
            '\t\t\tjustify-content: center; align-items: center;" id="progressCon">\n' +
            '\t\t<div style="z-index: 10">\n' +
            '\t\t\t<img src="/img/ajax-loader.gif">\n' +
            '\t\t</div>\n' +
            '\t</div>';
        $("body").append(html);
    },
    off : function(text) {
        window.setTimeout(function () {
            $("#progressCon").remove();
            alert(text);
            location.reload();
        },500);

    },
}