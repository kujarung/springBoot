package muscle.school.muman.exercise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import muscle.school.muman.exercise.service.ExerciseService;

@Controller
@EnableAutoConfiguration
public class ExerciseController {

    @Autowired
    ExerciseService service;
    
    //운동 일지 및 RM 선택
    @RequestMapping(value = "/exercise/exerciseChoice", method=RequestMethod.GET)
    public String exerciseChoice() throws Exception {
        return "exercise/exerciseChoice";
    }
    
    //운동 일지 작성
    @RequestMapping(value = "/exercise/exerciseRecord", method=RequestMethod.GET)
    public String exerciseRecord() throws Exception {
        return "exercise/exerciseRecord";
    }

    //운동 일지 리스트 출력
    @RequestMapping(value = "/exercise/exerciseConfirm", method=RequestMethod.GET)
    public String exerciseConfirm(Model model, @RequestParam int currentPage) throws Exception {
        int memberSeq = 1;
        Map<String, Object> params = new HashMap<>();
        params.put("member_seq", memberSeq);
        params.put("current_page", currentPage);
        List<Map<String, Object>> list = service.ExDataList(params);
        int totalPage = service.countTotalCnt();
        Map<String, Object> pagingInfo = calcPaging(totalPage, currentPage, 5);
        
        model.addAttribute("list", list);
        model.addAttribute("paging", pagingInfo);
        return "exercise/exerciseConfirm";
    }

    //운동 일지 디테일 확인
    @RequestMapping(value = "/exercise/exerciseConfirmDetail", method=RequestMethod.GET)
    public String exerciseConfirmDetail(HttpServletRequest request, Model model) throws Exception {
    	int ex_seq = Integer.parseInt(request.getParameter("ex_seq") );
    	List<Map<String, Object>> detail = service.serchExDetail(ex_seq);
    	model.addAttribute("detail", detail);
        return "exercise/exerciseDetail";
    }

    //운동 일지 등록
    @RequestMapping(value ="/exercise/regExData",  method=RequestMethod.POST)
    public String regExData(HttpServletRequest request) throws JSONException {
    	service.insertExData(request);
    	return "exercise/exerciseConfirm";
    }
    
    //운동 측정 결과 등록
    @RequestMapping(value = "/exercise/excerciseMeasure", method=RequestMethod.GET)
    public String excerciseMeasure() throws Exception {
        return "exercise/excerciseMeasure";
    }
    
    
    
    //페이징 처리
    public Map<String, Object> calcPaging(int totalCnt, int currentPage, int pagingCnt) {
    	Map<String,Object> pagingInfo = new HashMap<>();
    	int lastPage  = (int)(Math.ceil( currentPage / 10.0 )) * 10;
    	int firstPage = lastPage - 9;
    	
    	int prev = 0;
    	if(firstPage == 1) {
    		prev = 1;
    	} else {
    		prev = firstPage - 10;
    	}
    	
    	
    	int realEnd = (int)(Math.ceil ( (totalCnt * 1.0) / pagingCnt ) );
    	if(lastPage > realEnd ) {
    		lastPage = realEnd;
    	}
    	
    	int next = 0;
    	if(lastPage % 10 == 0) {
    		next = lastPage + 1;
    	} else {
    		next = lastPage;
    	}
    	
    	pagingInfo.put("lastPage", lastPage);
    	pagingInfo.put("firstPage", firstPage);
    	pagingInfo.put("prev", prev);
    	pagingInfo.put("next", next);
    	return pagingInfo;
    }
}

