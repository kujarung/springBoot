package muscle.school.muman.course_student.controller;

import java.text.ParseException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.course_student.service.CourseStudentService;
import muscle.school.muman.member.service.MemberService;

@Controller
public class CourseStudentController {
	
	@Autowired 
	CourseStudentService service;
	@Autowired
	MemberService memberService;
	@Autowired
	CourseMasterService courseMasterService;
	@Autowired
	CourseStudentService courseStudentService;
	
	@PostMapping("/admin/insertCourseStudent")
	public String insertCourseStudent(HttpServletRequest req, 					  @RequestParam() String member_seq, 
									  @RequestParam(value = "register_start_time") String 	start_date, @RequestParam() int times,
									  @RequestParam(value = "register_end_time") String 	end_date,   @RequestParam() String[] time_list, 
									  @RequestParam() String dayList, 			  @RequestParam() String aliasList ) throws ParseException {
		courseMasterService.insertCourse(member_seq, dayList, time_list, aliasList);
		courseStudentService.insertStudent(member_seq, start_date, end_date, times, aliasList);
		
		return "redirect:admin_view_course_student";
	}
	
	
}
