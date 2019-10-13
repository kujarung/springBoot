package muscle.school.muman.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import muscle.school.muman.member.service.MemberService;


@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
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
	
	//회원 가입 admin
	@PostMapping("/admin/insertMember")
	public String insertAdminMember(
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String id,
			@RequestParam(required=false) String pass,
			@RequestParam(required=false) String branch,
			@RequestParam(required=false) String member_etc,
			@RequestParam(required=false) String pnum
			) {
		service.insertMember(name, id, pass, branch, member_etc, pnum);
		
		return "redirect:admin/admin_veiw_member";
		
	}
	
	//회원 리스트 출력
	@GetMapping("/selectMemberList")
	@ResponseBody
	public void selectMemberList( @RequestParam(required = false) String member_name, Model model , HttpServletResponse response) throws JSONException {
		List< Map<String,Object> > data = service.selectMemberList(member_name);
		try {
			response.setContentType("text/html;charset=UTF-8"); 

			JSONArray jsonArray = new JSONArray();
			JSONObject jso = new JSONObject();
			JSONObject result = new JSONObject();
			for(int i=0;i<data.size();i++) {
				jso.put(String.valueOf(i),data.get(i));
			}
			result.put("result",jso);
			jsonArray.add(result);
			System.out.println(result);
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
