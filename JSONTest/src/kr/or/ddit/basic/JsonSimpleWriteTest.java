package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * JSON : Java Script Object Notation
 * 
 * - JSON 에서 value값으로 가능한 데이터 타입
 * 1. string
 * 2. number
 * 3. object(JSON object)
 * 4. array
 * 5. boolean
 * 6. null
 * 
 */
public class JsonSimpleWriteTest {
	public static void main(String[] args) throws IOException {
		// JSON 데이터 생성
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "홍길동");
		jsonObj.put("job", "학생");
		jsonObj.put("age", 30);
		jsonObj.put("addr", "대전시 중구 대흥동 대덕인재개발원");
		
		// JSONArray 데이터 생성
		JSONArray singerList = new JSONArray();
		JSONObject singer = new JSONObject();
		singer.put("name", "싸이");
		singer.put("gender", "남자");
		singer.put("age", 40);
		singerList.add(singer);
		
		singer = new JSONObject();
		singer.put("name", "비욘세");
		singer.put("gender", "여자");
		singer.put("age", 37);
		singerList.add(singer);
		
		singer = new JSONObject();
		singer.put("name", "본조비");
		singer.put("gender", "남자");
		singer.put("age", 44);
		singerList.add(singer);
		
		jsonObj.put("singerList", singerList);
		
		FileWriter fw = new FileWriter("e:/myJsonFile.txt");
		fw.write(jsonObj.toString());
		fw.flush();
		fw.close();
		
		System.out.println("JSON객체 내용 출력 : " + jsonObj);
		
		
		
	}
}
