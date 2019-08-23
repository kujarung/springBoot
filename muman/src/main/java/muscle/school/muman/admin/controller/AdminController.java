package muscle.school.muman.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import muscle.school.muman.admin.service.AdminService;
import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.course_student.service.CourseStudentService;
import muscle.school.muman.member.service.MemberService;

@Controller
public class AdminController {
	
	@Autowired
	MemberService memberService;
	AdminService  adminService;
	CourseStudentService courseStudentService;
	CourseMasterService courseMasterService;
	
	//관리자 메인 페이지
	@GetMapping("/admin/index")
	public String adminMain() {
		return "admin/index";
	}
	
	//관리자 학생 등록 페이지
	@GetMapping("/admin/admin_reg_course_student")
	public String adminRegCourseStudent(Model model) {
		List<Map<String,Object>> list = courseMasterService.selectCourseMaterList();
		model.addAttribute("list",list);		
		return "admin/admin_reg_course_student";
	}
	
	//관리자 강의 등록 페이지
	@GetMapping("/admin/admin_reg_course")
	public String adminRegCourse() {
		return "admin/admin_reg_course";
	}
	
	
}
