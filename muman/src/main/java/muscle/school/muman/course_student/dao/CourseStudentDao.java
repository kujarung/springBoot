package muscle.school.muman.course_student.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository
public interface CourseStudentDao {

	void insertStudent(String member_seq, String start_date, String end_date, int times_week, String alias_list_full_name, String aliasList);

	List<Map<String, Object>> selectCourseStudentList(int currentPage);

	Map<String, Object> selectCourseStudent(int memberSeq);

	void updateEndDate(int member_seq, String updateDay);
	
}
