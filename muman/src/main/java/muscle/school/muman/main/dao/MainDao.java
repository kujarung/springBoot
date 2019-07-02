package muscle.school.muman.main.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import muscle.school.muman.main.vo.MemberVO;

@Repository
public interface MainDao {
 
    public List<MemberVO> selectMemberList() throws Exception;
    
}
