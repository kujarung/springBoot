package muscle.school.muman.sigin.service;

import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.member.service.MemberService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
@MapperScan(basePackages = "muscle.school.muman.sign.dao")
public class SignService {

	@Autowired
	MemberService memberService;
	@Autowired
	CommonService commonService;

	public List<Map<String, Object>> signIn(String loginId, String loginPassword) {
		String encPass = commonService.encryptSHA256(loginPassword);
		return memberService.searchIdAndPass(loginId, encPass);
	}

	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "/";
	}
}
