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
		return "sign/sign_up";
	}
	
	//로그인 페이지
	@GetMapping("/sign_in")
	public String sign_in() {
		
		return "sign/sign_in";
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
		String encPass = commonService.encryptSHA256(pass);
		service.insertMember(name, id, encPass, branch, member_etc, pnum);
		return "redirect:veiw_member";
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
			return "redirect:admin/member/success";
		} else {
			model.addAttribute("error", "1");
			return "redirect:/sign_up";
		}
		
	}


	//관리자 회원 리스트 페이지
	@GetMapping("/admin/veiw_member")
	public String admin_veiw_member(Model model, String member_name, @RequestParam(required=false, defaultValue = "1") int currentPage) {
		List< Map<String,Object> > data = service.selectMemberList(currentPage, member_name);

		int totalCnt = Integer.parseInt( data.get(0).get("TOTAL_CNT").toString() );
		Map<String,Object> pagingInfo = commonService.calcPaging(totalCnt, currentPage, 10);
		model.addAttribute("memberList", data);
		model.addAttribute("pagingInfo", pagingInfo);
		model.addAttribute("currentPage", currentPage);

		return "admin/member/veiw_member";
	}

	//관리자 회원 등록 페이지
	@GetMapping("/admin/reg_member")
	public String adminRegMember() {
		return "admin/member/reg_member";
	}


	@GetMapping("/sucess")
	public String sucessRegMember() {
		return "member/insert_sucess";
	}


//	아이디가 있는 지 여부
	@GetMapping("/searchId")
	@ResponseBody
	public boolean searchId(@RequestParam(required = false) String id) {
		int result = service.isExistId(id);
		if(result > 0) {
			return true;
		} else {
			return false;
		}
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
