package muscle.school.muman.record.service;

import muscle.school.muman.record.dao.RecordDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@MapperScan(basePackages = "muscle.school.muman.record.dao")
public class RecordService {

	@Autowired
	RecordDao dao;

	public List<Map<String, Object>> getExNameList() {
		return dao.getExNameList();
	}
	public List<Map<String, Object>> getExRecord() {return dao.getExRecord();}

    public List<Map<String, Object>> getExMemberList(String memberSeq, String date) {
		return dao.getExMemberList(memberSeq, date);
    }
	public Map<String, Object> getExMember(int memner_seq, String date) {
		return dao.getExMember(memner_seq, date);
	}

	public void updateExRecor(String ex_seq, String ex_name, String ex_hide, String ex_desc) {
		dao.updateExRecor(ex_seq, ex_name, ex_hide, ex_desc);
	}

	public Boolean hasRecord(String member_seq, String date) {
		return dao.hasRecord(member_seq, date);
	}

	public void updateRecord(String member_seq, String ex_weight1, String ex_weight2, String ex_weight3, String ex_weight4, String ex_weight5, String ex_weight6, String ex_weight7, String ex_weight8, String ex_weight9, String ex_weight10, String ex_date) {
		dao.updateRecord(member_seq, ex_weight1, ex_weight2, ex_weight3, ex_weight4, ex_weight5, ex_weight6, ex_weight7, ex_weight8, ex_weight9, ex_weight10, ex_date);
	}

	public void insertRecord(String member_seq, String ex_weight1, String ex_weight2, String ex_weight3, String ex_weight4, String ex_weight5, String ex_weight6, String ex_weight7, String ex_weight8, String ex_weight9, String ex_weight10, String ex_date) {
		dao.insertRecord(member_seq, ex_weight1, ex_weight2, ex_weight3, ex_weight4, ex_weight5, ex_weight6, ex_weight7, ex_weight8, ex_weight9, ex_weight10, ex_date);
	}
}
