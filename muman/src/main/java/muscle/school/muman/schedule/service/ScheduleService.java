package muscle.school.muman.schedule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import muscle.school.muman.schedule.dao.ScheduleDao;

@Service
@MapperScan(basePackages = "muscle.school.muman.schedule.dao")
public class ScheduleService {

	@Autowired
	ScheduleDao dao;

	public void insertCourse(HttpServletRequest request) throws Exception {
		Map<String, Object> params = new HashMap<>();
		//course_seq,start_date,end_date,title,contents,week,start_time,end_time
		params.put("start_date",request.getParameter("startDate") );
		params.put("end_date",request.getParameter("endDate") );
		params.put("title",request.getParameter("course_title") );
		params.put("contents",request.getParameter("contents") );
		params.put("week",request.getParameter("week_total") );
		params.put("start_time",request.getParameter("startTime") );
		params.put("end_time",request.getParameter("endTime") );

		System.out.println( request.getParameter("startDate")  );
		System.out.println( request.getParameter("endDate")  );
		System.out.println( request.getParameter("course_title")  );
		System.out.println( request.getParameter("contents")  );
		System.out.println( request.getParameter("week_total")  );
		System.out.println( request.getParameter("startTime")  );
		System.out.println( request.getParameter("endTime")  );
		dao.insertCourse(params);
	}

	public List<Map<String, Object>> SelectListCheckSchedule() {
		return dao.SelectListCheckSchedule();
	}

}

