package muscle.school.muman.member.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
	List<Map<String, Object>> selectMemberList(String member_name);

	void insertMember(String name, String id, String pass);
}
