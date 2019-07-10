package muscle.school.muman.exercise.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableAutoConfiguration
public class exerciseController {

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
    

}

