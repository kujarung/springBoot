package muscle.school.muman.my_info.controller;

import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.course_student.service.CourseStudentService;
import muscle.school.muman.member.service.MemberService;
import muscle.school.muman.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    RecordService recordService;

    @RequestMapping(value = "/memberMyPage")
    public String memberMyPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Integer memberSeq = (Integer)session.getAttribute("memberSeq");
        if(memberSeq == null) {
            return "index";
        } else {
            Map<String, Object> memberInfo = memberService.getMember(memberSeq);
            Integer branch = (Integer)session.getAttribute("branch");
            List<Map<String, Object>> exList = courseMasterService.selectCourseList(memberSeq);
            Map<String, Object> courseStudentDetail = courseStudentService.getCourseStudentDetail(memberSeq);
            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("exList", exList);
            model.addAttribute("memberSeq", memberSeq);
            model.addAttribute("branch", branch);
            if (courseStudentDetail == null) {
                model.addAttribute("courseStudentDetail", null);
            } else {
                model.addAttribute("courseStudentDetail", courseStudentDetail);
            }
            return "my_page/myPage";
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
            Integer branch = (Integer)session.getAttribute("branch");
            List<Map<String, Object>> exList = courseMasterService.selectCourseList(memberSeq);
            Map<String, Object> courseStudentDetail = courseStudentService.getCourseStudentDetail(memberSeq);
            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("exList", exList);
            model.addAttribute("memberSeq", memberSeq);
            model.addAttribute("branch", branch);
            if (courseStudentDetail == null) {
                model.addAttribute("courseStudentDetail", null);
            } else {
                model.addAttribute("courseStudentDetail", courseStudentDetail);
            }
            return "my_page/memberInfo";
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
            Integer branch = (Integer)session.getAttribute("branch");
            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("exList", exList);
            model.addAttribute("memberSeq", memberSeq);
            model.addAttribute("branch", branch);
            if (courseStudentDetail == null) {
                model.addAttribute("courseStudentDetail", null);
            } else {
                model.addAttribute("courseStudentDetail", courseStudentDetail);
            }
            return "my_page/exInfo";
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
            List<Map<String, Object>> exNameList = recordService.getExNameList();

            model.addAttribute("exNameList", exNameList);
            model.addAttribute("memberInfo", memberInfo);
            return "my_page/exRecord";
        }
    }

    @RequestMapping(value = "/exCreate")
    public String exCreate(HttpServletRequest request, Model model,
                           @RequestParam(required=false, defaultValue="1") String date) {
        HttpSession session = request.getSession();
        Integer memberSeq = (Integer)session.getAttribute("memberSeq");
        if(memberSeq == null) {
            return "index";
        } else {
            if(date.equals("1") ) {
                SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd");
                Date time = new Date();
                date = format1.format(time);
                System.out.println(date);
            }
            Map<String, Object> memberInfo = memberService.getMember(memberSeq);
            Map<String, Object> memberExRecord = recordService.getExMember(memberSeq, date);
            List<Map<String, Object>> exNameList = recordService.getExNameList();

            model.addAttribute("exNameList", exNameList);
            model.addAttribute("memberExRecord", memberExRecord);
            model.addAttribute("memberInfo", memberInfo);
            model.addAttribute("today", date);
            return "my_page/exRecord/createRecord";
        }
    }


    @RequestMapping(value = "/exView")
    public String exView(HttpServletRequest request, Model model,
                           @RequestParam(required=false, defaultValue="1") String date) {
        HttpSession session = request.getSession();
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd");
        Integer memberSeq = (Integer)session.getAttribute("memberSeq");
        if(memberSeq == null) {
            return "index";
        } else {
        if(date.equals("1") ) {
            Date time = new Date();
            date = format1.format(time);
        }
        Map<String, Object> memberInfo = memberService.getMember(memberSeq);
        Map<String, Object> memberExRecord = recordService.getExMember(memberSeq, date);
        List<Map<String, Object>> memberExRecordTotal = recordService.getExMemberList(""  + memberSeq, "");
        List<Map<String, Object>> exNameList = recordService.getExNameList();
        System.out.println(memberExRecord);
        model.addAttribute("exNameList", exNameList);
        model.addAttribute("memberExRecord", memberExRecord);
        model.addAttribute("memberExRecordTotal", memberExRecordTotal);
        model.addAttribute("memberInfo", memberInfo);
        model.addAttribute("today", date);
        return "my_page/exRecord/viewRecord";
        }
    }

}