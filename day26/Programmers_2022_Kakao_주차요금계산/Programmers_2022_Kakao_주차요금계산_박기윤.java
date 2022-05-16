package Day26;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Programmers_2022_Kakao_주차요금계산_박기윤 {

	public static void main(String[] args) {
		int[] fees = { 120, 0, 60, 591 };
		String[] records = { "16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN" };
		System.out.println(Arrays.toString(solution(fees, records)));

	}

	public static int[] solution(int[] fees, String[] records) {
		TreeMap<String, StringBuilder> map = new TreeMap<>(); // key: 차량번호, value: 누적주차시간
		int recordsLen = records.length;
		StringTokenizer st = null;
		for (int i = recordsLen - 1; i >= 0; i--) { // 기록의 마지막부터
			st = new StringTokenizer(records[i], " ");
			String time = st.nextToken();
			int hour = Integer.parseInt(time.split(":")[0]); // 시
			int minute = Integer.parseInt(time.split(":")[1]); // 분
			String carId = st.nextToken(); // 차량번호
			String type = st.nextToken(); // 내역
			if (type.equals("OUT")) { // 출차인 경우
				int outVal = hour * 60 + minute;
				if (map.get(carId) != null)
					map.put(carId, map.get(carId).append(" ").append(outVal));
				else {
					StringBuilder outSb = new StringBuilder();
					outSb.append(outVal);
					map.put(carId, outSb);
				}
			} else { // 입차인 경우
				int inVal = hour * 60 + minute;
				if (map.get(carId) != null)
					map.put(carId, map.get(carId).append(" ").append(inVal));
				else {
					StringBuilder tmp = new StringBuilder();
					tmp.append(23 * 60 + 59);
					map.put(carId, tmp.append(" ").append(inVal));
				}
			}
		}

		int answer[] = new int[map.size()];
		int count = 0;
		for (Entry<String, StringBuilder> e : map.entrySet()) {
			// System.out.println(e.getKey() + " : " + e.getValue().toString());
			int time = 0;
			st = new StringTokenizer(e.getValue().toString(), " ");
			while (st.hasMoreTokens()) {
				int out = Integer.parseInt(st.nextToken());
				int in = Integer.parseInt(st.nextToken());
				time += (out - in);
			}
			// System.out.println(time);
			answer[count++] = getFee(fees, time);
		}
		return answer;
	}

	private static int getFee(int[] fees, int time) {
		if (time <= fees[0])
			return fees[1];
		else
			return (fees[1] + ((int) Math.ceil((double) (time - fees[0]) / fees[2]) * fees[3]));
	}

}
