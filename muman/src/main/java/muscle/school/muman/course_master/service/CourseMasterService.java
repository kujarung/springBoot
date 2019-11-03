package muscle.school.muman.course_master.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.course_master.dao.CourseMasterDao;
import muscle.school.muman.course_student.service.CourseStudentService;

@Service
@MapperScan(basePackages = "muscle.school.muman.course_master.dao")
public class CourseMasterService {
	
	@Autowired
	CourseMasterDao dao;
	@Autowired
	CommonService commonSerivce;
	@Autowired
	CourseStudentService courseStudentService;
	
	
	public void insertMember() {
		
	}
	
	public List<Map<String, Object>> selectCourseMasterList(int alias) {
		return dao.selectCourseMasterList(alias);
	}


	public List<Map<String, Object>> selectRegMemberList(String startDate, String endDate) {
		return dao.selectRegMemberList(startDate, endDate);
	}

	public List<Map<String, Object>> selectHolidayList() {
		// TODO Auto-generated method stub
		return dao.selectHolidayList();
	}

	@Transactional 
	public int insertCourse(String member_seq, String dayListString, String[] time_list, String aliasListString) throws ParseException {
	    try {
			for(int i=0;i < time_list.length;i++) {
		    	String[] dayList 	= dayListString.split("\\|");
		    	String[] aliasList 	= aliasListString.split("\\|");
		    	for(int j=0; j<dayList.length;j++) {
		    		if ( Integer.parseInt(dayList[j]) == commonSerivce.getDayOfWeek(time_list[i])  ) {
		    			dao.insertCourse(member_seq, aliasList[j], time_list[i]);
		    		}
		    	}
		    }
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public int delayCourse(int member_seq, int delay_num) throws ParseException {

		String firstEndDate = "2019-11-22";
		for(int j=0; j< delay_num; j++) {
			Map<String, Object> studentDetail = courseStudentService.getCourseStudentDetail(member_seq);
			String aliasList[] = studentDetail.get("aliasList").toString().split("\\|");
			//수강이 끝나는 마지막 일
			String endDate = studentDetail.get("end_date").toString();
			int day = commonSerivce.getDayOfWeek(endDate);
			
			//끝나는 날을 받음
			//끝나는 날의 요일을 구함
			int intEndDay = commonSerivce.getDayOfWeek(endDate);
			//그 요일 보다 큰 수를 alias리스트에서 찾음
			//없을 시 첫번 째 요일이 구하는 수
			int firstTargetDay 
			= Integer.parseInt(commonSerivce.aliasToDetail(aliasList[0].toString()).get("week_of_day").toString() );
			int serchIntDay =  firstTargetDay;
			for(int i=0; i<aliasList.length;i++) {
				int targetDay = Integer.parseInt(
								commonSerivce.aliasToDetail(aliasList[i].toString()).get("week_of_day").toString() );
				if(day < targetDay) {
					serchIntDay = targetDay;
				}
			}
			//내가 수요일, 화요일이 타깃 요일이라면?
			//화요일 = 3
			//수요일 = 4
			//결과 = 7 - 절대값(4 - 3)
			//오늘 부터 결과 값 만큼 +
			
			int resultDay = serchIntDay - intEndDay;
			if(serchIntDay - intEndDay < 0) {
				resultDay = 7 - Math.abs(serchIntDay - intEndDay);
			}
			
			String updateDay = commonSerivce.afterDay(endDate, resultDay);
			System.out.println(updateDay);
			courseStudentService.updateEndDate(member_seq, updateDay);
		}
		courseStudentService.updateEndDate(member_seq, firstEndDate);
		return 1;
	}
	
	public static void main(String[] args) {
		String a = "3|16|29";
		String[] b = a.split("\\|");
        for(int i=0 ; i<b.length ; i++)
        {
            System.out.println("date["+i+"] : "+b[i]);
        }
	}

	public List<Map<String, Object>> selectCourseList(int memberSeq) {
		System.out.println(memberSeq);
		
		return dao.selectCourseList(memberSeq);
	}
}
