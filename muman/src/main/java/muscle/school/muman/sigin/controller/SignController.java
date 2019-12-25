package muscle.school.muman.sigin.controller;

import muscle.school.muman.sigin.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
public class SignController {

	@Autowired
	SignService service;

	@PostMapping(value = "/signIn")
	@ResponseBody
	public int signIn(HttpServletRequest request,
					  HttpServletResponse response,
					  String loginId, String loginPassword) {
		List<Map<String, Object>> result = service.signIn(loginId, loginPassword);
		if(result != null) {
			HttpSession session = request.getSession();
			int memberSeq = Integer.parseInt(result.get(0).get("member_seq").toString() );
			int authority = Integer.parseInt(result.get(0).get("authority").toString() );
			String name = result.get(0).get("name").toString();
			session.setAttribute("memberSeq", memberSeq);
			session.setAttribute("name", name);
			session.setAttribute("authority", authority);
			return 1;
		} else {
			return 0;
		}
	}

	@GetMapping(value = "/logout")
	@ResponseBody
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "/";
	}
}
