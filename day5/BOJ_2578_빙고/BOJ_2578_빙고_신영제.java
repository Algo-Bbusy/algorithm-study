package com.ssafy.study.homework5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2578_빙고 { // 빙고

	static int N; // 빙고크기.
	static int[][] all_arr;
	static int[][] real_arr;
	static int[][] you_arr;
	// static int you_num;
	static boolean[][] value_arr;
	static int binggo;
	static int r_zeroCount;
	static int c_zeroCount;
	static int l_zeroCount;
	static int a_zeroCount;
	static int cnt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = 5;

		// 숫자 입력.
		all_arr = new int[N * 2][N];
		real_arr = new int[N][N];
		you_arr = new int[N][N];
		value_arr = new boolean[N][N];
		binggo = 0;
		cnt = 0;

		for (int i = 0; i < N * 2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				all_arr[i][j] = Integer.parseInt(st.nextToken());
				// System.out.print(all_arr[i][j] + " ");
			}
			// System.out.println();
		}

		// 내 빙고
		for (int i = 0; i < N * 2; i++) {
			for (int a = 0; a < N; a++) {
				for (int s = 0; s < N; s++) {
					real_arr[a][s] = all_arr[i][s];
				}
			}
		}

		// 사회자
		for (int i = 5; i < N * 2; i++) {
			for (int b = 0; b < N; b++) {
				you_arr[i - 5][b] = all_arr[i][b];
				// System.out.print(you_arr[i-5][b] + " ");
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) { // 여기까지 사회자 번호 돌리기.
				for (int k = 0; k < N; k++) {
					for (int b = 0; b < N; b++) { // 여기까지 내번호 돌리기.
						if (you_arr[i][j] == real_arr[k][b]) {
							//if (value_arr[k][b] != true) {
								value_arr[k][b] = true;
								rcheck();
								cCheck();
								leftCheck();
								RightCheck();
								// int sum = r_zeroCount + c_zeroCount + l_zeroCount + a_zeroCount;
							 // end last if
						} // end if
					} // end b for
				} // end k for
				if (binggo >= 3) {
					//System.out.println(sum);
					//System.out.println(cnt);
					System.out.println(you_arr[i][j]);
					break;
				}
				binggo = 0;				
			} // end j for
		} // end i for
	}

	// 행체크
	public static void rcheck() {
		for (int i = 0; i < 5; i++) {
			r_zeroCount = 0;
			for (int j = 0; j < 5; j++) {
				if (value_arr[i][j] == true)
					r_zeroCount++;
			}
			if (r_zeroCount == 5)
				binggo++;
		}
	}

	// 열체크
	public static void cCheck() {
		for (int i = 0; i < 5; i++) {
			c_zeroCount = 0;
			for (int j = 0; j < 5; j++) {
				if (value_arr[i][j] == true)
					c_zeroCount++;
			}
			if (c_zeroCount == 5)
				binggo++;
		}
	}

	// 왼쪽에서 오른쪽으로 가는 대각선 체크
	public static void leftCheck() {
		l_zeroCount = 0;
		for (int i = 0; i < 5; i++) {
			if (value_arr[i][i] == true)
				l_zeroCount++;
		}
		if (l_zeroCount == 5)
			binggo++;
	}

	// 오른쪽에서 왼쪽으로 가는 대각선 체크
	public static void RightCheck() {
		a_zeroCount = 0;
		for (int i = 0; i < 5; i++) {
			if (value_arr[i][4 - i] == true)
				a_zeroCount++;
		}
		if (a_zeroCount == 5)
			binggo++;
	}

}
