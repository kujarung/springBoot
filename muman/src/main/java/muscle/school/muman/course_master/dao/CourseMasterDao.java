package muscle.school.muman.course_master.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseMasterDao {
	List<Map<String, Object>> selectCourseMasterList(int alias);

	List<Map<String, Object>> selectRegMemberList(String startDate, String endDate, int branch);

	List<Map<String, Object>> selectHolidayList();

	void insertCourse(String memberSeq, String exAlias, String exDate, int branch);

	List<Map<String, Object>> selectCourseList(int memberSeq);

	void updateDetail(int useYn, int maxPeople, int alias);

	void changePayment(Integer memberSeq);

	List<Map<String, Object>> searchHolidayInCourse(String startDate, String endDate);
}
