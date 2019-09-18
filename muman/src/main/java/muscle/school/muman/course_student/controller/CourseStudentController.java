package muscle.school.muman.course_student.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import muscle.school.muman.course_student.service.CourseStudentService;
import reactor.netty.http.server.HttpServerRequest;

@Controller
public class CourseStudentController {
	
	@Autowired CourseStudentService courseStudentService;
	
	@PostMapping("/admin/insertCourseStudent")
	public void insertCourseStudent(HttpServletRequest req) {
		req.getParameter("member_seq");
		req.getParameter("register_start_time");
		req.getParameter("register_end_time");
		req.getParameter("times");
		req.getParameter("number");
		req.getParameter("member_seq");
		System.out.println(req);
	}
	
	
}
