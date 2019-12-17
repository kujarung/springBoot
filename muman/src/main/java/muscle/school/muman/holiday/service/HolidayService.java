package muscle.school.muman.holiday.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import muscle.school.muman.commom.service.CommonService;
import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.holiday.dao.HolidayDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import muscle.school.muman.commom.dao.CommonDao;
import muscle.school.muman.member.dao.MemberDao;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.ResponseWrapper;

@Service
@MapperScan(basePackages = "muscle.school.muman.holiday.dao")
public class HolidayService {
	
    @Autowired
	HolidayDao dao;
    @Autowired
    CommonDao commonDao;
    @Autowired
    CommonService commonService;
    @Autowired
    CourseMasterService courseMasterService;

    public List<Map<String, Object>> selectHolidayList() {
		return dao.selectHolidayList();
    }

    @Transactional
    public int insertHoliday(String title, String start, String end, String branch) throws ParseException {
        dao.insertHoliday(title, start, end, branch);
        int startDay = commonService.getDayOfWeek(start);

        List<Map<String, Object>> deayMemberList = dao.getDeayMember(startDay,start);

        for(int i=0; i <deayMemberList.size(); i++) {
            String memberStartDay = deayMemberList.get(i).get("start_date").toString();
            String memberEndDay = deayMemberList.get(i).get("end_date").toString();
            int memberSeq = Integer.parseInt(deayMemberList.get(i).get("member_seq").toString());
            int insertBranch = Integer.parseInt(deayMemberList.get(i).get("branch").toString());
            System.out.println(deayMemberList.get(i).get("aliasList") );
            String[] memberAliasList = deayMemberList.get(i).get("aliasList").toString().split("|");
            Calendar endCal = commonService.changeCal(end);
            Calendar memberStartCal = commonService.changeCal(memberStartDay);
            while( endCal.compareTo(memberStartCal) == 1 ) {
                int plusDate = 0;
                memberStartCal.add(Calendar.DATE, plusDate);
                for(int a = 0;a < memberAliasList.length;a++) {
                    int holdiay = memberStartCal.get(Calendar.DAY_OF_WEEK);
                    int alias = Integer.parseInt(memberAliasList[a]);
                    if(holdiay == alias) {
                        courseMasterService.delayCourse(memberSeq, 1, insertBranch);
                    }
                }
            }

        }

        /*
        * 휴일 시작일 ~ 끝일
        * 수강자 수강 시작일 ~ 끝일
        *
        * 휴일 시작일과 끝일 사이에 수강자의 시작일과 끝일이 있는 지 여부
        *
        * 있다면?
        * 있는 인원 추출
        * 있는 인원의 시작일과 휴일의 끝일을 비교
        * 휴일의 끝일이 크다면
        * 운동의 시작 일이 운동하는 날인 지 비교
        * 맞다면 딜레이
        * 운동의 시작 일 + 1
        * 휴일의 끝일과 비교
        * ~~~ 반복
        * 휴일의 끝일 보다 인원의 시작일이 크다면 끝냄
        *
        * 없다면 skip
        *
        * */

        return 1;
    }

    
    public List<Map<String, Object>> searchHoilday(String searchDay) {
        List<Map<String, Object>> result = dao.searchHoilday(searchDay);
        return result;
    }
}
