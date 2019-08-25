package muscle.school.muman.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import muscle.school.muman.admin.service.AdminService;
import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.member.service.MemberService;

@Controller
@EnableAutoConfiguration
public class AdminController {
	
	@Autowired
	AdminService  adminService;
	@Autowired
	CourseMasterService courseMasterService;
	@Autowired
	MemberService memberService;
	
	//관리자 메인 페이지
	@GetMapping("/admin/index")
	public String adminMain() {
		return "admin/index";
	}
	
	//관리자 학생 등록 페이지
	@GetMapping("/admin/admin_reg_course_student")
	public String adminRegCourseStudent(Model model) {
		List<Map<String,Object>> list = courseMasterService.selectRegMemberNum();
		model.addAttribute("courseRegNumList", list);
		System.out.println(list);
		return "admin/admin_reg_course_student";
	}
	
	//관리자 강의 등록 페이지
	@GetMapping("/admin/admin_reg_course")
	public String adminRegCourse() {
		return "admin/admin_reg_course";
	}
	
	
}
