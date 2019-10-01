package muscle.school.muman.member.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import muscle.school.muman.commom.dao.CommonDao;
import muscle.school.muman.member.dao.MemberDao;

@Service
@MapperScan(basePackages = "muscle.school.muman.member.dao")
public class MemberService {
	
    @Autowired
    MemberDao dao;
    @Autowired
    CommonDao commonDao;
	
	public void insertMember(String name, String id, String pass) {
		dao.insertMember(name, id, pass);
		commonDao.nextMemberSeq();
	}

	public List<Map<String, Object>> selectMemberList(String member_name) {
		return dao.selectMemberList(member_name);
	}
}
