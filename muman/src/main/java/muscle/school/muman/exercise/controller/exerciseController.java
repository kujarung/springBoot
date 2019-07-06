package muscle.school.muman.exercise.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableAutoConfiguration
public class exerciseController {

    @RequestMapping(value = "/exercise/exerciseRecord", method=RequestMethod.GET)
    public String exerciseRecord() throws Exception {
    
        return "exercise/exerciseRecord";
    }

}

