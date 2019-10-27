package muscle.school.muman.admin.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import muscle.school.muman.admin.service.AdminService;
import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.course_student.service.CourseStudentService;
import muscle.school.muman.member.service.MemberService;

@Controller
@EnableAutoConfiguration
public class AdminController {
	
	@Autowired
	AdminService adminService;
	@Autowired
	CourseMasterService courseMasterService;
	@Autowired
	MemberService memberService;
	@Autowired
	CommonService commonService;
	@Autowired
	CourseStudentService courseStudentService;
	
	//관리자 메인 페이지
	@GetMapping("/admin/index")
	public String adminMain() {
		return "admin/index";
	}
	
	//관리자 학생 등록 페이지
	@GetMapping("/admin/admin_reg_course_student")
	public String adminRegCourseStudent(Model model, @RequestParam(required=false) String standardDate) {
		//시작일과 끝일을 리턴 받음
		String[] dateList   = commonService.todayWeek(standardDate);
		String startDate 	= dateList[0];
		String endDate 		= dateList[1];
		List<Map<String,Object>> courseNumList 	= courseMasterService.selectRegMemberList(startDate, endDate);
		List<Map<String,Object>> holidayList 	= courseMasterService.selectHolidayList();
		model.addAttribute("courseNumList", courseNumList);
		model.addAttribute("dateList", dateList);
		model.addAttribute("today", commonService.currentDay(standardDate));
		model.addAttribute("holidayList" , holidayList);
		return "admin/admin_reg_course_student";
	}
	
	//관리자 학생 확인 페이지
	@GetMapping("/admin/admin_view_course_student")
	public String adminViewCourseStudent(Model model, @RequestParam(required=false, defaultValue = "1") int currentPage) {
		List<Map<String,Object>> courseStudentList 	= courseStudentService.selectCourseStudentList(currentPage);
		
		int totalCnt = Integer.parseInt( courseStudentList.get(0).get("TOTAL_CNT").toString());
		Map<String,Object> pagingInfo = commonService.calcPaging(totalCnt, currentPage, 10);
		model.addAttribute("courseStudentList", courseStudentList);
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("currentPage", currentPage);
		return "admin/admin_view_course_student";
	}
	
	//관리자 강의 등록 페이지
	@GetMapping("/admin/admin_reg_course")
	public String adminRegCourse() {
		return "admin/admin_reg_course";
	}
	
	//관리자 회원 등록 페이지
	@GetMapping("/admin/admin_reg_member")
	public String adminRegMember() {
		return "admin/admin_reg_member";
	}	

	//관리자 회원 리스트 페이지
	@GetMapping("/admin/admin_veiw_member")
	public String admin_veiw_member(Model model, String member_name, @RequestParam(required=false, defaultValue = "1") int currentPage) {
		List< Map<String,Object> > data = memberService.selectMemberList(currentPage, member_name);
		
		int totalCnt = Integer.parseInt( data.get(0).get("TOTAL_CNT").toString() );
		Map<String,Object> pagingInfo = commonService.calcPaging(totalCnt, currentPage, 10);
		model.addAttribute("memberList", data);
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("currentPage", currentPage);
		
		return "admin/admin_veiw_member";
	}	
	
	
	@RequestMapping("/test")
	public void test() {
		System.out.println("킹치웠나?");
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(cal.YEAR) );
		System.out.println(cal.get(cal.MONTH) );
		System.out.println(cal.get(cal.DATE) );
		System.out.println(cal.get(cal.DAY_OF_WEEK) );
		cal.set(2019, 11, 31);
		System.out.println(cal.get(cal.YEAR) );
		System.out.println(cal.get(cal.MONTH) );
		System.out.println(cal.get(cal.DATE) );
		System.out.println(cal.get(cal.DAY_OF_WEEK) );
	}
	

}
