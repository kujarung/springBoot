//가장 큰 값을 찾는 포멧터
function maxDate(date) {
	var resultDate = [];
	var dateVal;
	for(var i=0; i< date.length; i++) {
		resultDate.push( dateToInt( $( date[i] ).val() )  );
	}
	var maxValue = Math.max.apply(null, resultDate);
	return intToDate(maxValue);
}

//숫자 비교를 위해 날짜를 int로 묶는 함수
function intToDate(date) {
	date = date + "";
	return date.slice(0,4) + "-" + date.slice(4,6) + "-" + date.slice(6,8);
}

function dateToInt(intValue) {
	return intValue.slice(0,4) + "" + intValue.slice(5,7) + "" + intValue.slice(8,10);
}

//시작과 끝일 사이에 일치하는 요일 리스트를 구하는 함수
function betweenDate(startTime, dayOfWeekList) {
	var resultList = [], totalList = {}, resultDayOfWeekList = [], holidayList = [];
	var differDate = new Date(startTime.getFullYear(), startTime.getMonth(), startTime.getDate());
	var i = 0;
	var start = 0
	var totalCnt = dayOfWeekList.length * 4;
	console.log(dayOfWeekList);
	totalList.len = 0;
	while(i < totalCnt ) {
		if(start == 0) {
			differDate.setDate( parseInt( differDate.getDate() ) );
			start++;
		} else {
			differDate.setDate( parseInt( differDate.getDate() ) + 1 );
		}
		
		for(var j = 0; j< dayOfWeekList.length; j++) {
			if( dayOfWeekList[j].index == differDate.getDay() ) {
					var isHolidayYn = isHoliday( dateForm(differDate));
				if( isHolidayYn.isHoliday ) {
					resultList.push(dateForm(differDate) + "(" + isHolidayYn.name + ")") ;
					holidayList.push(i);
					i++;
					totalCnt++;
				} else {
					resultList.push(dateForm(differDate));
					i++;
					totalList.len++;
				} 
			}
		}
	}
	totalList.holidayList = holidayList;
	totalList.list = resultList;
	totalList.maxValue = resultList[resultList.length - 1];
	return totalList;
}

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