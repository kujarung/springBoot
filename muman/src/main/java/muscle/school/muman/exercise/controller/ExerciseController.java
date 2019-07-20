package muscle.school.muman.exercise.controller;

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
    public String exerciseConfirm(Model model) throws Exception {
        int memberSeq = 1;
        List<Map<String, Object>> list = service.serchExData(memberSeq);
        System.out.println(list);
        model.addAttribute("list", list);
        return "exercise/exerciseConfirm";
    }

    //운동 일지 디테일 확인
    @RequestMapping(value = "/exercise/exerciseConfirmDetail", method=RequestMethod.GET)
    public String exerciseConfirmDetail(HttpServletRequest request, Model model) throws Exception {
    	int ex_seq = Integer.parseInt(request.getParameter("ex_seq") );
    	List<Map<String, Object>> detail = service.serchExDetail(ex_seq);
    	model.addAttribute("detail", detail);
    	System.out.println(detail);
        return "exercise/exerciseDetail";
    }

    //운동 일지 등록
    @RequestMapping(value ="/exercise/regExData",  method=RequestMethod.POST)
    public String regExData(HttpServletRequest request) throws JSONException {
    	service.insertExData(request);
    	return "exercise/exerciseConfirm";
    }
}

