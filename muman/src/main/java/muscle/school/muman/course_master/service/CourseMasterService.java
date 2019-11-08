package muscle.school.muman.course_master.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public int insertCourse(String memberSeq, String dayListString, String[] timeList, String aliasListString) throws ParseException {
	    try {
			for(int i=0;i < timeList.length;i++) {
		    	String[] dayList 	= dayListString.split("\\|");
		    	String[] aliasList 	= aliasListString.split("\\|");
		    	for(int j=0; j<dayList.length;j++) {
		    		if ( Integer.parseInt(dayList[j]) == commonSerivce.getDayOfWeek(timeList[i])  ) {
		    			dao.insertCourse(memberSeq, aliasList[j], timeList[i]);
		    		}
		    	}
		    }
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int insertOneCourse(String memberSeq, String exAlias, String endDate) {
		dao.insertCourse(memberSeq, exAlias, endDate);
		return 0;
	}
	
	/**
	 * @param member_seq
	 * @param delay_num
	 * 
	 * @return 
	 * */
	
	@Transactional
	@RequestMapping("/courseMasterService")
	public int delayCourse(int memberSeq, int delayNum) throws ParseException {
		System.out.println(memberSeq);
		try {
			for(int j=0; j< delayNum; j++) {
				Map<String, Object> studentDetail = courseStudentService.getCourseStudentDetail(memberSeq);
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
				String insertAlias = aliasList[0].toString();
				for(int i=0; i<aliasList.length;i++) {
					int targetDay = 
							Integer.parseInt(commonSerivce.aliasToDetail(aliasList[i].toString()).get("week_of_day").toString());
					if(day < targetDay) {
						serchIntDay = targetDay;
						insertAlias =  aliasList[i].toString();
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
				courseStudentService.updateDelay(memberSeq, updateDay);
				insertOneCourse( Integer.toString(memberSeq)   , insertAlias,  updateDay); 
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
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
		return dao.selectCourseList(memberSeq);
	}


}
