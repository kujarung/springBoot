package muscle.school.muman.commom.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface CommonDao {

	void nextCourseStudentSeq();

	void nextMemberSeq();

	
}
