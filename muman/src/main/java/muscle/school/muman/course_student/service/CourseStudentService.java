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

	public void insertStudent(String member_seq, String start_date, String end_date, int times_week, String aliasList) {
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
		dao.insertStudent(member_seq, start_date, end_date, times_week, alias_list_full_name, aliasList);
		commonDao.nextCourseStudentSeq();
		
	}
	
	public void updateStudent(String member_seq, String register_start_time, String register_end_time) {
		// TODO Auto-generated method stub
		
	}

	public List<Map<String, Object>> selectCourseStudentList() {
		// TODO Auto-generated method stub
		return dao.selectCourseStudentList();
	}
	
}
