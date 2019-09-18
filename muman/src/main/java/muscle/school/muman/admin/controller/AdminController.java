package muscle.school.muman.admin.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	        standardDate = "2019-09-23";
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
        
		List<Map<String,Object>> courseNumList = courseMasterService.selectRegMemberList();
		System.out.println(startDate);
		System.out.println(endDate);
		List<Map<String,Object>> regNumList =    courseMasterService.selectRegNumList(startDate, endDate);
		model.addAttribute("courseNumList", courseNumList);
		model.addAttribute("regNumList", regNumList);
		System.out.println(courseNumList);
		System.out.println(regNumList);
		return "admin/admin_reg_course_student";
	}
	
	//관리자 강의 등록 페이지
	@GetMapping("/admin/admin_reg_course")
	public String adminRegCourse() {
		return "admin/admin_reg_course";
	}
	
	public String formatDate(Calendar currentCalendar) {
		return currentCalendar.get(Calendar.DAY_OF_YEAR) + "-" + currentCalendar.get(Calendar.DAY_OF_MONTH) + "-" + currentCalendar.get(Calendar.DATE);
	}
}
