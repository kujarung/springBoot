package muscle.school.muman.main.controller;

import muscle.school.muman.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import muscle.school.muman.main.service.MainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class MainController {

    @Autowired
    MainService service;


    @Autowired
    MemberService memberService;
    //메인 화면으로 이동
    @RequestMapping(value = "/", method=RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) throws Exception {
        List<Map<String, Object>> result = memberService.selectMemberList(1,"");
        model.addAttribute("result", result);
        return "index";
    }

    //강사 소개 페이지로 이동
    @RequestMapping(value = "/intro_teacher", method=RequestMethod.GET)
    public String introTeacher() {
        return "intro_teacher/intro_teacher";
    }

    //학교 소개 페이지로 이동
    @RequestMapping(value = "/intro_school", method=RequestMethod.GET)
    public String introSchool() {
        return "intro_school/intro_school";
    }
    
    //강좌 소개 페이지로 이동
    @RequestMapping(value = "/intro_course", method=RequestMethod.GET)
    public String intro_course() {
        return "intro_course/intro_course";
    }

    @RequestMapping(value = "/map")
    public String map() {
        return "map/map";
    }


    @RequestMapping(value = "/success")
    public String success() {
        return "sign/success";
    }
}