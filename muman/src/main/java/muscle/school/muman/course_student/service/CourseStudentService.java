package muscle.school.muman.course_student.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import muscle.school.muman.commom.dao.CommonDao;
import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.course_student.dao.CourseStudentDao;

@Service
@MapperScan(basePackages = "muscle.school.muman.course_student.dao")
public class CourseStudentService {
	
	@Autowired
	CourseStudentDao dao;
	@Autowired
	CommonDao commonDao;
	@Autowired
	CommonService commonService;
	public void insertMember() {
		
	}

	public void insertStudent(String memberSeq, String startDate, String endDate, int timesWeek, String aliasList, 
			String price, String price_date, String price_type
			) {
		String alias_list_full_name= "";
		String [] tempAliasList = aliasList.split("\\|");
		for(int i=0; i< tempAliasList.length;i++) {
			int aliasNum = Integer.parseInt( tempAliasList[i].toString() );
			String aliasName = commonService.getAliasToFullName( aliasNum );
			if(i == 0 ) {
				alias_list_full_name = aliasName;
			} else {
				alias_list_full_name = alias_list_full_name + "," + aliasName;
			}
		}
		alias_list_full_name = "(" + alias_list_full_name + ")";
		dao.insertStudent(memberSeq, startDate, endDate, timesWeek, alias_list_full_name, aliasList, price, price_type, price_date);
		commonDao.nextCourseStudentSeq();
		
	}

	public List<Map<String, Object>> selectCourseStudentList(int currentPage) {
		// TODO Auto-generated method stub
		return dao.selectCourseStudentList(currentPage);
	}

	public Map<String, Object> getCourseStudentDetail(int memberSeq) {
		// TODO Auto-generated method stub
		return dao.selectCourseStudent(memberSeq);
	}

	public void updateDelay(int memberSeq, String updateDay) {
		// TODO Auto-generated method stub
		dao.updateDelay(memberSeq, updateDay);
	}
	
}
