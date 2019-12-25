package muscle.school.muman.my_info.controller;

import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.course_student.service.CourseStudentService;
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
    @Autowired
    CourseStudentService courseStudentService;

    @RequestMapping(value = "/my_page")
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Integer memberSeq = (Integer)session.getAttribute("memberSeq");
        if(memberSeq == null) {
            return "index";
        } else {
            Map<String,Object> memberInfo = memberService.getMember(memberSeq);
            List<Map<String,Object>> exList = courseMasterService.selectCourseList(memberSeq);
            Map<String, Object> courseStudentDetail = courseStudentService.getCourseStudentDetail(memberSeq);
            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("exList", exList);
            if(courseStudentDetail.size() == 0) {
                model.addAttribute("courseStudentDetail", null);
            } else {
                model.addAttribute("courseStudentDetail", courseStudentDetail);
            }
            return "/my_page/myPage";
        }
    }

    @RequestMapping(value = "/memberInfo")
    public String memberInfo(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Integer memberSeq = (Integer)session.getAttribute("memberSeq");
        if(memberSeq == null) {
            return "index";
        } else {
            Map<String, Object> memberInfo = memberService.getMember(memberSeq);
            List<Map<String, Object>> exList = courseMasterService.selectCourseList(memberSeq);
            Map<String, Object> courseStudentDetail = courseStudentService.getCourseStudentDetail(memberSeq);
            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("exList", exList);
            if (courseStudentDetail.size() == 0) {
                model.addAttribute("courseStudentDetail", null);
            } else {
                model.addAttribute("courseStudentDetail", courseStudentDetail);
            }
            return "/my_page/memberInfo";
        }
    }


    @RequestMapping(value = "/exInfo")
    public String exInfo(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Integer memberSeq = (Integer)session.getAttribute("memberSeq");
        if(memberSeq == null) {
            return "index";
        } else {
            Map<String, Object> memberInfo = memberService.getMember(memberSeq);
            List<Map<String, Object>> exList = courseMasterService.selectCourseList(memberSeq);
            Map<String, Object> courseStudentDetail = courseStudentService.getCourseStudentDetail(memberSeq);
            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("exList", exList);
            if (courseStudentDetail.size() == 0) {
                model.addAttribute("courseStudentDetail", null);
            } else {
                model.addAttribute("courseStudentDetail", courseStudentDetail);
            }
            return "/my_page/exInfo";
        }
    }

    @RequestMapping(value = "/exRecord")
    public String exRecord(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Integer memberSeq = (Integer)session.getAttribute("memberSeq");
        if(memberSeq == null) {
            return "index";
        } else {
            Map<String, Object> memberInfo = memberService.getMember(memberSeq);
            List<Map<String, Object>> exList = courseMasterService.selectCourseList(memberSeq);
            Map<String, Object> courseStudentDetail = courseStudentService.getCourseStudentDetail(memberSeq);
            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("exList", exList);
            if (courseStudentDetail.size() == 0) {
                model.addAttribute("courseStudentDetail", null);
            } else {
                model.addAttribute("courseStudentDetail", courseStudentDetail);
            }
            return "/my_page/exRecord";
        }
    }
}