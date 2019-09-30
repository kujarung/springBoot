package muscle.school.muman.course_student.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import muscle.school.muman.commom.dao.CommonDao;
import muscle.school.muman.course_student.dao.CourseStudentDao;

@Service
@MapperScan(basePackages = "muscle.school.muman.course_student.dao")
public class CourseStudentService {
	
	@Autowired
	CourseStudentDao dao;
	@Autowired
	CommonDao commonDao;
	public void insertMember() {
		
	}

	public void insertStudent(String member_seq, String start_date, String end_date, int times_week) {
		dao.insertStudent(member_seq, start_date, end_date, times_week);
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
