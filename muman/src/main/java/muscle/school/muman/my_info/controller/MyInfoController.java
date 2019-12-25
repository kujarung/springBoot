package muscle.school.muman.my_info.controller;

import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class MyInfoController {

    @Autowired
    MemberService memberService;
    @Autowired
    CourseMasterService courseMasterService;

    @RequestMapping(value = "/my_page")
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Integer memberSeq = (Integer)session.getAttribute("memberSeq");
        if(memberSeq == null) {
            return "index";
        } else {
            Map<String,Object> memberInfo = memberService.getMember(memberSeq);
            List<Map<String,Object>> exList = courseMasterService.selectCourseList(memberSeq);
            System.out.println(memberInfo);
            System.out.println(exList);
            return "/my_page/myPage";
        }
    }
}