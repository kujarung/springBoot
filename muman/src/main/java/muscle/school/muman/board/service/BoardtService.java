package muscle.school.muman.board.service;

import muscle.school.muman.board.dao.BoardDao;
import muscle.school.muman.commom.dao.CommonDao;
import muscle.school.muman.commom.service.CommonService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@MapperScan(basePackages = "muscle.school.muman.board.dao")
public class BoardtService {
	
	@Autowired
	BoardDao dao;
	@Autowired
	CommonDao commonDao;
	@Autowired
	CommonService commonService;

	public void insertStudent(int memberSeq, String startDate, String endDate, int timesWeek, String aliasList,
			String price, String price_date, String price_type, int paymentYn
			) {
		String aliasListFullFame= "";
		String [] tempAliasList = aliasList.split("\\|");
		for(int i=0; i< tempAliasList.length;i++) {
			int aliasNum = Integer.parseInt( tempAliasList[i].toString() );
			String aliasName = commonService.getAliasToFullName( aliasNum );
			if(i == 0 ) {
				aliasListFullFame = aliasName;
			} else {
				aliasListFullFame = aliasListFullFame + "," + aliasName;
			}
		}
		aliasListFullFame = "(" + aliasListFullFame + ")";
		dao.insertStudent(memberSeq, startDate, endDate, timesWeek, aliasListFullFame, aliasList, price, price_type, price_date, paymentYn);
		commonDao.nextCourseStudentSeq();
	}

	public List<Map<String, Object>> selectCourseStudentList(int currentPage) {
		// TODO Auto-generated method stub
		return dao.selectCourseStudentList(currentPage);
	}

	public Map<String, Object> getCourseStudentDetail(int memberSeq) {
		// TODO Auto-generated method stub
		return dao.selectCourseStudent(memberSeq);
	}

	public void updateDelay(int memberSeq, String updateDay) {
		// TODO Auto-generated method stub
		dao.updateDelay(memberSeq, updateDay);
	}

	public void updateCourseStudent(int memberSeq, String endDate, int timesWeek, String aliasList,
									String price, String priceDate, String priceType, int paymentYn){
		String aliasListFullName= "";
		String [] tempAliasList = aliasList.split("\\|");
		for(int i=0; i< tempAliasList.length;i++) {
			int aliasNum = Integer.parseInt( tempAliasList[i].toString() );
			String aliasName = commonService.getAliasToFullName( aliasNum );
			if(i == 0 ) {
				aliasListFullName = aliasName;
			} else {
				aliasListFullName = aliasListFullName + "," + aliasName;
			}
		}
		aliasListFullName = "(" + aliasListFullName + ")";
		dao.updateCourseStudent(memberSeq, endDate, timesWeek, aliasListFullName, aliasList, price, priceType, priceDate, paymentYn);
	}

    public void updateDealy(int memberSeq, String delayDate) {
		dao.updateDealy(memberSeq, delayDate);
    }
}