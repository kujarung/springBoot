package muscle.school.muman.course_master.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface CourseMasterDao {
	List<Map<String, Object>> selectCourseMasterList(int alias);

	List<Map<String, Object>> selectRegMemberList(String startDate, String endDate, int branch);

	List<Map<String, Object>> selectHolidayList();

	void insertCourse(String memberSeq, String exAlias, String exDate, int branch);

	List<Map<String, Object>> selectCourseList(int memberSeq);

}
