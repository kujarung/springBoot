package muscle.school.muman.course_master.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface CourseMasterDao {
	List<Map<String, Object>> selectCourseMaterList();

	List<Map<String, Object>> selectRegMemberList(String startDate, String endDate);

	List<Map<String, Object>> selectHolidayList();

	void insertCourse(String member_seq, String ex_alias, String ex_date);
	
}
