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
	CourseMasterService courseMasterService;
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
	
	//관리자 학생 등록 페이지
	@GetMapping("/admin/reg_student")
	public String adminRegCourseStudent(Model model, @RequestParam(required=false) String standardDate
										,@RequestParam(required=false, defaultValue= "1") int branch
	) {
		//시작일과 끝일을 리턴 받음
		String[] dateList   = commonService.todayWeek(standardDate);
		String startDate 	= dateList[0];
		String endDate 		= dateList[1];
		List<Map<String,Object>> courseNumList 	= courseMasterService.selectRegMemberList(startDate, endDate, branch);
		List<Map<String,Object>> holidayList 	= holidayService.selectHolidayList();
		model.addAttribute("courseNumList", courseNumList);
		model.addAttribute("dateList", dateList);
		model.addAttribute("today", commonService.currentDay(standardDate));
		model.addAttribute("holidayList" , holidayList);
		model.addAttribute("branch", branch);
		return "admin/student/reg_student";
}

	//관리자 학생 등록 페이지
	@GetMapping("/admin/course_manage")
	public String adminCourseManage(Model model, @RequestParam(required=false) String standardDate
			,@RequestParam(required=false, defaultValue= "1") int branch
	) {
		//시작일과 끝일을 리턴 받음
		String[] dateList   = commonService.todayWeek(standardDate);
		String startDate 	= dateList[0];
		String endDate 		= dateList[1];
		List<Map<String,Object>> courseNumList 	= courseMasterService.selectRegMemberList(startDate, endDate, branch);
		List<Map<String,Object>> holidayList 	= holidayService.selectHolidayList();
		model.addAttribute("courseNumList", courseNumList);
		model.addAttribute("dateList", dateList);
		model.addAttribute("today", commonService.currentDay(standardDate));
		model.addAttribute("holidayList" , holidayList);
		return "admin/course/course_manage";
	}


	//관리자 학생 확인 페이지
	@GetMapping("/admin/view_student")
	public String adminViewCourseStudent(Model model, @RequestParam(required=false, defaultValue = "1") int currentPage) {
		List<Map<String,Object>> courseStudentList 	= courseStudentService.selectCourseStudentList(currentPage);
		if(courseStudentList.size() != 0) {
			System.out.println(courseStudentList);
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
	
	//관리자 학생 확인 페이지
	@GetMapping("/admin/detail_student")
	public String detail_student(Model model, int memberSeq) {
		Map<String, Object> courseStudent = courseStudentService.getCourseStudentDetail(memberSeq);
		List<Map<String, Object>> courseList = courseMasterService.selectCourseList(memberSeq);
		
		model.addAttribute("courseStudent", courseStudent);
		model.addAttribute("courseList", courseList);
		return "/admin/student/detail_student";
	}
	
	//관리자 강의 등록 페이지
	@GetMapping("/admin/reg_course")
	public String adminRegCourse() {
		return "admin/reg_course";
	}
	
	//관리자 회원 등록 페이지
	@GetMapping("/admin/reg_member")
	public String adminRegMember() {
		return "admin/member/reg_member";
	}	

	//관리자 회원 리스트 페이지
	@GetMapping("/admin/veiw_member")
	public String admin_veiw_member(Model model, String member_name, @RequestParam(required=false, defaultValue = "1") int currentPage) {
		List< Map<String,Object> > data = memberService.selectMemberList(currentPage, member_name);
		
		int totalCnt = Integer.parseInt( data.get(0).get("TOTAL_CNT").toString() );
		Map<String,Object> pagingInfo = commonService.calcPaging(totalCnt, currentPage, 10);
		model.addAttribute("memberList", data);
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("currentPage", currentPage);
		
		return "admin/member/veiw_member";
	}
	
	// 강좌 연장 
	@GetMapping("/admin/extendCourse")
	public String extendCourse(int memberSeq, Model model, @RequestParam(required=false) String standardDate
			,@RequestParam(required=false, defaultValue= "1") int branch
	) {
		String[] dateList   = commonService.todayWeek(standardDate);
		String startDate 	= dateList[0];
		String endDate 		= dateList[1];
		List<Map<String,Object>> courseNumList 	= courseMasterService.selectRegMemberList(startDate, endDate, branch);
		List<Map<String,Object>> holidayList 	= holidayService.selectHolidayList();
		model.addAttribute("courseNumList", courseNumList);
		model.addAttribute("dateList", dateList);
		model.addAttribute("today", commonService.currentDay(standardDate));
		model.addAttribute("holidayList" , holidayList);
		model.addAttribute("memberSeq", memberSeq);

		return "admin/extendCourse";
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
