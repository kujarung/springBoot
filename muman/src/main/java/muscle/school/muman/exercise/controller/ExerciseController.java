package muscle.school.muman.exercise.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonParser;

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

    //운동 일지 확인
    @RequestMapping(value = "/exercise/exerciseConfirm", method=RequestMethod.GET)
    public String exerciseConfirm() throws Exception {
        return "exercise/exerciseConfirm";
    }

    //운동 일지 등록
    @RequestMapping(value ="/exercise/regExData",  method=RequestMethod.POST)
    public String regExData(HttpServletRequest request) throws JSONException {
    	service.insertExData(request);
    	return "/";
    }
}

