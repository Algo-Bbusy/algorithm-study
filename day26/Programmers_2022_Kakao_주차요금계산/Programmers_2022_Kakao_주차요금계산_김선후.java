package kakao2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Programmers_2022_Kakao_주차요금계산 {
	//주차요금계산
	//자동차 번호를 key값으로 놓고 입차시간과 출차시간을 계산하고 관리한다.
	//두개의 맵을 사용하는데 차량 번호가 작은 자동차부터 정렬해야 하므로 하나는 Treemap 선언
	//records 배열 기준 In이 들어오면 Hashmap out이 들어오면 Treemap에 분으로 치환한 시간 저장
	//out이 들어올 때 기존 in에 있는 데이터 삭제
	//마지막까지 in에 남아있는 데이터는 23시 59분 기준 계산
	//최종적으로 트리맵 out에 key값 차번호 value값 사용한 시간이 담겨져 있음.
	//초과된 시간이 0분보다 높다면 올림처리. Math.ceil 사용
	//풀이시간 1시간 12분 14초
	public int[] solution(int[] fees, String[] records) {
		HashMap<String, Integer> in = new HashMap<>();
        TreeMap<String, Integer> out = new TreeMap<>();
        for(String s : records) {
        	//temp[0] 시각 temp[1] 차량번호 temp[2] 내역
        	String[] temp = s.split(" ");
        	if(temp[2].equals("IN")) {
        		int min = hourToMin(temp[0]);
        		if(!out.containsKey(temp[1])) out.put(temp[1], 0);
        		in.put(temp[1], min);
        	}
        	else if(temp[2].equals("OUT")) {
        		int min = hourToMin(temp[0]);
        		out.put(temp[1], out.get(temp[1])+ min-in.get(temp[1]));
        		in.remove(temp[1]);
        	}
        }
        if(!in.isEmpty()) {
        	for(String key : in.keySet()) out.put(key, out.get(key)+(23*60+59)-in.get(key));
        }
        ArrayList<Integer> answer = new ArrayList<>();
        for(int ans : out.values()) {
        	//fees[0] 기본시간 fees[1] 기본요금 fees[2] 단위시간 fees[3] 단위요금
        	if(ans <= fees[0]) answer.add(fees[1]);
        	else {
        		int res = (int) Math.ceil((ans-fees[0])/(double)fees[2]);
        		answer.add(fees[1] + res*fees[3]);
        	}
        }
		//Integer->int[] 이부분은 검색했음..
        return answer.stream().mapToInt(i->i).toArray();
    }

	private int hourToMin(String hourTime) {
		String[] time = hourTime.split(":");
		int min = Integer.parseInt(time[0]) * 60;
		min += Integer.parseInt(time[1]);
		return min;
	}
}
