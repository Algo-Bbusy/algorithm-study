import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_신규아이디추천 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String new_id = br.readLine();
		String answer = solution(new_id);
		
		System.out.println(answer);
	} // end main()

	private static String solution(String new_id) {
		StringBuilder recommend_id = new StringBuilder();
		List<Integer> dotPoint = new ArrayList<>(); 
		
//		1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
		new_id = new_id.toLowerCase();
		
//		2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
		int length = new_id.length();
		int cnt = 0;
		for(int i = 0; i < length; i++) {
			char el = new_id.charAt(i);
			if((el >= 97 && el <= 122) || (el >=48 && el <= 57)
					|| el == '-' || el == '_' || el == '.') {
				recommend_id.append(el);
				if(el == '.') dotPoint.add(cnt);
				cnt++;
			}
		}
		
		// 모든 문자가 제거 되면 "aaa"리턴
		if(recommend_id.length() == 0) {
			return "aaa";
		}
		
//		3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
//		4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
		if(dotPoint.size() >= 2) {
			int prePoint = dotPoint.get(dotPoint.size() - 1);
			for(int i = dotPoint.size() - 2; i >= 0; i--) {
				int curPoint = dotPoint.get(i);
				
				if(prePoint - curPoint == 1) {
					recommend_id.deleteCharAt(prePoint);
				}
				
				prePoint = curPoint;
			}
		}
		if(recommend_id.length() != 0 && recommend_id.charAt(0) == '.') {
			recommend_id.deleteCharAt(0);
		}
		if(recommend_id.length() != 0 && recommend_id.charAt(recommend_id.length() - 1) == '.') {
			recommend_id.deleteCharAt(recommend_id.length() - 1);
		}
		
//		5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
		// 모든 문자가 제거 되면 "aaa"리턴
		if(recommend_id.length() == 0) {
			return "aaa";
		}
		
		
//		6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
//		     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
		if(recommend_id.length() >= 16) {
			recommend_id.setLength(15);
			if(recommend_id.charAt(recommend_id.length() - 1) == '.') {
				recommend_id.setLength(recommend_id.length() - 1);
			}
			
			return recommend_id.toString();
		}
		
//		7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
		int recommend_id_length = recommend_id.length();
		if(recommend_id_length <= 2) {
			char lastElement = recommend_id.charAt(recommend_id_length - 1);
			for(int i = 0; i < 3 - recommend_id_length; i++) {
				recommend_id.append(lastElement);
			}
		}
		
		return recommend_id.toString();
	}
}
