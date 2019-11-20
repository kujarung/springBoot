package muscle.school.muman.course_master.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import muscle.school.muman.course_master.service.CourseMasterService;
import reactor.netty.http.server.HttpServerRequest;

@Controller
public class CourseMasterController {
	
	@Autowired
	CourseMasterService service;
	/*
	 * @Autowired MemberService memberService; AdminService adminService;
	 * 
	 * 
	 * //관리자 메인 페이지
	 * 
	 * @GetMapping("/admin/index") public String adminMain() { return "admin/index";
	 * }
	 * 
	 * //관리자 학생 등록 페이지
	 * 
	 * @GetMapping("/admin/admin_reg_course_student") public String
	 * adminRegCourseStudent(Model model) {
	 * 
	 * return "admin/admin_reg_course_student"; }
	 * 
	 * //관리자 강의 등록 페이지
	 * 
	 * @GetMapping("/admin/admin_reg_course") public String adminRegCourse() {
	 * return "admin/admin_reg_course"; }
	 * 
	 */

//	미루기 기능
	@RequestMapping("/couseMaster/delayCourse")
	@ResponseBody
	public void delayCourse(HttpServletResponse response, 
			@RequestParam(required = false) Integer memberSeq, 
			@RequestParam(required = false) Integer delayNum) throws IOException  {
		int result;
		try {
			result = service.delayCourse(memberSeq, delayNum);
			result = 1;
			PrintWriter pw;
			pw = response.getWriter();
			pw.print(result);
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
