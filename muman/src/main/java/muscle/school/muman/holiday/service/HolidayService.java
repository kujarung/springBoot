package muscle.school.muman.holiday.service;

import java.util.List;
import java.util.Map;

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
    MemberDao dao;
    @Autowired
    CommonDao commonDao;
	
    @Transactional
	public int insertMember(String name, String id, String pass, String branch, String member_etc, String pnum) {
		try {
			dao.insertMember(name, id, pass, branch, member_etc, pnum);
			commonDao.nextMemberSeq();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
    	
	}

	public List<Map<String, Object>> selectMemberList(int currentPage, String member_name) {
		return dao.selectMemberList(currentPage, member_name);
	}
}