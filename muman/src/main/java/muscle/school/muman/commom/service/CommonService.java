package muscle.school.muman.commom.service;

import muscle.school.muman.course_master.service.CourseMasterService;
import muscle.school.muman.holiday.service.HolidayService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@MapperScan(basePackages = "muscle.school.muman.commom.dao")
public class CommonService {

	@Autowired
	CourseMasterService courseMasterService;
	@Autowired
	HolidayService holidayService;

	//월요일과 금요일 날을 리턴 하는 함수
	public String[] todayWeek(String standardDate) {
		String[] result = new String[2];
		String startDate ="";
		String endDate = "";
        Calendar currentCalendar = Calendar.getInstance();
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
        if(standardDate == "" || standardDate == null) {
		    //이번주 첫째 날짜  
        	currentCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        	startDate = dateFmt.format(currentCalendar.getTime());
        	//이번주 마지막 날짜  
        	currentCalendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        	endDate = dateFmt.format(currentCalendar.getTime()); 
		} else {
			Calendar newCal = Calendar.getInstance();
	        int mYear = Integer.parseInt( standardDate.split("-")[0].toString());
	        int mMonth = Integer.parseInt( standardDate.split("-")[1].toString()) - 1;
	        int mDate = Integer.parseInt( standardDate.split("-")[2].toString());
	        newCal.set ( mYear, mMonth, mDate);
			newCal.getTime();
		    //이번주 첫째 날짜
			newCal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
	    	startDate = dateFmt.format(newCal.getTime());
	    	//이번주 마지막 날짜  
	    	newCal.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY); 
	        endDate = dateFmt.format(newCal.getTime());
		}
        result[0] = startDate;
        result[1] = endDate;
		return result;
	}
	
	public String currentDay(String standardDate) {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
		Calendar today =  Calendar.getInstance();
		if(standardDate == null) {
			return format1.format(today.getTime());
		} else {
			return standardDate;
		}
	}
	
	//yyyy-mm-dd 형태로 포메팅 하는 함수
	public String formatDate(Calendar currentCalendar) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return (format.format(currentCalendar).toString() );
	}
	
	//날짜의 요일을 알려 주는 함수
	public int getDayOfWeek(String currentDate) throws ParseException {
		int changeYear, changeMonth, changeDate, changeDayOfWeek; 
		Calendar cal = Calendar.getInstance();
		changeYear = Integer.parseInt( currentDate.split("-")[0] );	
		changeMonth= Integer.parseInt( currentDate.split("-")[1]);
		changeDate =Integer.parseInt( currentDate.split("-")[2] );
		cal.set( changeYear , changeMonth - 1, changeDate);
		changeDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return changeDayOfWeek;
	}
	
    //페이징 처리
    public Map<String, Object> calcPaging(int totalCnt, int currentPage, int pagingCnt) {
    	Map<String,Object> pagingInfo = new HashMap<>();
    	int lastPage  = (int)(Math.ceil( currentPage / 10.0 )) * 10;
    	int firstPage = lastPage - 9;
    	
    	int prev = 0;
    	if(firstPage == 1) {
    		prev = 1;
    	} else {
    		prev = firstPage - 10;
    	}
    	
    	int realEnd = (int)(Math.ceil ( (totalCnt * 1.0) / pagingCnt ) );
    	if(lastPage > realEnd ) {
    		lastPage = realEnd;
    	}
    	
    	int next = 0;
    	if(lastPage % 10 == 0) {
    		next = lastPage + 1;
    	} else {
    		next = lastPage;
    	}
    	
    	pagingInfo.put("lastPage", lastPage);
    	pagingInfo.put("firstPage", firstPage);
    	pagingInfo.put("prev", prev);
    	pagingInfo.put("next", next);
    	return pagingInfo;
    }

    public Map<String, Object> getAliasDetail(int alias) {
		Map<String, Object> result = new HashMap<String, Object>();
		switch(alias){
			case 1:
				result.put("fullName","월1교시");
				result.put("dayOfWeek",2);
				result.put("branch",1);
				break ;
			case 2:
				result.put("fullName","월2교시");
				result.put("dayOfWeek",2);
				result.put("branch",1);
				break ;
			case 3:
				result.put("fullName","월3교시");
				result.put("dayOfWeek",2);
				result.put("branch",1);
				break ;
			case 4:
				result.put("fullName","월4교시");
				result.put("dayOfWeek",2);
				result.put("branch",1);
				break ;
			case 5:
				result.put("fullName","월5교시");
				result.put("dayOfWeek",2);
				result.put("branch",1);
				break ;
			case 6:
				result.put("fullName","월6교시");
				result.put("dayOfWeek",2);
				result.put("branch",1);
				break ;
			case 7:
				result.put("fullName","화1교시");
				result.put("dayOfWeek",3);
				result.put("branch",1);
				break ;
			case 8:
				result.put("fullName","화2교시");
				result.put("dayOfWeek",3);
				result.put("branch",1);
				break ;
			case 9:
				result.put("fullName","화3교시");
				result.put("dayOfWeek",3);
				result.put("branch",1);
				break ;
			case 10:
				result.put("fullName","화4교시");
				result.put("dayOfWeek",3);
				result.put("branch",1);
				break ;
			case 11:
				result.put("fullName","화5교시");
				result.put("dayOfWeek",3);
				result.put("branch",1);
				break ;
			case 12:
				result.put("fullName","화6교시");
				result.put("dayOfWeek",3);
				result.put("branch",1);
				break ;
			case 13:
				result.put("fullName","수1교시");
				result.put("dayOfWeek",4);
				result.put("branch",1);
				break ;
			case 14:
				result.put("fullName","수2교시");
				result.put("dayOfWeek",4);
				result.put("branch",1);
				break ;
			case 15:
				result.put("fullName","수3교시");
				result.put("dayOfWeek",4);
				result.put("branch",1);
				break ;
			case 16:
				result.put("fullName","수4교시");
				result.put("dayOfWeek",4);
				result.put("branch",1);
				break ;
			case 17:
				result.put("fullName","수5교시");
				result.put("dayOfWeek",4);
				result.put("branch",1);
				break ;
			case 18:
				result.put("fullName","수6교시");
				result.put("dayOfWeek",4);
				result.put("branch",1);
				break ;

			case 19:
				result.put("fullName","목1교시");
				result.put("dayOfWeek",5);
				result.put("branch",1);
				break ;
			case 20:
				result.put("fullName","목2교시");
				result.put("dayOfWeek",5);
				result.put("branch",1);
				break ;
			case 21:
				result.put("fullName","목3교시");
				result.put("dayOfWeek",5);
				result.put("branch",1);
				break ;
			case 22:
				result.put("fullName","목4교시");
				result.put("dayOfWeek",5);
				result.put("branch",1);
				break ;
			case 23:
				result.put("fullName","목5교시");
				result.put("dayOfWeek",5);
				result.put("branch",1);
				break ;
			case 24:
				result.put("fullName","목6교시");
				result.put("dayOfWeek",5);
				result.put("branch",1);
				break ;

			case 25:
				result.put("fullName","금1교시");
				result.put("dayOfWeek",6);
				result.put("branch",1);
				break ;
			case 26:
				result.put("fullName","금2교시");
				result.put("dayOfWeek",6);
				result.put("branch",1);
				break ;
			case 27:
				result.put("fullName","금3교시");
				result.put("dayOfWeek",6);
				result.put("branch",1);
				break ;
			case 28:
				result.put("fullName","금4교시");
				result.put("dayOfWeek",6);
				result.put("branch",1);
				break ;
			case 29:
				result.put("fullName","금5교시");
				result.put("dayOfWeek",6);
				result.put("branch",1);
				break ;
			case 30:
				result.put("fullName","금6교시");
				result.put("dayOfWeek",6);
				result.put("branch",1);
				break ;

			case 31:
				result.put("fullName","월1교시");
				result.put("dayOfWeek",2);
				result.put("branch",2);
				break ;
			case 32:
				result.put("fullName","월2교시");
				result.put("dayOfWeek",2);
				result.put("branch",2);
				break ;
			case 33:
				result.put("fullName","월3교시");
				result.put("dayOfWeek",2);
				result.put("branch",2);
				break ;
			case 34:
				result.put("fullName","월4교시");
				result.put("dayOfWeek",2);
				result.put("branch",2);
				break ;
			case 35:
				result.put("fullName","월5교시");
				result.put("dayOfWeek",2);
				result.put("branch",2);
				break ;
			case 36:
				result.put("fullName","월6교시");
				result.put("dayOfWeek",2);
				result.put("branch",2);
				break ;
			case 37:
				result.put("fullName","월7교시");
				result.put("dayOfWeek",2);
				result.put("branch",2);
				break ;
			case 38:
				result.put("fullName","화1교시");
				result.put("dayOfWeek",3);
				result.put("branch",2);
				break ;
			case 39:
				result.put("fullName","화2교시");
				result.put("dayOfWeek",3);
				result.put("branch",2);
				break ;
			case 40:
				result.put("fullName","화3교시");
				result.put("dayOfWeek",3);
				result.put("branch",2);
				break ;
			case 41:
				result.put("fullName","화4교시");
				result.put("dayOfWeek",3);
				result.put("branch",2);
				break ;
			case 42:
				result.put("fullName","화5교시");
				result.put("dayOfWeek",3);
				result.put("branch",2);
				break ;
			case 43:
				result.put("fullName","화6교시");
				result.put("dayOfWeek",3);
				result.put("branch",2);
				break ;
			case 44:
				result.put("fullName","화7교시");
				result.put("dayOfWeek",3);
				result.put("branch",2);
				break ;
			case 45:
				result.put("fullName","수1교시");
				result.put("dayOfWeek",4);
				result.put("branch",2);
				break ;
			case 46:
				result.put("fullName","수2교시");
				result.put("dayOfWeek",4);
				result.put("branch",2);
				break ;
			case 47:
				result.put("fullName","수3교시");
				result.put("dayOfWeek",4);
				result.put("branch",2);
				break ;
			case 48:
				result.put("fullName","수4교시");
				result.put("dayOfWeek",4);
				result.put("branch",2);
				break ;
			case 49:
				result.put("fullName","수5교시");
				result.put("dayOfWeek",4);
				result.put("branch",2);
				break ;
			case 50:
				result.put("fullName","수6교시");
				result.put("dayOfWeek",4);
				result.put("branch",2);
				break ;
			case 51:
				result.put("fullName","수7교시");
				result.put("dayOfWeek",4);
				result.put("branch",2);
				break ;
			case 52:
				result.put("fullName","목1교시");
				result.put("dayOfWeek",5);
				result.put("branch",2);
				break ;
			case 53:
				result.put("fullName","목2교시");
				result.put("dayOfWeek",5);
				result.put("branch",2);
				break ;
			case 54:
				result.put("fullName","목3교시");
				result.put("dayOfWeek",5);
				result.put("branch",2);
				break ;
			case 55:
				result.put("fullName","목4교시");
				result.put("dayOfWeek",5);
				result.put("branch",2);
				break ;
			case 56:
				result.put("fullName","목5교시");
				result.put("dayOfWeek",5);
				result.put("branch",2);
				break ;
			case 57:
				result.put("fullName","목6교시");
				result.put("dayOfWeek",5);
				result.put("branch",2);
				break ;
			case 58:
				result.put("fullName","목7교시");
				result.put("dayOfWeek",5);
				result.put("branch",2);
				break ;
			case 59:
				result.put("fullName","금1교시");
				result.put("dayOfWeek",6);
				result.put("branch",2);
				break ;
			case 60:
				result.put("fullName","금2교시");
				result.put("dayOfWeek",6);
				result.put("branch",2);
				break ;
			case 61:
				result.put("fullName","금3교시");
				result.put("dayOfWeek",6);
				result.put("branch",2);
				break ;
			case 62:
				result.put("fullName","금4교시");
				result.put("dayOfWeek",6);
				result.put("branch",2);
				break ;
			case 63:
				result.put("fullName","금5교시");
				result.put("dayOfWeek",6);
				result.put("branch",2);
				break ;
			case 64:
				result.put("fullName","금6교시");
				result.put("dayOfWeek",6);
				result.put("branch",2);
				break ;
			case 65:
				result.put("fullName","금7교시");
				result.put("dayOfWeek",6);
				result.put("branch",2);
				break ;
		}
		return result;
	}

	public Map<String, Object> getAlias(String dayName, int branch) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(branch == 1) {
			if(dayName.equals("월1")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",1);
			}
			if(dayName.equals("월2")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",2);
			}
			if(dayName.equals("월3")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",3);
			}
			if(dayName.equals("월4")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",4);
			}
			if(dayName.equals("월5")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",5);
			}
			if(dayName.equals("월6")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",6);
			}
			if(dayName.equals("화1")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",7);
			}
			if(dayName.equals("화2")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",8);
			}
			if(dayName.equals("화3")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",9);
			}
			if(dayName.equals("화4")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",10);
			}
			if(dayName.equals("화5")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",11);
			}
			if(dayName.equals("화6")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",12);
			}
			if(dayName.equals("수1")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",13);
			}
			if(dayName.equals("수2")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",14);
			}
			if(dayName.equals("수3")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",15);
			}
			if(dayName.equals("수4")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",16);
			}
			if(dayName.equals("수5")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",17);
			}
			if(dayName.equals("수6")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",18);
			}
			if(dayName.equals("목1")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",19);
			}
			if(dayName.equals("목2")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",20);
			}
			if(dayName.equals("목3")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",21);
			}
			if(dayName.equals("목4")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",22);
			}
			if(dayName.equals("목5")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",23);
			}
			if(dayName.equals("목6")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",24);
			}
			if(dayName.equals("금1")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",25);
			}
			if(dayName.equals("금2")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",26);
			}
			if(dayName.equals("금3")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",27);
			}
			if(dayName.equals("금4")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",28);
			}
			if(dayName.equals("금5")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",29);
			}
			if(dayName.equals("금6")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",30);
			}
		}
		else if(branch == 2) {
			if(dayName.equals("월1")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",31);
			}
			if(dayName.equals("월2")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",32);
			}
			if(dayName.equals("월3")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",33);
			}
			if(dayName.equals("월4")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",34);
			}
			if(dayName.equals("월5")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",35);
			}
			if(dayName.equals("월6")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",36);
			}
			if(dayName.equals("월7")) {
				result.put("dayOfWeek",2);
				result.put("dayAlias",37);
			}
			if(dayName.equals("화1")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",38);
			}
			if(dayName.equals("화2")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",39);
			}
			if(dayName.equals("화3")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",40);
			}
			if(dayName.equals("화4")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",41);
			}
			if(dayName.equals("화5")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",42);
			}
			if(dayName.equals("화6")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",43);
			}
			if(dayName.equals("화7")) {
				result.put("dayOfWeek",3);
				result.put("dayAlias",44);
			}
			if(dayName.equals("수1")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",45);
			}
			if(dayName.equals("수2")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",46);
			}
			if(dayName.equals("수3")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",47);
			}
			if(dayName.equals("수4")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",48);
			}
			if(dayName.equals("수5")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",49);
			}
			if(dayName.equals("수6")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",50);
			}
			if(dayName.equals("수7")) {
				result.put("dayOfWeek",4);
				result.put("dayAlias",51);
			}
			if(dayName.equals("목1")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",52);
			}
			if(dayName.equals("목2")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",53);
			}
			if(dayName.equals("목3")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",54);
			}
			if(dayName.equals("목4")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",55);
			}
			if(dayName.equals("목5")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",56);
			}
			if(dayName.equals("목6")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",57);
			}
			if(dayName.equals("목7")) {
				result.put("dayOfWeek",5);
				result.put("dayAlias",58);
			}
			if(dayName.equals("금1")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",59);
			}
			if(dayName.equals("금2")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",60);
			}
			if(dayName.equals("금3")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",61);
			}
			if(dayName.equals("금4")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",62);
			}
			if(dayName.equals("금5")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",63);
			}
			if(dayName.equals("금6")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",64);
			}
			if(dayName.equals("금7")) {
				result.put("dayOfWeek",6);
				result.put("dayAlias",65);
			}
		}
		return result;
	}
	
	//alias 번호를 풀 네임으로 변환 시키는 함수
	public String getAliasToFullName(int alias) {
		String fullName = null;
		switch(alias){
        case 1:
        	fullName = "월1교시";
            break ;
        case 2:
        	fullName = "월2교시";
            break ;
        case 3:
        	fullName = "월3교시";
            break ;
        case 4:
        	fullName = "월4교시";
            break ;
        case 5:
        	fullName = "월5교시";
            break ;
        case 6:
        	fullName = "월6교시";
            break ;
        
        case 7:
        	fullName = "화1교시";
            break ;
        case 8:
        	fullName = "화2교시";
            break ;
        case 9:
        	fullName = "화3교시";
            break ;
        case 10:
        	fullName = "화4교시";
            break ;
        case 11:
        	fullName = "화5교시";
            break ;
        case 12:
        	fullName = "화6교시";
            break ;
        
        case 13:
        	fullName = "수1교시";
            break ;
        case 14:
        	fullName = "수2교시";
            break ;
        case 15:
        	fullName = "수3교시";
            break ;
        case 16:
        	fullName = "수4교시";
            break ;
        case 17:
        	fullName = "수5교시";
            break ;
        case 18:
        	fullName = "수6교시";
            break ;

        case 19:
        	fullName = "목1교시";
            break ;            
        case 20:
        	fullName = "목2교시";
            break ;
        case 21:
        	fullName = "목3교시";
            break ;
        case 22:
        	fullName = "목4교시";
            break ;
        case 23:
        	fullName = "목5교시";
            break ;
        case 24:
        	fullName = "목6교시";
            break ;
        
        case 25:
        	fullName = "금1교시";
            break ;            
        case 26:
        	fullName = "금2교시";
            break ;            
        case 27:
        	fullName = "금3교시";
            break ;            
        case 28:
        	fullName = "금4교시";
            break ;            
        case 29:
        	fullName = "금5교시";
            break ;            
        case 30:
        	fullName = "금6교시";
            break ;

		case 31:
			fullName = "월1교시";
			break ;
		case 32:
			fullName = "월2교시";
			break ;
		case 33:
			fullName = "월3교시";
			break ;
		case 34:
			fullName = "월4교시";
			break ;
		case 35:
			fullName = "월5교시";
			break ;
		case 36:
			fullName = "월6교시";
			break ;

		case 37:
			fullName = "월7교시";
			break ;
		case 38:
			fullName = "화1교시";
			break ;
		case 39:
			fullName = "화2교시";
			break ;
		case 40:
			fullName = "화3교시";
			break ;
		case 41:
			fullName = "화4교시";
			break ;
		case 42:
			fullName = "화5교시";
			break ;

		case 43:
			fullName = "화6교시";
			break ;
		case 44:
			fullName = "화7교시";
			break ;
		case 45:
			fullName = "수1교시";
			break ;
		case 46:
			fullName = "수2교시";
			break ;
		case 47:
			fullName = "수3교시";
			break ;
		case 48:
			fullName = "수4교시";
			break ;

		case 49:
			fullName = "수5교시";
			break ;
		case 50:
			fullName = "수6교시";
			break ;
		case 51:
			fullName = "수7교시";
			break ;
		case 52:
			fullName = "목1교시";
			break ;
		case 53:
			fullName = "목2교시";
			break ;
		case 54:
			fullName = "목3교시";
			break ;
		case 55:
			fullName = "목4교시";
			break ;
		case 56:
			fullName = "목5교시";
			break ;
		case 57:
			fullName = "목6교시";
			break ;
		case 58:
			fullName = "목7교시";
			break ;
		case 59:
			fullName = "금1교시";
			break ;
		case 60:
			fullName = "금2교시";
			break ;
		case 61:
			fullName = "금3교시";
			break ;
		case 62:
			fullName = "금4교시";
			break ;
		case 63:
			fullName = "금5교시";
			break ;
		case 64:
			fullName = "금6교시";
			break ;
		case 65:
			fullName = "금7교시";
			break ;
		}
		return fullName;
	}
	
	// alias로 되어있는 것을 변형 하는 method
	public Map<String, Object> aliasToDetail(String alias) {
		List<Map<String, Object>> courseList = courseMasterService.selectCourseMasterList(Integer.parseInt(alias));
		return courseList.get(0);
	}
	
	//resultDay 만큼 endDate에서 더해주는 함수
	/** 
	 * @param day : 시작 날짜
	 * @param addDate : 더 할 일수
	 * @return String 
	 * 날짜(day)와 더 할 일수(addDate)를 더하여 결과 날짜를 리턴 시키는 함수
	 * */
	public String afterDay(String day, int addDate) {
		Calendar cal = Calendar.getInstance();
		int endDateYear = Integer.parseInt( day.split("-")[0] );
		int endDateMonth = Integer.parseInt( day.split("-")[1] ) - 1;
		int endDateDate = Integer.parseInt(day.split("-")[2] ) + addDate;
		cal.set( Calendar.YEAR, endDateYear );
		cal.set( Calendar.MONTH, endDateMonth );
		cal.set( Calendar.DATE, endDateDate );
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
	}

	public Calendar changeCal(String day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(day.split("-")[0]) );
		cal.set(Calendar.MONTH, Integer.parseInt(day.split("-")[1]) - 1);
		cal.set(Calendar.DATE, Integer.parseInt(day.split("-")[2]));

		return cal;
	}

	public String changeFormat(String day) {
		return  "20" + day.substring(0,2) + "-" + day.substring(2,4) + "-" + day.substring(4,6);
	}

	public String showCalDetail(Calendar cal) {
		return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
	}

	public String encryptSHA256(String str){
		String sha = "";
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte[] byteData = sh.digest();
			StringBuilder sb = new StringBuilder();
			for (byte byteDatum : byteData) {
				sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
			}
			sha = sb.toString();
		}catch(NoSuchAlgorithmException e){
			//e.printStackTrace();
			sha = null;
		}
		return sha;
	}

	public Map<String, Object> findList(String startDate, String endDate, String timeAndDay, int branch, int times, int delayYn, String delayDate) {
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			String[] tempChange = timeAndDay.split("\\|");
			String dayList = "";
			String tempAliasList = "";
			String[] timeList = new String[times*4];
			for (String s : tempChange) {
				if(dayList.equals("")) {
					dayList = dayList + (Integer.parseInt(getAlias(s, branch).get("dayOfWeek").toString()) - 1);
				} else {
					dayList = dayList + "|" + (Integer.parseInt(getAlias(s, branch).get("dayOfWeek").toString()) - 1);
				}
				if(tempAliasList == "") {
					tempAliasList = tempAliasList + getAlias(s, branch).get("dayAlias");
				} else {
					tempAliasList = tempAliasList + "|" + getAlias(s, branch).get("dayAlias");
				}
			}
			String aliasList[] = tempAliasList.split("\\|");
			int tempList[] = new int[aliasList.length];
			for(int i=0;i<aliasList.length;i++) { tempList[i] = Integer.parseInt(aliasList[i]); }
			Arrays.sort(tempList);
			for(int i=0;i<aliasList.length;i++) { aliasList[i] = Integer.toString(tempList[i]); }
			//수강이 끝나는 마지막 일
			int day = getDayOfWeek(startDate);
			//끝나는 날을 받음
			//끝나는 날의 요일을 구함
			timeList[0] = startDate;
			for(int j=1; j< times*4; j++) {
				int intEndDay = getDayOfWeek(startDate);
				//그 요일 보다 큰 수를 alias리스트에서 찾음
				//없을 시 첫번 째 요일이 구하는 수
				int searchIntDay = Integer.parseInt(aliasToDetail(aliasList[0]).get("week_of_day").toString() );
				for (String s : aliasList) {
					int targetDay =
							Integer.parseInt(aliasToDetail(s).get("week_of_day").toString());
					if (intEndDay < targetDay) {
						searchIntDay = targetDay;
						break;
					}
				}
				//내가 수요일, 화요일이 타깃 요일이라면?
				//화요일 = 3
				//수요일 = 4
				//결과 = 7 - 절대값(4 - 3)
				//오늘 부터 결과 값 만큼 +
				int resultDay = searchIntDay - intEndDay;
				if(searchIntDay - intEndDay <= 0) {
					resultDay = 7 - Math.abs(searchIntDay - intEndDay);
				}
				String updateDay = afterDay(startDate, resultDay);
				if( Integer.parseInt( updateDay.split("-")[1] )< 10 ) {
					updateDay = updateDay.split("-")[0] + "-0" + updateDay.split("-")[1] + "-" + updateDay.split("-")[2];
				}
				if( Integer.parseInt(updateDay.split("-")[2])< 10 ) {
					updateDay = updateDay.split("-")[0] + "-" + updateDay.split("-")[1] + "-0" + updateDay.split("-")[2];
				}
				boolean updateDayIsHoliday = holidayService.searchHoliday(updateDay);
				if( updateDayIsHoliday ) {
					j--;
					startDate = updateDay;
				} else {
					if(delayYn == 1) {
						if(updateDay.equals(delayDate)) {
							j--;
							delayYn = 0;
							startDate = updateDay;
						} else {

							timeList[j] = updateDay;
							startDate = updateDay;
						}
					} else {
						timeList[j] = updateDay;
						startDate = updateDay;
					}
				}
			}
			result.put("dayList", dayList);
			result.put("timeList", timeList);
			result.put("aliasList", tempAliasList);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}
