package com.ssafy.study.homework18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11053_가장긴증가하는부분수열 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine()); // 수열의 크기
		int[] arr = new int[N]; // 수열의 원소들 저장
		int[] LIS = new int[N]; // 자신을 끝으로 하는 LIS길이 // 동적테이블

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int max = 0; // 해당 수열의 LIS최장길이
		for (int i = 0; i < N; i++) {// 모든 원소에 대해 자신을 끝으로하는 LIS길이 계산
			LIS[i] = 1; // 자신 혼자 LIS구성할때의 길이 1로 초기화
			for (int j = 0; j < i; j++) { // 첫 원소부터 i원소 직전까지 비교
				if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) { // arr[j] < arr[i] : 증가수열의 모습
					LIS[i] = LIS[j] + 1;
				}
			}
			if (max < LIS[i])
				max = LIS[i];
		}
		System.out.println(max);
	}

}

