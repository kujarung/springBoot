package muscle.school.muman.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
	List<Map<String, Object>> selectMemberList(int currentPage, String member_name);

	void insertMember(String name, String id, String pass, int branch, String member_etc, String pnum);

	Map<String, Object> getMember(int memberSeq);

    int isExistId(String id);

	List<Map<String, Object>> searchIdAndPass(String loginId, String encPass);

    void updateInfo(int memberSeq, String pnum, String etc);

    void updateDelay(String delayDate);

	List<Map<String, Object>> getTotalMember();
}
