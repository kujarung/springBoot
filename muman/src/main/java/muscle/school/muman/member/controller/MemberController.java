package muscle.school.muman.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.member.service.MemberService;


@Controller
public class MemberController {
	
	@Autowired
	MemberService service;

	@Autowired
	CommonService commonService;
	
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
		
		return "redirect:admin/veiw_member";
		
	}
	
	//회원등록
	@PostMapping("/insertMember")
	public String insertMember(
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String id,
			@RequestParam(required=false) String pass,
			@RequestParam(required=false) String branch,
			@RequestParam(required=false) String member_etc,
			@RequestParam(required=false) String pnum,
			RedirectAttributes model
			) 
	{
		int sucess = 0;
		sucess = service.insertMember(name, id, pass, branch, member_etc, pnum);
		if(sucess == 1) {
			model.addAttribute("error", "1");
			System.out.println("2");
			return "redirect:sucess";
		} else {
			System.out.println("3");
			model.addAttribute("error", "1");
			return "redirect:/sign_up";
		}
		
	}
	
	@GetMapping("/sucess")
	public String sucessRegMember() {
		return "member/insert_sucess";
	}
	
	//회원 리스트 출력
	@GetMapping("/selectMemberList")
	@ResponseBody
	public void selectMemberList( 
			@RequestParam(required = false) String member_name, 
			@RequestParam(required=false, defaultValue = "1") int currentPage,
			Model model , HttpServletResponse response) throws JSONException {
		List< Map<String,Object> > data = service.selectMemberList(currentPage, member_name);
		
		int totalCnt = Integer.parseInt( data.get(0).get("TOTAL_CNT").toString() );
		Map<String,Object> pagingInfo = commonService.calcPaging(totalCnt, currentPage, 10);
		try {
			response.setContentType("text/html;charset=UTF-8"); 

			JSONArray jsonArray = new JSONArray();
			JSONObject jso = new JSONObject();
			JSONObject result = new JSONObject();
			for(int i=0;i<data.size();i++) {
				jso.put(String.valueOf(i),data.get(i));
			}
			result.put("result",jso);
			result.put("totalCnt",totalCnt);
			result.put("pagingInfo",pagingInfo);
			result.put("currentPage",currentPage);
			jsonArray.add(result);
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
