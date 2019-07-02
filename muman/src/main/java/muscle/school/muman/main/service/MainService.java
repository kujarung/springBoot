package muscle.school.muman.main.service;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import muscle.school.muman.main.dao.MainDao;
import muscle.school.muman.main.vo.MemberVO;

@Service
@MapperScan(basePackages = "muscle.school.muman.main.dao")
public class MainService {
	
    @Autowired
    MainDao dao;

	public List<MemberVO> selectMemberList() throws Exception {
		return dao.selectMemberList();
	}

}

