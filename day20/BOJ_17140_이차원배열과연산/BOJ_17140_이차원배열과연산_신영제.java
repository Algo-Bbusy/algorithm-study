package com.ssafy.study.homework20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17140_이차원배열과연산 {

	static int r, c, k;
	static int row, col, time;
	static int[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		row = 3;
		col = 3;
		time = 0;

		A = new int[100][100];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {

			// 종료 조건
			if (A[r - 1][c - 1] == k || time > 100)
				break;

			// R연산
			if (row >= col) Rcalc();
			else Ccalc();
			time++;
		}
		

		if (time > 100)	System.out.println(-1);
		else System.out.println(time);
	}
	

	private static void Rcalc() {
		int max = 0;

		for (int i = 0; i < row; i++) {
			int[] numCnt = new int[101]; // 등장횟수를 담을 공간
			int size = 0; // 배열의 크기를 확인하기 위한 변수

			for (int j = 0; j < col; j++) {
				numCnt[A[i][j]]++;
			}

			if (size < 100) { // 배열의 크기가 100을 넘지 않는 다면
				for (int j = 1; j <= col; j++) {
					for (int b = 1; b < 101; b++) {
						if (numCnt[b] == j) { // 등장횟수와 같다면
							A[i][size++] = b; // 숫자 넣고
							A[i][size++] = j; // 등장횟수
						}
					}
				}
				// 0으로 채울 부분이 있다면 0으로 채움
				for (int j = size; j <= col; j++) {
					A[i][j] = 0;
				}
			}
			max = Math.max(max, size); // col의 크기를 정하는 값
		}
		col = max;
	}

	private static void Ccalc() {

		int max = 0;

		for (int i = 0; i < col; i++) {
			int numCnt[] = new int[101];
			int size = 0;

			for (int j = 0; j < row; j++) {
				numCnt[A[j][i]]++;
			}

			if (size < 100) {
				for (int j = 1; j <= row; j++) {
					for (int b = 1; b < 101; b++) {
						if (numCnt[b] == j) {
							A[size++][i] = b;
							A[size++][i] = j;
						}
					}
				}
				for (int j = size; j <= row; j++) {
					A[j][i] = 0;
				}
			}
			max = Math.max(max, size);
		}
		row = max;

	}

}