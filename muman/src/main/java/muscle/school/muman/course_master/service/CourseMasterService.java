package muscle.school.muman.course_master.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


	public List<Map<String, Object>> selectRegMemberList() {
		return dao.selectRegMemberList();
	}
	
	public List<Map<String, Object>> selectRegNumList(String startDate, String endDate) {
		return dao.selectRegNumList(startDate, endDate);
	}
}