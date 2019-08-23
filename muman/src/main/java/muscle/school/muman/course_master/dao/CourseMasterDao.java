package muscle.school.muman.course_master.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface CourseMasterDao {

	List<Map<String, Object>> selectCourseMaterList();
}
