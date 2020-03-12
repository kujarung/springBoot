package muscle.school.muman.record.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface  RecordDao {

    List<Map<String, Object>> getExNameList();

    List<Map<String, Object>> getExRecord();

    List<Map<String, Object>> getExMemberList(String member_seq, String date);

    void updateExRecor(String ex_seq, String ex_name, String ex_hide, String ex_desc);

    Boolean hasRecord(String member_seq, String date);

    void updateRecord(String member_seq, String ex_weight1, String ex_weight2, String ex_weight3, String ex_weight4, String ex_weight5, String ex_weight6, String ex_weight7, String ex_weight8, String ex_weight9, String ex_weight10, String ex_date);

    void insertRecord(String member_seq, String ex_weight1, String ex_weight2, String ex_weight3, String ex_weight4, String ex_weight5, String ex_weight6, String ex_weight7, String ex_weight8, String ex_weight9, String ex_weight10, String ex_date);

    Map<String, Object> getExMember(int member_seq, String date);
}
