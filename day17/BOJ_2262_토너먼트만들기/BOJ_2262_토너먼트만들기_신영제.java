package com.ssafy.study.homework17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 *  
 *  1) 우승자는 항상 1번.
 *  2) 자신의 양 옆 사람중에서 랭크차이가 적게나는 사람과 경기. (랭크 차이가 많이나면 경기를 많이 해야함)
 *  3) 랭킹 낮은 사람은 제거해간다.
 *  4) 양옆중 차이가 적게나는 값들을 저장하여 출력.
 */

public class BOJ_2262_토너먼트만들기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>(); // list에 rank를 정보를 담는다.
		int diff = 0; //값들의 합

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int rank = Integer.parseInt(st.nextToken());
			list.add(rank);
		}
		
		int max = N;
		for (int i = 0; i < N - 1; i++) {
			int idx = list.indexOf(max);
			if (idx == 0) { //0번째 인덱스일 경우 
				diff += list.get(idx) - list.get(idx + 1);
			} else if (idx == list.size() - 1) //N번째 인덱스일 경우
				diff += list.get(idx) - list.get(idx - 1);
			else // 이외의 경우 좌-우 값들중 최소값을 담는다. 
				diff += Math.min(list.get(idx) - list.get(idx + 1), list.get(idx) - list.get(idx - 1));
			list.remove(idx); // 해당 인덱스를 제거
			max--; 
		}
		System.out.println(diff);
	}
}







