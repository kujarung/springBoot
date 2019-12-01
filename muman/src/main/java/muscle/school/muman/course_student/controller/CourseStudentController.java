package muscle.school.muman.course_student.controller;

import java.text.ParseException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping("/courseStudent/insertCourseStudent")
	@Transactional
	public String insertCourseStudent(HttpServletRequest req, 					  @RequestParam() String member_seq, 
									  @RequestParam(value = "register_start_time") String 	start_date, @RequestParam() int times,
									  @RequestParam(value = "register_end_time") String 	end_date,   @RequestParam() String[] time_list, 
									  @RequestParam() String dayList, 			  		@RequestParam() String aliasList,
									  @RequestParam() String price_date, 			  	@RequestParam() String price, @RequestParam() String price_type,
									  @RequestParam(value="insertBranch") int branch
	) throws ParseException {
		if( price == null || price == "") {
			price = "-1";
		}

		if(price_type == null || price_type == "" ) {
			price_type = "-1";
		}


		if(price_date == "" || price_date == null ) {
			price_date = "1980-01-01";
		}

		courseMasterService.insertCourse(member_seq, dayList, time_list, aliasList, branch);
		courseStudentService.insertStudent(member_seq, start_date, end_date, times, aliasList, price, price_date, price_type);
		System.out.println("킹되나");
		return "admin/common/success";
	}

}
