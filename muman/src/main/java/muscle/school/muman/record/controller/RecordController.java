package muscle.school.muman.record.controller;

import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.member.service.MemberService;
import muscle.school.muman.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Member;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class RecordController {
	
	@Autowired
	RecordService service;
	@Autowired
	MemberService memberService;
	@Autowired
	CommonService commonService;

	@GetMapping("/admin/create_record")
	public String createRecord(Model model) throws ParseException {
		List<Map<String, Object>> exNameList = service.getExNameList();
		model.addAttribute("exNameList", exNameList);
		return "admin/record/create_record";
	}

	@GetMapping("/admin/view_record")
	public String viewRecord(Model model, @RequestParam(required=false, defaultValue="1") String date) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd");
		if(date.equals("1") ) {
			Date time = new Date();
			date = format1.format(time);
		}

		List<Map<String, Object>> memberExList = service.getExMemberList("", date);
		List<Map<String, Object>> exNameList = service.getExNameList();
		List<Map<String, Object>> memberTotal = memberService.getTotalMember();
		System.out.println(memberExList);
		model.addAttribute("memberExList", memberExList);
		model.addAttribute("exNameList", exNameList);
		model.addAttribute("memberTotal", memberTotal);
		model.addAttribute("today", date);
		return "admin/record/view_record";
	}

	@GetMapping("/exSave")
	@ResponseBody
	public int exSave(
			@RequestParam(required = false)String EX_NAME,
			@RequestParam(required = false)String EX_HIDE,
			@RequestParam(required = false)String EX_DESC,
			@RequestParam(required = false)String EX_SEQ
			) throws ParseException {
		try {
			service.updateExRecor(EX_SEQ, EX_NAME, EX_HIDE, EX_DESC);
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}


	@GetMapping("/exUpdate")
	@ResponseBody
	public int exUpdate(
			@RequestParam(required = false)String MEMBER_SEQ,
			@RequestParam(required = false, defaultValue = "0")String EX_WEIGHT1,
			@RequestParam(required = false, defaultValue = "0")String EX_WEIGHT2,
			@RequestParam(required = false, defaultValue = "0")String EX_WEIGHT3,
			@RequestParam(required = false, defaultValue = "0")String EX_WEIGHT4,
			@RequestParam(required = false, defaultValue = "0")String EX_WEIGHT5,
			@RequestParam(required = false, defaultValue = "0")String EX_WEIGHT6,
			@RequestParam(required = false, defaultValue = "0")String EX_WEIGHT7,
			@RequestParam(required = false, defaultValue = "0")String EX_WEIGHT8,
			@RequestParam(required = false, defaultValue = "0")String EX_WEIGHT9,
			@RequestParam(required = false, defaultValue = "0")String EX_WEIGHT10,
			@RequestParam(required = false, defaultValue = "0")String EX_DATE
	) throws ParseException {
		try {
			Boolean updateYn = service.hasRecord(MEMBER_SEQ, EX_DATE);
			System.out.println(updateYn);
			if(updateYn) {
				service.updateRecord(MEMBER_SEQ, EX_WEIGHT1, EX_WEIGHT2, EX_WEIGHT3, EX_WEIGHT4, EX_WEIGHT5, EX_WEIGHT6, EX_WEIGHT7,
									 EX_WEIGHT8, EX_WEIGHT9, EX_WEIGHT10, EX_DATE
				);
			} else {
				service.insertRecord(MEMBER_SEQ, EX_WEIGHT1, EX_WEIGHT2, EX_WEIGHT3, EX_WEIGHT4, EX_WEIGHT5, EX_WEIGHT6, EX_WEIGHT7,
						             EX_WEIGHT8, EX_WEIGHT9, EX_WEIGHT10, EX_DATE
				);
			}
			return 0;
		} catch (Exception e) {
			System.out.println("error");
			return 1;
		}
	}
}
