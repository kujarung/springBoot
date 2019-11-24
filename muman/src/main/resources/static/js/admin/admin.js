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