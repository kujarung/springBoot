package muscle.school.muman.admin.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.member.service.MemberService;

@Controller
@EnableAutoConfiguration
public class AdminController {
	
	@Autowired
	AdminService  adminService;
	@Autowired
	CourseMasterService courseMasterService;
	@Autowired
	MemberService memberService;
	
	//관리자 메인 페이지
	@GetMapping("/admin/index")
	public String adminMain() {
		return "admin/index";
	}
	
	//관리자 학생 등록 페이지
	@GetMapping("/admin/admin_reg_course_student")
	public String adminRegCourseStudent(Model model,@RequestParam(required=false) String standardDate) {
		//시작일과 끝일을 리턴 받음
		String[] dateList   = todayWeek(standardDate);
		String startDate 	= dateList[0];
		String endDate 		= dateList[1];
		List<Map<String,Object>> courseNumList 	= courseMasterService.selectRegMemberList(startDate, endDate);
		List<Map<String,Object>> holidayList 	= courseMasterService.selectHolidayList();
		model.addAttribute("courseNumList", courseNumList);
		model.addAttribute("dateList", dateList);
		model.addAttribute("today", currentDay(standardDate));
		model.addAttribute("holidayList" , holidayList);
		return "admin/admin_reg_course_student";
	}
	
	//관리자 강의 등록 페이지
	@GetMapping("/admin/admin_reg_course")
	public String adminRegCourse() {
		return "admin/admin_reg_course";
	}
	
	//월요일과 금요일 날을 리턴 하는 함수
	public String[] todayWeek(String standardDate) {
		String[] result = new String[2];
		String startDate ="";
		String endDate = "";
        Calendar currentCalendar = Calendar.getInstance();
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
        if(standardDate == "" || standardDate == null) {
		    //이번주 첫째 날짜  
        	currentCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        	startDate = dateFmt.format(currentCalendar.getTime());
        	//이번주 마지막 날짜  
        	currentCalendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        	endDate = dateFmt.format(currentCalendar.getTime()); 
		} else {
			Calendar newCal = Calendar.getInstance();
	        int mYear = Integer.parseInt( standardDate.split("-")[0].toString());
	        int mMonth = Integer.parseInt( standardDate.split("-")[1].toString()) - 1;
	        int mDate = Integer.parseInt( standardDate.split("-")[2].toString());
	        newCal.set ( mYear, mMonth, mDate);
	        newCal.getTime();
		    //이번주 첫째 날짜  
			newCal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
	    	startDate = dateFmt.format(newCal.getTime());
	    	//이번주 마지막 날짜  
	    	newCal.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY); 
	        endDate = dateFmt.format(newCal.getTime());
		}
        result[0] = startDate;
        result[1] = endDate;
		return result;
	}
	
	public String currentDay(String standardDate) {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		Calendar today =  Calendar.getInstance();
		if(standardDate == null) {
			return format1.format(today.getTime());
		} else {
			return standardDate;
		}
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
	
	//yyyy-mm-dd 형태로 포메팅 하는 함수
	public String formatDate(Calendar currentCalendar) {
		return currentCalendar.get(Calendar.DAY_OF_YEAR) + "-" + currentCalendar.get(Calendar.DAY_OF_MONTH) + "-" + currentCalendar.get(Calendar.DATE);
	}
}
