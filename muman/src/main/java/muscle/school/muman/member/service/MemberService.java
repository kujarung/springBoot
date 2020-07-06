package muscle.school.muman.member.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import muscle.school.muman.commom.dao.CommonDao;
import muscle.school.muman.member.dao.MemberDao;

@Service
@MapperScan(basePackages = "muscle.school.muman.member.dao")
public class MemberService {
	
    @Autowired
    MemberDao dao;
    @Autowired
    CommonDao commonDao;

    @Transactional
	public int insertMember(String name, String id, String pass, int branch, String member_etc, String pnum) {
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

	public Map<String, Object> getMember(int memberSeq) {
		return dao.getMember(memberSeq);
	}

	public List<Map<String, Object>> getTotalMember() {return dao.getTotalMember(); }

    public int isExistId(String id) {
    	return dao.isExistId(id);
    }

	public List<Map<String, Object>> searchIdAndPass(String loginId, String encPass) {
    	List<Map<String, Object>> result = dao.searchIdAndPass(loginId, encPass);
    	if(result.size() == 0) {
    		return null;
		}
    	return result;
	}


	public void updateInfo(int memberSeq, String pnum, String etc) {
    	dao.updateInfo(memberSeq, pnum, etc);
	}

	public void updateDelay(String delayDate) {
    	dao.updateDelay(delayDate);
	}
}
