package muscle.school.muman.commom.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import muscle.school.muman.course_master.service.CourseMasterService;

@Service
@MapperScan(basePackages = "muscle.school.muman.commom.dao")
public class CommonService {
	
	@Autowired
	CourseMasterService courseMasterService;
	
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
		return currentCalendar.get(Calendar.DAY_OF_YEAR) + "-" + currentCalendar.get(Calendar.DAY_OF_MONTH) + "-" + currentCalendar.get(Calendar.DATE);
	}
	
	//날짜의 요일을 알려 주는 함수
	public int getDayOfWeek(String currentDate) throws ParseException {
		int changeYear, changeMonth, changeDate, changeDayOfWeek; 
		Calendar cal = Calendar.getInstance();
		changeYear = Integer.parseInt( currentDate.split("-")[0] );	
		changeMonth= Integer.parseInt( currentDate.split("-")[1] );
		changeDate =Integer.parseInt( currentDate.split("-")[2] );
		cal.set( changeYear , changeMonth - 1, changeDate);
		changeDayOfWeek = cal.get(cal.DAY_OF_WEEK) - 1;
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
    	
    }
		return fullName;
	}
	
	// alias로 되어있는 것을 변형 하는 method
	public Map<String, Object> aliasToDetail(String alias) {
		List<Map<String, Object>> courseList = courseMasterService.selectCourseMasterList(Integer.parseInt(alias));
		return courseList.get(0);
	}
	
	//resultDay 만큼 endDate에서 더해주는 함수
	public String afterDay(String day, int addDate) {
		Calendar cal = Calendar.getInstance();
		int endDateYear = Integer.parseInt( day.split("-")[0] );
		int endDateMonth = Integer.parseInt( day.split("-")[1] ) - 1;
		int endDateDate = Integer.parseInt(day.split("-")[2] ) + addDate;
		cal.set( Calendar.YEAR, endDateYear );
		cal.set( Calendar.MONTH, endDateMonth );
		cal.set( Calendar.DATE, endDateDate );
		
		String result = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
		return result;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int alias = 2;
		String endDate = "2019-12-23";
		int num = 12;
		Calendar cal = Calendar.getInstance();
		int endDateYear = Integer.parseInt( endDate.split("-")[0] );
		int endDateMonth = Integer.parseInt( endDate.split("-")[1] ) - 1;
		int endDateDate = Integer.parseInt(endDate.split("-")[2] );
		cal.set( Calendar.YEAR, endDateYear );
		cal.set( Calendar.MONTH, endDateMonth );
		cal.set( Calendar.DATE, endDateDate );
		
	}
}
