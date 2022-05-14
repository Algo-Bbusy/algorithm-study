package com.ssafy.study.homework26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Pro_주차요금계산 {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
							"18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		System.out.println(Arrays.toString(soultion(fees, records)));

	}

	private static int[] soultion(int[] fees, String[] records) {
		int lastTime = getMin("23:59");
		Map<String, Integer> parking = new HashMap<>();  // 현재 파킹 중인 차들
		Map<String, Integer> times = new HashMap<>();    // 차들의 누적 파킹시간
		List<String> cars = new ArrayList<>();         	 // 차들 list
		
		for(String record : records) {
			String[] info = record.split(" "); // 차들의 정보를 분리.
			int time = getMin(info[0]); // 해당 시간을 분으로 가져온다.
			String car = info[1]; // 차 등록. 
			
            // 새로운차 등장 
			if(!cars.contains(car)) {
				cars.add(car);
				times.put(car, 0); // 누적 파킹시간에 차 와 0으로 추가. 
			}
			
			// containsKey --> 맵에 키, 값 있는지 확인. 
			if(parking.containsKey(car)) { // 현재 파킹이 되어 있다면 출차
				times.put( car, times.get(car)+(time-parking.get(car)) ); //출차이므로 시간 계산. 
				parking.remove(car); // 나갔으니 제거
			} else {
            	// 파킹이 안되어 있다면 입차
				parking.put(car, time);
			}
			
		}
		
		int[] ret = new int[cars.size()];        	
		Collections.sort(cars); // 차번 순으로 정렬
		
		for(int i=0 ; i<cars.size() ; i++) {
			ret[i] = fees[1]; 
			String car = cars.get(i);
			int time = times.get(car)-fees[0]; // 누적시간중 기본요금 시간 제외
			if(parking.containsKey(car)) time += (lastTime-parking.get(car)); // 아직 출차가 안되었다면 마지막시간으로 정산
			if(time>0) ret[i] += (Math.ceil(time/(fees[2]*1.0))*fees[3]);     // 요금 정산
			// Math.ceil --> 입력 인자 값보다 같은 가장 작은 정수 값을 double형으로 반환. 
			// 초과 시간 계산을 위해 사용. 
		}
		
		return ret;
    }
    
    public static int getMin(String time) {
		String[] t = time.split(":");
		return Integer.parseInt(t[0])*60+Integer.parseInt(t[1]);
	}
    
}
