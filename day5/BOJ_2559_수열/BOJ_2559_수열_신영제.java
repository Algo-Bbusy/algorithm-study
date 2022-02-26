package com.ssafy.study.homework5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2559_수열 {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());		
		int N = Integer.parseInt(st.nextToken()); // 전체 날짜 
		int K = Integer.parseInt(st.nextToken()); // 연속 날짜
		
		int max = Integer.MIN_VALUE;
		
		int[]arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			//System.out.print(arr[i]+ " ");
		}
		
		for(int i = 0; i < N-K+1; i++) {  // 범위만큼 돌기위해.
			int sum = 0;
			for(int j = i; j < i+K; j++) { // 연속적인 값 만큼 더한다
				sum += arr[j]; // K만큼 더하고 sum에 넣는다. 
			}
			max = Math.max(max, sum); // sum과 max값 비교 해서 max에 값넣기
		}
		System.out.println(max);
	}
}
