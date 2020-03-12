package muscle.school.muman.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import muscle.school.muman.holiday.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import muscle.school.muman.admin.service.AdminService;
import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.course_student.service.CourseStudentService;
import muscle.school.muman.member.service.MemberService;
import reactor.netty.http.server.HttpServerRequest;

@Controller
@EnableAutoConfiguration
public class AdminController {
	
	@Autowired
	AdminService adminService;
	@Autowired
	MemberService memberService;
	@Autowired
	CommonService commonService;
	@Autowired
	CourseStudentService courseStudentService;
	@Autowired
	HolidayService holidayService;

	//관리자 메인 페이지
	@GetMapping("/admin/index")
	public String adminMain() {
		return "admin/index";
	}

	/**
	 * @param response
	 * @param memberSeq
	 * @return 
	 */
	@RequestMapping("/admin/delStu")
	@ResponseBody
	public void test(HttpServletResponse response, int memberSeq) throws ParseException {
		int result = 1;
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print(result);
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
