package muscle.school.muman.holiday.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface HolidayDao {
	List<Map<String, Object>> selectMemberList(int currentPage, String member_name);

	void insertMember(String name, String id, String pass, String branch, String member_etc, String pnum);

    List<Map<String, Object>> selectHolidayList();
}
