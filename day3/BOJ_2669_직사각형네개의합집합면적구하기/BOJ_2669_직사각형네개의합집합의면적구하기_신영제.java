package com.ssafy.study.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2669 {
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] arr = new int[101][101]; // 0으로 이미 초기화 되있다.

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 꼭지점 입력.
			int under_x = Integer.parseInt(st.nextToken());
			int under_y = Integer.parseInt(st.nextToken());
			int top_x = Integer.parseInt(st.nextToken());
			int top_y = Integer.parseInt(st.nextToken());
			
			// (top_x - under_x) * (top_y - under_y) 하면 넓이가 나온다. 
			
			for(int a = under_x; a < top_x; a++) {
				for(int b = under_y; b < top_y; b++) {
					arr[a][b] = 1;
					if(arr[a][b] == 1) { // 1이여도 그냥 또 1로 덮기.
						arr[a][b] = 1;
					}
				}
			}
			//1의 개수가 곧 정답.
			cnt = 0;
			for(int a = 0; a <= 100; a++) {
				for(int b = 0; b <= 100; b++) {
					if(arr[a][b] == 1) {
						cnt++;
					}
				}
			}
			
		}
		System.out.println(cnt);

	}

}
