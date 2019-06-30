package muscle.school.muman.main.service.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import muscle.school.muman.main.service.MemberVO;

@Repository
public interface MainMapper {
 
    public List<MemberVO> selectMemberList() throws Exception;
    
}
