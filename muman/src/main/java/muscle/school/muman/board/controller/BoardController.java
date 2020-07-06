package muscle.school.muman.board.controller;

import muscle.school.muman.board.service.BoardtService;
import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.holiday.service.HolidayService;
import muscle.school.muman.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {
	
	@Autowired
	BoardtService service;
	@Autowired
	MemberService memberService;
	@Autowired
	CourseMasterService courseMasterService;
	@Autowired
	BoardtService courseStudentService;
	@Autowired
	CommonService commonService;
	@Autowired
	HolidayService holidayService;

	@RequestMapping(value="/board")
	public String boardList() {
		System.out.println("!!1");
		return "board/boardList";
	}

	@RequestMapping(value="/board/:id/detail")
	public String boardDetail(@PathVariable String id) {
		System.out.println(id);
		return "board/boardDetail";
	}
}
