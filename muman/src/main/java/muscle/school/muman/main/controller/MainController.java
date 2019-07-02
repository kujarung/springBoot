package muscle.school.muman.main.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import muscle.school.muman.main.service.MemberVO;
import muscle.school.muman.main.service.mapper.MainMapper;

@Controller
@EnableAutoConfiguration
@MapperScan(basePackages = "muscle.school.muman.main.service.mapper")

public class MainController {

    @Autowired
    private MainMapper mapper;

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public String main() throws Exception {
        List<MemberVO> list = mapper.selectMemberList();
        for(int i=0; i<list.size(); i++){
            System.out.println("name : " + list.get(i).getName());
        }       
        return "main";
    }

    @RequestMapping(value = "/intro", method=RequestMethod.GET)
    public String intro() {
        return "intro";
    }
}