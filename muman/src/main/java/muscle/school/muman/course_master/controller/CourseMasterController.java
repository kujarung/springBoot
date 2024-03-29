package muscle.school.muman.course_master.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.holiday.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import muscle.school.muman.course_master.service.CourseMasterService;
import reactor.netty.http.server.HttpServerRequest;

@Controller
public class CourseMasterController {
	
	@Autowired
	CourseMasterService service;

	@Autowired
	CommonService commonService;

	@Autowired
	HolidayService holidayService;

	//	미루기 기능
	@RequestMapping("/couseMaster/delayCourse")
	@ResponseBody
	public void delayCourse(HttpServletResponse response, 
			@RequestParam(required = false) Integer memberSeq, 
			@RequestParam(required = false) Integer delayNum, int branch
	) throws IOException  {
		int result;
		try {
			result = service.delayCourse(memberSeq, delayNum, branch);
			PrintWriter pw;
			pw = response.getWriter();
			pw.print(result);
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//결제 상태 변경
	@RequestMapping("/couseMaster/changePayment")
	@ResponseBody
	public void changePayment(HttpServletResponse response,
							  @RequestParam(required = false) Integer memberSeq,
							  @RequestParam(required = false) Integer priceType) throws IOException  {
		int result;
		try {
			result = service.changePayment(memberSeq, priceType);
			PrintWriter pw;
			pw = response.getWriter();
			pw.print(result);
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//결제 상태 변경
	@RequestMapping("/couseMaster/changeGrade")
	@ResponseBody
	public void changeGrade(HttpServletResponse response,
							  @RequestParam(required = false) Integer memberSeq,
							  @RequestParam(required = false) Integer grade) throws IOException  {
		int result;
		try {
			result = service.changeGrade(memberSeq, grade);
			PrintWriter pw;
			pw = response.getWriter();
			pw.print(result);
			pw.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//관리자 강의 등록 페이지
	@GetMapping("/admin/reg_course")
	public String adminRegCourse() {
	    return "admin/reg_course";
	}


	//관리자 강좌 등록 페이지
	@GetMapping("/admin/course_manage")
	public String adminCourseManage(Model model, @RequestParam(required=false) String standardDate
			,@RequestParam(required=false, defaultValue= "1") int branch
	) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("branch", branch);
		//시작일과 끝일을 리턴 받음
		String[] dateList   = commonService.todayWeek(standardDate);
		String startDate 	= dateList[0];
		String endDate 		= dateList[1];
		List<Map<String,Object>> courseNumList 	= service.selectRegMemberList(startDate, endDate, branch);
		List<Map<String,Object>> holidayList 	= holidayService.selectHolidayList(param);
		model.addAttribute("courseNumList", courseNumList);
		model.addAttribute("dateList", dateList);
		model.addAttribute("today", commonService.currentDay(standardDate));
		model.addAttribute("holidayList" , holidayList);
		model.addAttribute("branch", branch);
		return "admin/course/course_manage";
	}

	// 강좌 연장
	@GetMapping("/admin/extendCourse")
	public String extendCourse(int memberSeq, Model model, @RequestParam(required=false) String standardDate
			,@RequestParam(required=false, defaultValue= "1") int branch
	) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("branch", branch);
		//시작일과 끝일을 리턴 받음
		String[] dateList   = commonService.todayWeek(standardDate);
		String startDate 	= dateList[0];
		String endDate 		= dateList[1];
		List<Map<String,Object>> courseNumList 	= service.selectRegMemberList(startDate, endDate, branch);
		List<Map<String,Object>> holidayList 	= holidayService.selectHolidayList(param);
		model.addAttribute("courseNumList", courseNumList);
		model.addAttribute("dateList", dateList);
		model.addAttribute("today", commonService.currentDay(standardDate));
		model.addAttribute("holidayList" , holidayList);
		model.addAttribute("branch", branch);
		model.addAttribute("memberSeq", memberSeq);

		return "admin/course/extendCourse";
	}
	
	@GetMapping("/admin/course_manage_detail")
	public String course_detail(Model model, int alias) {
		List<Map<String,Object>> courseDetail = service.selectCourseMasterList(alias);
		System.out.println(courseDetail);
		model.addAttribute("alias", alias);
		model.addAttribute("courseDetail", courseDetail);
		return "admin/course/course_manage_detail";
	}


	@GetMapping("/updateDetail")
	@ResponseBody
	public int updateDetail(@RequestParam(required=false) int useYn,
							   @RequestParam(required=false) int maxPeople,
							   @RequestParam(required=false) int alias
	) {
		int result;
		result = service.updateDetail(useYn, maxPeople, alias);
		return result;
	}


}
