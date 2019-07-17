package muscle.school.muman.exercise.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository
public interface ExerciseDao {

	void insertExData( Map<String, Object> param);
	void insertExContent( Map<String, Object> param);
	int selectExMasterSeq();
 
    
}
