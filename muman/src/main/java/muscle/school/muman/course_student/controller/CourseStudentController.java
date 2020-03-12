package muscle.school.muman.course_student.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.holiday.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
	@Autowired
	CommonService commonService;
	@Autowired
	HolidayService holidayService;

	@PostMapping("/courseStudent/insertCourseStudent")
	@Transactional
	public String insertCourseStudent(HttpServletRequest req, 					  							@RequestParam(value = "member_seq") int memberSeq,
									  @RequestParam(value = "register_start_time") String 	startDate, 		@RequestParam() int times,
									  @RequestParam(value = "register_end_time") String 	endDate,   		@RequestParam(value = "time_list") String[] timeList,
									  @RequestParam() String dayList, 			  														@RequestParam() String aliasList,
									  @RequestParam(value = "price_date", defaultValue = "1999-09-09") String priceDate, 			  	@RequestParam() String price,
									  @RequestParam(value = "price_type") String priceType,
									  @RequestParam(value="insertBranch") int branch, @RequestParam(value="payment-yn") int paymentYn
	) throws ParseException {

		courseMasterService.insertCourse(memberSeq, dayList, timeList, aliasList, branch);
		courseStudentService.insertStudent(memberSeq, startDate, endDate, times, aliasList, price, priceDate, priceType, paymentYn);
		return "admin/common/success";
	}


	@PostMapping("/courseStudent/updateCourseStudent")
	@Transactional
	public String updateCourseStudent(HttpServletRequest req, 					  							@RequestParam(value = "member_seq") int memberSeq,
									  @RequestParam() int times,
									  @RequestParam(value = "register_end_time") String 	endDate,   		@RequestParam(value = "time_list") String[] timeList,
									  @RequestParam() String dayList, 			  														@RequestParam() String aliasList,
									  @RequestParam(value = "price_date", defaultValue = "1999-09-09") String priceDate, 			  	@RequestParam() String price,
									  @RequestParam(value = "price_type") String priceType,
									  @RequestParam(value="insertBranch") int branch, @RequestParam(value="payment-yn") int paymentYn
	) throws ParseException {

		courseMasterService.insertCourse(memberSeq, dayList, timeList, aliasList, branch);
		courseStudentService.updateCourseStudent(memberSeq, endDate, times, aliasList, price, priceDate, priceType, paymentYn);
		return "admin/common/success";
	}

	//관리자 학생 확인 페이지
	@GetMapping("/admin/view_student")
	public String adminViewCourseStudent(Model model, @RequestParam(required=false, defaultValue = "1") int currentPage) {
		List<Map<String,Object>> courseStudentList 	= courseStudentService.selectCourseStudentList(currentPage);

		System.out.println(courseStudentList);
		if(courseStudentList.size() != 0) {
			int totalCnt = Integer.parseInt( courseStudentList.get(0).get("TOTAL_CNT").toString());
			Map<String,Object> pagingInfo = commonService.calcPaging(totalCnt, currentPage, 10);
			model.addAttribute("courseStudentList", courseStudentList);
			model.addAttribute("pagingInfo", pagingInfo);
			model.addAttribute("currentPage", currentPage);
		} else {
			Map<String,Object> pagingInfo = commonService.calcPaging(0, currentPage, 10);
			model.addAttribute("courseStudentList", "");
			model.addAttribute("pagingInfo", pagingInfo);
			model.addAttribute("currentPage", currentPage);
		}
		return "admin/student/view_student";
	}

	//관리자 학생 등록 페이지
	@GetMapping("/admin/reg_student")
	public String adminRegCourseStudent(Model model, @RequestParam(required=false) String standardDate
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
		return "admin/student/reg_student";
	}

	//관리자 확인 페이지
	@GetMapping("/admin/detail_student")
	public String detail_student(HttpServletRequest req, Model model) {
		int memberSeq = Integer.parseInt(req.getParameter("memberSeq").toString() );
		Map<String, Object> courseStudent = courseStudentService.getCourseStudentDetail(memberSeq);
		List<Map<String, Object>> courseList = courseMasterService.selectCourseList(memberSeq);

		model.addAttribute("courseStudent", courseStudent);
		model.addAttribute("courseList", courseList);
		return "/admin/student/detail_student";
	}

	public void updateDealy(int memberSeq, String delayDate) {
		service.updateDealy(memberSeq, delayDate);
	}
}
