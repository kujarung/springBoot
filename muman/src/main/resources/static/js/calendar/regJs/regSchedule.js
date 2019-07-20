$(function(){
    $("#reg_course_submit").on("click", function() {
        
        $("[name='startTime']").val($("[name='startTimeH']").val() + $("[name='startTimeM']").val() );
        $("[name='endTime']").val($("[name='endTimeH']").val() + $("[name='endTimeM']").val() );
        var weekInputVal = $('[name="week"]'); 
        var weekResult = "first";
        for(var i=0;i< weekInputVal.length;i++) {
            if(  $(  weekInputVal[i]  ).is(":checked") ) {
                if(weekResult =="first") {
                    weekResult = $(weekInputVal[i]).val();
                } else {
                    weekResult = weekResult + "|" + $(weekInputVal[i]).val();
                }
            }
        }
        $('[name="week_total"]').val(weekResult);
        $("#reg_course_form").attr("action","/inserCourse");
        $("#reg_course_form").submit();
    })
});