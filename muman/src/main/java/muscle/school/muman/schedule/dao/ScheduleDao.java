package muscle.school.muman.schedule.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleDao {
	public void insertCourse(Map<String, Object> params);

	public List<Map<String, Object>> SelectListCheckSchedule();
}
