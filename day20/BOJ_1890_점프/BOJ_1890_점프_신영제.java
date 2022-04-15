package com.ssafy.study.homework20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1890_점프 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N + 1][N + 1]; //기존 정보를 담을 배열 생성
		long[][] dp = new long[N + 1][N + 1]; // 경로의 갯수를 저장하는 동적테이블 생성

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[0][0] = 0; 
		dp[1][1] = 1; // 초기 시작하는 곳이니 1로 설정 
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i == N && j == N) break; // 종료 조건				
				
				int value = arr[i][j];
				if(value+j < N+1) dp[i][value+j] = dp[i][value+j] + dp[i][j]; // Right
				if(value+i < N+1) dp[value+i][j] = dp[value+i][j] + dp[i][j]; // Down
				
			}
		}

		System.out.println(dp[N][N]);
	}

}
