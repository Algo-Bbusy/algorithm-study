package com.ssafy.study.homework6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10157_자리배정 {

	static int C, R; // C = j R = i
	static int K; // 원하는 값
	static int[][] arr; // 공연장 관객담을 공간.
	static int[] dr = { 1, 0, -1, 0 }; // 하 우 상 좌
	static int[] dc = { 0, 1, 0, -1 }; // 하 우 상 좌

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		K = Integer.parseInt(br.readLine());
		arr = new int[R][C];

		int num = 1;
		int move = 0;
		int r = 0; //i
		int c = 0; //j

		// 반시계 방향으로 숫자 증가
		for (int i = 0; i < R * C; i++) {

			arr[r][c] = num;
			
			if(arr[r][c] == K) System.out.printf("%d %d", c+1, r+1);
			else if(R*C < K) { 
				System.out.println(0);
				break;
			}

			int di = r + dr[move]; // i
			int dj = c + dc[move]; // j
			
			if (di < 0 || di >= R || dj < 0 || dj >= C || arr[di][dj] != 0) {
				move = ++move % 4;
			}

			r = r + dr[move];
			c = c + dc[move];
			++num;
		}

//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				if(arr[i][j] == K) { // k값과 일치하는애 만나면 뽑기.
//					System.out.printf("%d %d", j+1, i+1); // 0부터 시작하니까 +1씩	 				
//				}				
//			}
//			if(R*C < K) { //K가 지정칸보다 많으면 0 출력.
//				System.out.print(0);
//				break;
//			}
//			
//		}

	}

}
