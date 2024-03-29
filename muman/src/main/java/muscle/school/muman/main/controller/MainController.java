package muscle.school.muman.main.controller;

import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.course_student.controller.CourseStudentController;
import muscle.school.muman.holiday.service.HolidayService;
import muscle.school.muman.member.service.MemberService;
import muscle.school.muman.util.ExcelRead;
import muscle.school.muman.util.ExcelReadOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import muscle.school.muman.main.service.MainService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@Controller
@EnableAutoConfiguration
public class MainController {
    @Autowired
    CourseMasterService courseMasterService;

    HolidayService holidayService;
    @Autowired
    MainService service;
    @Autowired
    MemberService memberService;
    @Autowired
    CourseStudentController courseStudentController;
    @Autowired
    CommonService commonService;

    //메인 화면으로 이동
    @RequestMapping(value = "/", method=RequestMethod.GET)
    public String main(HttpServletRequest request, Model model) throws Exception {
        HttpSession session = request.getSession();
        Integer memberSeq = (Integer)session.getAttribute("memberSeq");
        model.addAttribute("memberSeq", memberSeq);
        return "index";
    }

    @RequestMapping(value="/popup", method=RequestMethod.GET)
    public String popup() {
        return "popup";
    }

    //강사 소개 페이지로 이동
    @RequestMapping(value = "/intro_teacher", method=RequestMethod.GET)
    public String introTeacher() {
        return "intro_teacher/intro_teacher";
    }

    //학교 소개 페이지로 이동
    @RequestMapping(value = "/intro_school", method=RequestMethod.GET)
    public String introSchool() {
        return "intro_school/intro_school";
    }
    
    //강좌 소개 페이지로 이동
    @RequestMapping(value = "/intro_course", method=RequestMethod.GET)
    public String intro_course(
            Model model, @RequestParam(required=false) String standardDate
            ,@RequestParam(required=false, defaultValue= "1") int branch
    ) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("branch", branch);
        //시작일과 끝일을 리턴 받음
        String[] dateList   = commonService.todayWeek(standardDate);
        String startDate 	= dateList[0];
        String endDate 		= dateList[1];
        List<Map<String,Object>> courseNumList 	= courseMasterService.selectRegMemberList(startDate, endDate, branch);
        model.addAttribute("courseNumList", courseNumList);
        model.addAttribute("dateList", dateList);
        model.addAttribute("today", commonService.currentDay(standardDate));
        model.addAttribute("branch", branch);

        return "intro_course/intro_course";
    }

    @RequestMapping(value = "/map")
    public String map() {
        return "map/map";
    }


    @RequestMapping(value = "/success")
    public String success() {
        return "sign/success";
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/person")
    public String person() {
        return "privacy/person";
    }

    @RequestMapping(value = "/terms")
    public String terms() {
        return "privacy/terms";
    }

    @ResponseBody
    @Transactional
    @RequestMapping(value = "/excelUploadAjax", method = RequestMethod.POST)
    public String excelUploadAjax(MultipartHttpServletRequest request)  throws Exception{
        MultipartFile excelFile =request.getFile("excelFile");
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        String path = "";
        String fileName = "";


        File destFile = new File("/Users/gujalyong/Desktop/"+excelFile.getOriginalFilename());
//        File destFile = new File("/geunryeokhakgyo/tomcat/webapps/upload/" + excelFile.getOriginalFilename());
        try{
            excelFile.transferTo(destFile);
        }catch(IllegalStateException | IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        //Service 단에서 가져온 코드
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D","E","F","G","H","I","J","K","L","N","M","O","P");
        excelReadOption.setStartRow(4);

        List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption);
        try {
            for (Map<String, String> article : excelContent) {
                String name = article.get("B");
                int times = (int)Float.parseFloat(article.get("C"));
                String timeAndDay = article.get("D");
                String memberEtc = article.get("E").equals("null") ? "없음" : article.get("E");;
                String startDate = article.get("F");
                String endDate = article.get("G");
                String price = article.get("H").toString();
                String priceDate = article.get("I");
                int paymentYn = (int)Float.parseFloat(article.get("J").toString().trim());
                String priceType = article.get("K").toString();
                int delayYn = (int)Float.parseFloat(article.get("L").toString().trim());
                String delayDate =  article.get("M");
                int branch = 1;
                if( article.get("N") == null ) {
                    branch = (int)Float.parseFloat(article.get("N").toString().trim());
                }
                String id = article.get("O");
                String pnum = article.get("P").equals("null")? "0000000000" : article.get("P");
                System.out.println(pnum);
                String insertDate;

                int memberInfo = memberService.isExistId(id);
                if(memberInfo != 1 ) {
                    String encPass = commonService.encryptSHA256("1234");
                    memberService.insertMember(name, id, encPass, branch, memberEtc, pnum);
                }
                if(delayDate != "") {
                    delayDate = commonService.changeFormat(delayDate);
                }

                startDate   = commonService.changeFormat(startDate);

                if(priceDate.trim().equals("")) {
                    insertDate = startDate;
                } else {
                    insertDate = commonService.changeFormat(priceDate);;
                }
                endDate     = commonService.changeFormat(endDate);

                List<Map<String, Object>> searchMemberInfo = memberService.searchIdAndPass(id, "" );

                int memberSeq = Integer.parseInt(searchMemberInfo.get(0).get("member_seq").toString());
                Map<String, Object> result = commonService.findList(insertDate, endDate, timeAndDay, branch, times, delayYn, delayDate);
                String dayList = result.get("dayList").toString();
                String[] timeList = (String[]) result.get("timeList");
                String aliasList = result.get("aliasList").toString();
                String aa =  courseStudentController.insertCourseStudent(request, memberSeq, startDate, times, endDate, timeList, dayList, aliasList, priceDate, price, priceType , branch, paymentYn);
                if(delayYn == 1) {
                    courseStudentController.updateDealy(memberSeq, delayDate);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "admin/member/veiw_member";
        }

        return "admin/member/veiw_member";
    }

    @ResponseBody
    @RequestMapping(value = "/selectReview", method = RequestMethod.GET)
    public List<Map<String, Object>> selectReview() {
        List<Map<String, Object>> reviewList = service.reviewList();
        return reviewList;
    }

}