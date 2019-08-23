$(function(){

	
	
	$("#courseTimeTable td").on("click", function(){
		if( $(this).hasClass("active")  ) {
			$(this).removeClass("active");
		} else {
			$(this).addClass("active");
		}
	})
	
	$("#startTime").datepicker({ dateFormat: 'yy-mm-dd' });
	$("#endTime").datepicker({ dateFormat: 'yy-mm-dd' });
	
	
	$("#startTime").on("change", function(){
		var dayOfWeekList = new Array;
		$("#courseTimeTable td.active").each(function(){
			dayOfWeekList.push ( $(this).index() );
		})
		var formatter = new Date();
		var startTime = new Date( $("#startTime").val().split("-")[0], ( $("#startTime").val().split("-")[1] - 1) 
									,$("#startTime").val().split("-")[2]);
		formatter = new Date( $("#startTime").val().split("-")[0], ( $("#startTime").val().split("-")[1] - 1) 
				,$("#startTime").val().split("-")[2]);
		formatter.setDate( (formatter.getDate() + 30)  )
		var endTime = formatter.getFullYear() + "-" + ("00" + (formatter.getMonth() + 1)).slice(-2) + "-" + ("00" + formatter.getDate()).slice(-2)
		$("#endTime").val(endTime);
		
		/*$("#courseDateList").text( betweenDate(startTime, formatter, dayOfWeekList) );*/
		var resultList = betweenDate(startTime, formatter, dayOfWeekList) 
		for(var i = 0; i < resultList.length; i++) {
			$("#courseDateList").append(  "<div class='time_list'>" + resultList[i] + "</div>"    );
		}
		$("#courseDateList").append("<div id='plus'>plus</div>");
		$("#plus").datepicker({ dateFormat: 'yy-mm-dd' });
		$(".time_list").on("click",function() {
			$(this).remove();
		});
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
	

	
	function betweenDate(startTime, endTime, dayOfWeekList) {
		var resultList = [];
		var cDay = 24 * 60 * 60 * 1000; //날짜 포멧팅을 위함
		var differ = parseInt( (endTime - startTime) / cDay); //마지막일과 처음 차이
		var differDate = new Date(startTime.getFullYear(), startTime.getMonth(), startTime.getDate());
		for(var i = 1; i < differ + 1; i++) {
			differDate.setDate( parseInt( differDate.getDate() ) + 1 ) ;
			for(var j = 0; j< dayOfWeekList.length; j++) {
				if(dayOfWeekList[j] == differDate.getDay() ) {
					resultList.push(differDate.getFullYear() + "-"  + ("00" + (differDate.getMonth() + 1)).slice(-2) + "-" + ("00" + differDate.getDate()).slice(-2) );
				}
			}
		}
		return resultList;
	}
})