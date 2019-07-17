package muscle.school.muman.schedule.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import muscle.school.muman.schedule.service.ScheduleService;


@Controller
@EnableAutoConfiguration
public class ScheduleController {

    @Autowired
    ScheduleService service;

    //스케쥴 등록
    @RequestMapping(value = "/course_schedule/regSchedule", method=RequestMethod.GET)
    public String main() throws Exception {
        return "course_schedule/regSchedule";
    }

    //스케쥴 확인
    @RequestMapping(value = "/course_schedule/checkSchedule", method=RequestMethod.GET)
    public String checkSchedule(Model model) {
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        List<Map<String,Object>> list = service.SelectListCheckSchedule();
        model.addAttribute("list", list);
        return "course_schedule/checkSchedule";
    }

    //스케쥴 등록
    @RequestMapping(value = "/inserCourse", method=RequestMethod.GET)
    public String inserCourse(HttpServletRequest request) throws Exception {
        try {
            service.insertCourse(request);
        } catch (Exception e) {
        }
        return "course_schedule/checkSchedule";
    }
}