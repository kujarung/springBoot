package muscle.school.muman.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import muscle.school.muman.main.vo.MemberVO;

@Repository
public interface MainDao {
    void insertReview(String name, String content);

    List<Map<String, Object>> getReview();
}
