package muscle.school.muman.holiday.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        try {
            dao.insertHoliday(title, start, end, branch);
            int startDay = commonService.getDayOfWeek(start);

            Calendar startCal = Calendar.getInstance();
            startCal.set(Calendar.YEAR, Integer.parseInt(start.split("-")[0]) );
            startCal.set(Calendar.MONTH, Integer.parseInt(start.split("-")[1]) - 1);
            startCal.set(Calendar.DATE, Integer.parseInt(start.split("-")[2]));

            Calendar endCal = Calendar.getInstance();
            endCal.set(Calendar.YEAR, Integer.parseInt(end.split("-")[0]) );
            endCal.set(Calendar.MONTH, Integer.parseInt(end.split("-")[1]) - 1);
            endCal.set(Calendar.DATE, Integer.parseInt(end.split("-")[2]));

            // 시작일이 끝나는 날 보다 클 때 까지 반복
            while(startCal.compareTo(endCal) < 0) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String whileStart = format.format(startCal.getTime() );
                System.out.println();
                // 휴일의 시작일이 수강 중인 날짜의 중간인 멤버를 찾음
                List<Map<String, Object>> dealMemberList = dao.getDeayMember(Calendar.DAY_OF_WEEK, whileStart);
                //멤버의 수 만큼 반복
                for (Map<String, Object> stringObjectMap : dealMemberList) {
                    //제어 할 멥버의 정보값을 가져옴
                    int memberSeq = Integer.parseInt(stringObjectMap.get("member_seq").toString());
                    int insertBranch = Integer.parseInt(stringObjectMap.get("branch").toString());
                    String[] memberAliasList = stringObjectMap.get("aliasList").toString().split("\\|");
                    // 정보 값 중 멤버가 수강하고 있는 날의 aliasList에 있는 값 중 휴일과 일치하는 날이 있는 지 찾아봄
                    // 있다면 딜레이 없다면 스킵
                    int holiday = startCal.get(Calendar.DAY_OF_WEEK);
                    System.out.println(format.format(startCal.getTime() ));
                    for (String s : memberAliasList) {
                        int alias = Integer.parseInt(s);
                        int aliasDay = Integer.parseInt(commonService.getAliasDetail(alias).get("dayOfWeek").toString());
                        if ( holiday == aliasDay) {
                            courseMasterService.delayCourse(memberSeq, 1, insertBranch);
                        }
                    }
                }
                startCal.add(Calendar.DATE,1);
            }
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

    
    public List<Map<String, Object>> searchHoilday(String searchDay) {
        List<Map<String, Object>> result = dao.searchHoilday(searchDay);
        return result;
    }
}
