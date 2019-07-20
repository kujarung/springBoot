package muscle.school.muman.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import muscle.school.muman.main.service.MainService;
import muscle.school.muman.main.vo.MemberVO;



@Controller
@EnableAutoConfiguration
public class MainController {

    @Autowired
    MainService service;

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public String main() throws Exception {
        List<MemberVO> list = service.selectMemberList();
        for(int i=0; i<list.size(); i++){
            System.out.println("name : " + list.get(i).getName());
        }       
        return "index";
    }

    @RequestMapping(value = "/intro", method=RequestMethod.GET)
    public String intro() {
        return "intro";
    }

    @RequestMapping(value = "/intro_course", method=RequestMethod.GET)
    public String intro_course() {
        return "intro_course/intro_course";
    }
}