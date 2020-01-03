package muscle.school.muman.holiday.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
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
    // 0입력 시 전체
    public List<Map<String, Object>> selectHolidayList(Map<String, Object> param) {
		return dao.selectHolidayList(param);
    }

    @Transactional
    public int insertHoliday(String title, String start, String end, String branch) throws ParseException {
        try {
            dao.insertHoliday(title, start, end, branch);
            Calendar startCal = commonService.changeCal(start);
            Calendar endCal = commonService.changeCal(end);
            // 시작일이 끝나는 날 보다 클 때 까지 반복
            String startDate = commonService.showCalDetail(startCal);
            String endDate = commonService.showCalDetail(endCal);
            List<Map<String, Object>> list = courseMasterService.searchHoildayInCourse(startDate, endDate);
            for(Map<String, Object> m : list) {
                int memberSeq = Integer.parseInt(m.get("member_seq").toString() );
                int insertBranch = Integer.parseInt(m.get("branch").toString() );
                courseMasterService.delayCourse(memberSeq,1, insertBranch);
            }
            startCal.add(Calendar.DATE,1);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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


    public boolean searchHoliday(String day) throws ParseException {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("branch", "");
        List<Map<String, Object>> holidayList = dao.selectHolidayList(param);
        Calendar targetCal = commonService.changeCal(day);

        for (Map<String,Object> item : holidayList) {
            String start = item.get("START_DAY").toString();
            String end = item.get("END_DAY").toString();
            Calendar startCal = commonService.changeCal(start);
            Calendar endCal = commonService.changeCal(end);

            while(startCal.compareTo(endCal) < 0) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String whileStart = format.format(startCal.getTime() );
                // 휴일의 시작일이 수강 중인 날짜의 중간인 멤버를 찾음
                List<Map<String, Object>> dealMemberList = dao.getDeayMember(Calendar.DAY_OF_WEEK, whileStart);
                //멤버의 수 만큼 반복
                //제어 할 멥버의 정보값을 가져옴
                // 정보 값 중 멤버가 수강하고 있는 날의 aliasList에 있는 값 중 휴일과 일치하는 날이 있는 지 찾아봄
                // 있다면 딜레이 없다면 스킵
                int result = targetCal.compareTo(startCal);
                String showTarget = commonService.showCalDetail(targetCal);
                String showStart= commonService.showCalDetail(startCal);

                System.out.println(showTarget);
                System.out.println(showStart);
                System.out.println(showTarget.equals(showStart) );
                if(showTarget.equals(showStart)) {
                    return true;
                }
                startCal.add(Calendar.DATE,1);
            }
        }
        return false;
    }

    public List<Map<String, Object>> searchHoilday(String searchDay) {
        List<Map<String, Object>> result = dao.searchHoilday(searchDay);
        return result;
    }

    public int deletetHoliday(int seq) {
        try {
            dao.deletetHoliday(seq);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
