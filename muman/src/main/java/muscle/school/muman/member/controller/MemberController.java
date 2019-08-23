package muscle.school.muman.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import muscle.school.muman.member.service.MemberService;


@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	//회원 가입 페이지
	@GetMapping("/sign_up")
	public String sign_up() {
		return "member/sign_up";
	}
	
	//로그인 페이지
	@GetMapping("/sign_in")
	public String sign_in() {
		return "member/sign_in";
	}
		
	//회원가입 로직
	@PostMapping("/insertMember")
	public void insertMember() {
		
	}
}
