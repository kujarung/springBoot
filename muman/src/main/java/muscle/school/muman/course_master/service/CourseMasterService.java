package muscle.school.muman.course_master.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.course_master.dao.CourseMasterDao;

@Service
@MapperScan(basePackages = "muscle.school.muman.course_master.dao")
public class CourseMasterService {
	
	@Autowired
	CourseMasterDao dao;
	@Autowired
	CommonService commonSerivce;
	
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
	

}
