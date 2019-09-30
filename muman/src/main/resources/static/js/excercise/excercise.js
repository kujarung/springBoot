var ex = {
	addRowBtn : function(e) {
		var $rowNum = $(e).parent().parent().parent();
		var $rowIndex = $(e).parent().parent();
		var containerStart = '<div id="ex_row' + $rowNum.index() +'">';
		var weightInput = '<input type="text" class="" name="ex_weight' + $rowNum.index() + '" placeholder="무게" style="display:inline;width:50px;">';
		var timesInput = '<input type="text" class="" name="ex_times' + $rowNum.index() + '" placeholder="횟수" style="display:inline;width:50px;">';;
		var realYnSelect = '<select name="ex_real_yn'+ $rowNum.index() + '" style="display:inline; width:40px;"><option value="n">예</option><option value="y">본</option></select>';
		var containerEnd = '</div>';
		var extraBtn = '<div class="btn_box"><a href="javascript:void(0)" onclick="ex.addRowBtn(this)">추가</a><a href="javascript:void(0)" onclick="ex.deleteRowBtn(this)">삭제</a></div>'
		var result = containerStart + " " + weightInput + " " + timesInput + " " + realYnSelect + " " + extraBtn + " " + containerEnd;
		$rowNum.append(result);
		$rowIndex.find( '[onclick="ex.addRowBtn(this)"]' ).remove();
	},
	deleteRowBtn : function(e) {
		var deletTarget = $(e).parent().parent();
		var addBtnTarget = $(e).parent().parent().prev();
		if(deletTarget.next().length ==0 ) {
			addBtnTarget.find(".btn_box").children().remove();
			if(deletTarget.index() == 1) {
				addBtnTarget.find(".btn_box").append('<a href="javascript:void(0)" onclick="ex.addRowBtn(this)">추가</a>');
			} else {
				addBtnTarget.find(".btn_box").append('<a href="javascript:void(0)" onclick="ex.addRowBtn(this)">추가</a><a href="javascript:void(0)" onclick="ex.deleteRowBtn(this)">삭제</a>');
			}
		}
		deletTarget.remove();
	},
	onSubmit : function() {
		var dataList = [];
		var weight="",times="",realYn="";
		for(var i=0;i<$("#ex_con").children().length;i++ ) {
			for(var j=0;j<$("#ex_row"+i).children().length;j++) {
				var target = $("#ex_row"+i);
				var addWeight = $( target.find("[name='ex_weight" +  i +   "']")[j] ).val();
				var addTimes = $( target.find("[name='ex_times" +  i +   "']")[j] ).val();
				var addRealYn = $( target.find("[name='ex_real_yn" +  i +   "']")[j] ).val();
				if(weight == "") {
					weight = addWeight;
					times = addTimes;
					realYn = addRealYn;
				} else {
					weight = weight + "|" + addWeight;
					times = times + "|" + addTimes;
					realYn = realYn + "|" + addRealYn;
				}
			}
			var aJson = new Object();
			aJson.exCate = $( '[name="ex_cate' + i + '"]').val();
			aJson.exSet = $("#ex_row"+i).children().length;
			aJson.weight = weight;
			aJson.times = times;
			aJson.realYn = realYn;
			dataList.push(aJson);
			weight =""; titmes =""; realYn ="";
		}
		dataList = JSON.stringify(dataList);
		$('[name="jsonData"]').val(dataList);
		$("#ex_submit").attr("action","/exercise/regExData");
		$("#ex_submit").submit();
	}
}