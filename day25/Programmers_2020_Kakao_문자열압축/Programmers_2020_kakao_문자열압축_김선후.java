package kakao2020;

public class Programmers_2020_kakao_문자열압축 {
	//문자열압축
	//Solution 1.
	//1. 주어진 문자열 s 길이의 반부터 길이 1까지 문자열을 자르는 단위길이 leng을 줄여나가며 압축된 문자열 길이 확인.
	//2. 반복문을 돌리면서 current문자열과 next문자열을 구하고 비교한다.
	//3. 해당문자열이 반복된 횟수 cnt를 업데이트하던중 next에 반복되지 않은 새로운 문자열이 나올시 기존 cnt와 current를 res문자열에 담기.
	//4. 잘려진 s의 마지막으로 남은 여분 문자열 next를 별도로 res에 담기.
	//5. answer 최솟값 대소비교 후 반환.
	//풀이시간 58분 14초
	public int solution(String s) {
        if(s.length()==1) return 1;
		int answer = 1001;
		for(int leng = s.length()/2; leng>0; leng--) {
			String current, next="",res="";
			int cnt=1;
			for(int pos=0; pos<=s.length()/leng; pos++) {
				current = next;
				int start = pos * leng;
				int end = Math.min(leng*(pos+1), s.length());
				next = s.substring(start,end);
				if(current.equals(next)) cnt++;
				else {
					res += (getCnt(cnt)+current);
					cnt=1;
				}
			}
			res += (getCnt(cnt)+next);
			answer=Math.min(answer, res.length());
		}
        return answer;
    }

	private String getCnt(int cnt) {
		
		return cnt>1?String.valueOf(cnt):"";
	}

}
