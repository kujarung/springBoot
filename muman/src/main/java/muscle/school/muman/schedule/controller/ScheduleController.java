package muscle.school.muman.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import muscle.school.muman.main.service.MainService;



@Controller
@EnableAutoConfiguration
public class ScheduleController {

    @Autowired
    MainService service;

    //스케쥴 등록
    @RequestMapping(value = "/regSchedule", method=RequestMethod.GET)
    public String main() throws Exception {

        return "main";
    }

    //스케쥴 확인
    @RequestMapping(value = "/checkSchedule", method=RequestMethod.GET)
    public String checkSchedule() {
        return "checkSchedule";
    }
}