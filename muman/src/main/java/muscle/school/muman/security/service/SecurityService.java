package muscle.school.muman.security.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import muscle.school.muman.schedule.dao.ScheduleDao;

@Service
@MapperScan(basePackages = "muscle.school.muman.security.dao")
public class SecurityService {

	@Autowired
	ScheduleDao dao;

	
	
}

