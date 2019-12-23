package muscle.school.muman.course_master.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import muscle.school.muman.holiday.service.HolidayService;
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
	@Autowired
	HolidayService holidayService;

	public List<Map<String, Object>> selectCourseMasterList(int alias) {
		return dao.selectCourseMasterList(alias);
	}

	public List<Map<String, Object>> selectRegMemberList(String startDate, String endDate, int branch) {
		return dao.selectRegMemberList(startDate, endDate, branch);
	}

	public List<Map<String, Object>> selectHolidayList() {
		// TODO Auto-generated method stub
		return dao.selectHolidayList();
	}
	
	@Transactional 
	public int insertCourse(String memberSeq, String dayListString, String[] timeList, String aliasListString
							,int branch
	) throws ParseException {
	    try {
			for (String s : timeList) {
				String[] dayList = dayListString.split("\\|");
				String[] aliasList = aliasListString.split("\\|");
				for (int j = 0; j < dayList.length; j++) {
					int day = Integer.parseInt(dayList[j]) + 1;
					if ( day == commonSerivce.getDayOfWeek(s)) {
						dao.insertCourse(memberSeq, aliasList[j], s, branch);
					}
				}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int insertOneCourse(String memberSeq, String exAlias, String endDate, int branch) {
		dao.insertCourse(memberSeq, exAlias, endDate, branch);
		return 0;
	}
	
	/**
	 *
	 * @return 
	 * */
	
	@Transactional
	@RequestMapping("/courseMasterService")
	public int delayCourse(int memberSeq, int delayNum, int branch) throws ParseException {
		System.out.println(memberSeq);
		try {
			Map<String, Object> studentDetail = courseStudentService.getCourseStudentDetail(memberSeq);
			String aliasList[] = studentDetail.get("aliasList").toString().split("\\|");
			//수강이 끝나는 마지막 일
			String endDate = studentDetail.get("end_date").toString();
			int day = commonSerivce.getDayOfWeek(endDate);
			//끝나는 날을 받음
			//끝나는 날의 요일을 구함
			for(int j=0; j< delayNum; j++) {
				int intEndDay = commonSerivce.getDayOfWeek(endDate);
				//그 요일 보다 큰 수를 alias리스트에서 찾음
				//없을 시 첫번 째 요일이 구하는 수
				int searchIntDay = Integer.parseInt(commonSerivce.aliasToDetail(aliasList[0].toString()).get("week_of_day").toString() );
				String insertAlias = aliasList[0].toString();
				for (String s : aliasList) {
					int targetDay =
							Integer.parseInt(commonSerivce.aliasToDetail(s.toString()).get("week_of_day").toString());
					if (intEndDay < targetDay) {
						searchIntDay = targetDay;
						insertAlias = s.toString();
						break;
					}
				}
				//내가 수요일, 화요일이 타깃 요일이라면?
				//화요일 = 3
				//수요일 = 4
				//결과 = 7 - 절대값(4 - 3)
				//오늘 부터 결과 값 만큼 +

				int resultDay = searchIntDay - intEndDay;
				if(searchIntDay - intEndDay <= 0) {
					resultDay = 7 - Math.abs(searchIntDay - intEndDay);
				}
				String updateDay = commonSerivce.afterDay(endDate, resultDay);
				boolean updateDayIsHoliday = holidayService.searchHoliday(updateDay);
				if( updateDayIsHoliday ) {
					j--;
					endDate = updateDay;
				} else {
					courseStudentService.updateDelay(memberSeq, updateDay);
					insertOneCourse( Integer.toString(memberSeq)   , insertAlias,  updateDay, branch);
					endDate = updateDay;
				}
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public List<Map<String, Object>> selectCourseList(int memberSeq) {
		return dao.selectCourseList(memberSeq);
	}


	public int updateDetail(int useYn, int maxPeople, int alias) {
		try {
			dao.updateDetail(useYn, maxPeople, alias);
			System.out.println(1);
			return 1;
		} catch (Exception e) {
			System.out.println(0);
			return 0;
		}
	}

	public int changePayment(Integer memberSeq) {
		try {
			dao.changePayment(memberSeq);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	public List<Map<String, Object>> searchHoildayInCourse(String startDate, String endDate) {
		return dao.searchHolidayInCourse(startDate, endDate);
	}
}
