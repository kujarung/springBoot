package muscle.school.muman.commom.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import muscle.school.muman.course_master.dao.CourseMasterDao;

@Service
@MapperScan(basePackages = "muscle.school.muman.commom.dao")
public class CommonService {
	
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
}
