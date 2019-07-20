package muscle.school.muman.exercise.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository
public interface ExerciseDao {

	void insertExData( Map<String, Object> param);
	void insertExContent( Map<String, Object> param);
	int selectExMasterSeq();
	List<Map<String, Object>> serchExData(int memberSeq);
	List<Map<String, Object>> serchExDetail(int ex_seq);
 
    
}
