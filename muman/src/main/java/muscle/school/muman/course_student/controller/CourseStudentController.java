package muscle.school.muman.course_student.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/admin/insertCourseStudent")
	public String insertCourseStudent(HttpServletRequest req, @RequestParam() String member_seq, @RequestParam() String register_start_time, 
									  @RequestParam() String register_end_time, @RequestParam() String[] time_list, 
									  @RequestParam() String dayList, @RequestParam() String aliasList ) throws ParseException {
		
		courseMasterService.insertCourse(member_seq, dayList, time_list, aliasList);
		courseStudentService.insertStudent(member_seq, register_start_time, register_end_time);
		
		return "index";
	}
	
	
}
