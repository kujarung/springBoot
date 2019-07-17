package muscle.school.muman.exercise.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import muscle.school.muman.exercise.dao.ExerciseDao;

@Service
@MapperScan(basePackages = "muscle.school.muman.exercise.dao")
public class ExerciseService {
	
    @Autowired
    ExerciseDao dao;

	public void insertExData(HttpServletRequest request) throws JSONException {
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Map<String,Object> exDataParam = new HashMap<>();
    	Map<String,Object> exContentParam = new HashMap<>();
		
    	int seq = dao.selectExMasterSeq();
    	String ex_title = request.getParameter("ex_title");
    	String ex_date = request.getParameter("ex_date");
    	exDataParam.put("ex_title", ex_title);
    	exDataParam.put("ex_date", ex_date);
    	exDataParam.put("ex_seq", seq);
    	exDataParam.put("member_seq", "1");
		dao.insertExData(exDataParam);
    	
    	String josnData = request.getParameter("jsonData").toString();
    	JsonParser jsonParser = new JsonParser();
    	JsonArray jsonArray = (JsonArray) jsonParser.parse(josnData);
    	for (int i = 0; i < jsonArray.size(); i++) {
    		JsonObject object = (JsonObject) jsonArray.get(i);
    		String weight = object.get("weight").toString();
    		String ex_cate = object.get("exCate").toString();
    		String times = object.get("times").toString();
    		String real_yn = object.get("realYn").toString();
    		String ex_set = object.get("exSet").toString();
    		
    		exContentParam.put("weight", weight );
    		exContentParam.put("ex_cate", ex_cate);
    		exContentParam.put("times", times );
    		exContentParam.put("real_yn", real_yn);
    		exContentParam.put("ex_set", ex_set);
    		exContentParam.put("ex_seq", seq);
    		exContentParam.put("member_seq", 1);
			dao.insertExContent(exContentParam);
    	}
	}

    

}

