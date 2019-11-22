package muscle.school.muman.holiday.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import muscle.school.muman.holiday.service.HolidayService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.member.service.MemberService;


@Controller
public class HolidayController {
	
	@Autowired
	MemberService service;
	@Autowired
	CommonService commonService;
	@Autowired
	HolidayService holidayService;

	@RequestMapping("/holiday_list")
	public String holidayList(Model model) {
		List<Map<String,Object>> holidayList = holidayService.selectHolidayList();

		model.addAttribute("holidayList", holidayList);
		return "admin/holiday/holiday_list";
	}
	
	
}
