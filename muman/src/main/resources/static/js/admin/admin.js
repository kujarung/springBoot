/*$(function(){
	$("#startTime").datepicker({ dateFormat: 'yy-mm-dd' });
	$("#endTime").datepicker({ dateFormat: 'yy-mm-dd' });
	
	var setStartDate = new Date();
	var setEndDate;
	if(setStartDate.getDay() == 1) {
		setStartDate = new Date();
		setEndDate = new Date(setStartDate.getFullYear(), setStartDate.getMonth(), setStartDate.getDate() + 4);
	} else {
		for(var i=1; i<5;i++) {
			if( ( setStartDate.getDay() - i) == 1  ) {
				setStartDate.setDate((setStartDate.getDate()-i));
				setEndDate = new Date(setStartDate.getFullYear(), setStartDate.getMonth(), setStartDate.getDate() + 4);
			} 
		}
	}
	
	console.log(setEndDate);
	var week = dateForm(setStartDate) + "~" + dateForm(setEndDate);
	$("#courseTimeTable thead").append();
	$("#today").text(week);
	
	$("#nextWeek").on("click",function() {
		setStartDate.setDate(setStartDate.getDate() + 7 );
		setEndDate.setDate(setEndDate.getDate() + 7);
		week = dateForm(setStartDate) + "~" + dateForm(setEndDate);
		$("#today").text(week);
	})
	
	$("#beforeWeek").on("click",function(){
		setStartDate.setDate(setStartDate.getDate() - 7 );
		setEndDate.setDate(setEndDate.getDate() - 7);
		var week = dateForm(setStartDate) + "~" + dateForm(setEndDate); 
		$("#today").text(week);
	})
	
	$("#memberList").hide();
	//맴버 찾기 버튼 클릭 시 리스트 출력
	$("#findMember").on("click", function(){
		$.ajax({
			url:'/selectMemberList',
			dataType: "json",
			async: false,
			success: function(result){
				$("#memberList").show();
				$("#memberList tbody").children().remove();
				var jsonResult = result.result;
				for(var i=0;i<Object.keys(jsonResult).length;i++) {
					var tr = "<tr>" 
					+ "<td>" + jsonResult[i].no + "</td>"
					+" <td class='tb_name'>" + jsonResult[i].name + "</td>"
					+" <td class='tb_id'>" + jsonResult[i].member_id + "</td>"
					+" <td class='hidden tb_seq'>" + jsonResult[i].member_seq + "</td>"
					+ "</tr>";
					$("#memberList tbody").append(tr);
				}
			}
         })
         
         $("#memberList td").on("click", function(){
        	 console.log( $(this).parent().find(".tb_name").text());
        	 $("#memberList tbody").children().remove();
        	 $("#memberList").hide();
        	 $('[name="member_name"]').val($(this).parent().find(".tb_name").text());
        	 $('[name="member_seq"]').val($(this).parent().find(".tb_seq").text());
         })
	})
	
	
	$("#courseTimeTable td").on("click", function(){
		if( $(this).hasClass("active")  ) {
			$(this).removeClass("active");
		} else {
			if( $(this).children().eq(0).text().trim() == "강의 없음"){
				alert("강의가 없는 날은 선택 불가능 합니다.");
				return false;
			} else {
				$(this).addClass("active");
			}
		}
	})
	
	//시작 일자 변경 시 로식 실행
	$("#startTime").on("change", function() {
		if($("#courseTimeTable td.active").length == 0 ) {
			alert("강좌를 먼저 선택하여 주세요.");
			$("#startTime").val("");
			return false;
		} else {
			$("#courseDateList").children().remove();
			var plusIndex = 0;
			var plusClass = "plus" + plusIndex;
			var dayOfWeekList = new Array;
			$("#courseTimeTable td.active").each(function(){
				dayOfWeekList.push ( $(this).index() );
			})
			var formatter = new Date();
			var startTime = new Date( $("#startTime").val().split("-")[0], ( $("#startTime").val().split("-")[1] - 1) 
										,$("#startTime").val().split("-")[2]);
			formatter = new Date( $("#startTime").val().split("-")[0], ( $("#startTime").val().split("-")[1] - 1) 
					,$("#startTime").val().split("-")[2]);
			formatter.setDate( (formatter.getDate() + 28)  )
			var endTime = dateForm(formatter);
			$("#endTime").val(endTime);
			
			$("#courseDateList").text( betweenDate(startTime, formatter, dayOfWeekList) );
			var resultList = betweenDate(startTime, formatter, dayOfWeekList) 
			for(var i = 0; i < resultList.length; i++) {
				$("#courseDateList").append(  "<div class='time_list'>" + resultList[i] + "</div>"    );
			}
			
			$("#courseDateList").append("<input class='"+ plusClass +"' readonly/>");
			$("." + plusClass).datepicker({ dateFormat: 'yy-mm-dd' });
			$("#plusBtn").on("click" , function(){
				$("." + plusClass).contents().unwrap().wrap( '<div></div>' )
				$("." + plusClass).addClass("time_list");
				$("." + plusClass).datepicker("destroy");
				$("." + plusClass).removeClass(plusClass);
				plusIndex++;
				$("#courseDateList").append("<input class='"+ plusClass +"' readonly></div>");
				$("." + plusClass).datepicker({ dateFormat: 'yy-mm-dd' });
				$(".time_list").on("click",function() {
					$(this).remove();
					$("#number").text( "총" + $(".time_list").length + "회");
				});
				$("#number").text( "총" + $(".time_list").length + "회");
			})
			$(".time_list").on("click",function() {
				$(this).remove();
			});
			$("#times").text("주 " + $("#courseTimeTable td.active").length + "회");
			$("#number").text( "총" + $(".time_list").length + "회");
		}
	});
	
	
	
	$("#endTime").on("change", function(){
		var dayOfWeekList = new Array;
		$("#courseTimeTable td.active").each(function(){
			dayOfWeekList.push ( $(this).index() );
		})
		var formatter = new Date();
		var startTime = new Date( $("#startTime").val().split("-")[0], ( $("#startTime").val().split("-")[1] - 1) 
									,$("#startTime").val().split("-")[2]);
		var endTime = new Date( $("#endTime").val().split("-")[0], ( $("#endTime").val().split("-")[1] - 1) 
				,$("#endTime").val().split("-")[2]);
		var resultRest = betweenDate(startTime, endTime, dayOfWeekList);
		for(var i = 0; i < resultRest.length; i++) {
			$("#courseDateList").innerHTML(  "<div class='time_list'>" + resultRest[i] + "</div>"    );
		}
	});
	

	//시작과 끝일 사이에 일치하는 요일 리스트를 구하는 함수
	function betweenDate(startTime, endTime, dayOfWeekList) {
		var resultList = [];
		var cDay = 24 * 60 * 60 * 1000; //날짜 포멧팅을 위함
		var differ = parseInt( (endTime - startTime) / cDay); //마지막일과 처음 차이
		var differDate = new Date(startTime.getFullYear(), startTime.getMonth(), startTime.getDate());
		var resultDayOfWeekList = [];
		for(var i = 0; i < differ; i++) {
			differDate.setDate( parseInt( differDate.getDate() ) + 1 ) ;
			for(var j = 0; j< dayOfWeekList.length; j++) {
				if(dayOfWeekList[j] == differDate.getDay() ) {
					resultList.push(dateForm(differDate));
				}
			}
		}
		return resultList;
	}
	
	function nextWeek(startTime, endTime) {
		
	}
	
	function beforeWeek(startTime, endTime) {
		
	}
	
	//인서트 버튼 클릭
	$("#courseInsertBtn").on("click", function() {
		var isValid = true;
		var validMsg;
		if ( $('[name="member_name"]').val() == '' ) {
			validMsg = "회원 이름 입력해 주세요";
			isValid = false;
		}
		
		if($("#startTime").val() == '') {
			validMsg = "시작일을 입력해 주세요.";
			isValid = false;
		}
		
		if(!isValid) {
			alert(validMsg);
			return false;
		} else {
			$("#course").submit();
		}
		
		
	});
	
	//yyy-mm-dd 형태로 변환
	function dateForm(setStartDate){
		return setStartDate.getFullYear() + "-" + ("00" + (setStartDate.getMonth() + 1)).slice(-2) + "-" + ("00" + setStartDate.getDate()).slice(-2)
	}
	
})*/