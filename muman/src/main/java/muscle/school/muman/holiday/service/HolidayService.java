package muscle.school.muman.holiday.service;

import java.util.List;
import java.util.Map;

import muscle.school.muman.holiday.dao.HolidayDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import muscle.school.muman.commom.dao.CommonDao;
import muscle.school.muman.member.dao.MemberDao;

@Service
@MapperScan(basePackages = "muscle.school.muman.holiday.dao")
public class HolidayService {
	
    @Autowired
	HolidayDao dao;
    @Autowired
    CommonDao commonDao;
	

    public List<Map<String, Object>> selectHolidayList() {
		return dao.selectHolidayList();

    }
}
