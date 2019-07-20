package muscle.school.muman.register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import muscle.school.muman.main.service.MainService;

@Controller
@EnableAutoConfiguration
public class registerController {

    @Autowired
    MainService service;

    //수강생 등록
    @RequestMapping(value = "/register/regStudent", method=RequestMethod.GET)
    public String registerStudent() throws Exception {

        return "register/regStudent";
    }

    //회원 등록
    @RequestMapping(value = "/register/member", method=RequestMethod.GET)
    public String registerMember() throws Exception {

        return "register/member";
    }



}