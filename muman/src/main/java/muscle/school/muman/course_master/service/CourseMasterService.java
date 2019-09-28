package muscle.school.muman.course_master.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import muscle.school.muman.course_master.dao.CourseMasterDao;

@Service
@MapperScan(basePackages = "muscle.school.muman.course_master.dao")
public class CourseMasterService {
	
	@Autowired
	CourseMasterDao dao;
	
	public void insertMember() {
		
	}
	
	public List<Map<String, Object>> selectCourseMaterList() {
		return dao.selectCourseMaterList();
	}


	public List<Map<String, Object>> selectRegMemberList(String startDate, String endDate) {
		return dao.selectRegMemberList(startDate, endDate);
	}

	public List<Map<String, Object>> selectHolidayList() {
		// TODO Auto-generated method stub
		return dao.selectHolidayList();
	}

	@Transactional 
	public void insertCourse(String member_seq, String dayListString, String[] time_list, String aliasListString) throws ParseException {
	    for(int i=0;i < time_list.length;i++) {
	    	String[] dayList 	= dayListString.split("\\|");
	    	String[] aliasList 	= aliasListString.split("\\|");
	    	for(int j=0; j<dayList.length;j++) {
	    		if ( Integer.parseInt(dayList[j]) == getDayOfWeek(time_list[i])  ) {
	    			dao.insertCourse(member_seq, aliasList[j], time_list[i]);
	    		}
	    	}
	    }
	}
	
	public int getDayOfWeek(String currentDate) throws ParseException {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd") ;
	    Date nDate = dateFormat.parse(currentDate) ;
	    Calendar cal = Calendar.getInstance() ;
	    cal.setTime(nDate);
	    int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
		return dayNum;
	}
}
