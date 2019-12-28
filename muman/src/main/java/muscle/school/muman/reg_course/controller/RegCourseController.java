package muscle.school.muman.reg_course.controller;

import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.course_student.service.CourseStudentService;
import muscle.school.muman.holiday.service.HolidayService;
import muscle.school.muman.member.service.MemberService;
import muscle.school.muman.reg_course.service.RegCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegCourseController {
	@Autowired
	MemberService memberService;
	@Autowired
	CourseMasterService courseMasterService;
	@Autowired
	CommonService commonService;
	@Autowired
	HolidayService holidayService;
	@Autowired
	CourseStudentService courseStudentService;


	//관리자 학생 등록 페이지
	@GetMapping(value = "/reg_course")
	public String reg_course(Model model, @RequestParam(required=false) String standardDate
			,@RequestParam(required=false, defaultValue= "1") int branch
	) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("branch", branch);
		//시작일과 끝일을 리턴 받음
		String[] dateList   = commonService.todayWeek(standardDate);
		String startDate 	= dateList[0];
		String endDate 		= dateList[1];
		List<Map<String,Object>> courseNumList 	= courseMasterService.selectRegMemberList(startDate, endDate, branch);
		List<Map<String,Object>> holidayList 	= holidayService.selectHolidayList(param);
		model.addAttribute("courseNumList", courseNumList);
		model.addAttribute("dateList", dateList);
		model.addAttribute("today", commonService.currentDay(standardDate));
		model.addAttribute("holidayList" , holidayList);
		model.addAttribute("branch", branch);

		return "reg_course/reg_course";
	}

	@PostMapping("/insertCourse")
	@Transactional
	public String insertCourse(HttpServletRequest request,
									  @RequestParam(value = "register_start_time") String 	startDate, @RequestParam() int times,
									  @RequestParam(value = "register_end_time") String 	endDate, @RequestParam(value = "time_list") String[] timeList,
									  @RequestParam() String dayList, @RequestParam() String aliasList,
									  @RequestParam(value = "price_date", defaultValue = "1999-09-09") String priceDate, @RequestParam() String price,
									  @RequestParam(value = "price_type") String priceType, @RequestParam(value="payment-yn") int paymentYn
	) throws ParseException {
		HttpSession session = request.getSession();
		Integer memberSeq = (Integer)session.getAttribute("memberSeq");
		Integer branch = (Integer)session.getAttribute("branch");
		courseMasterService.insertCourse(memberSeq, dayList, timeList, aliasList, branch);
		courseStudentService.insertStudent(memberSeq, startDate, endDate, times, aliasList, price, priceDate, priceType, paymentYn);
		return "reg_course/success";
	}


}
