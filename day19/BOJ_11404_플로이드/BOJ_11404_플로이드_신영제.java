package com.ssafy.study.homework19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11404_플로이드 {

	static final int INF = 99999999;
	static int[][] arr;
	static int N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				arr[i][j] = INF;
				if (i == j) {
					arr[i][j] = 0;
				}
			}
		}

		// 2) 인접행렬 입력.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			arr[from][to] = Math.min(arr[from][to], num);
		}

		// 3) 최소비용을 업데이트
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k)	continue;
				for (int j = 1; j <= N; j++) {
					if (i == j || k == j) continue;
					if (arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
		print();
	}

	private static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (arr[i][j] >= INF) arr[i][j] = 0;
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
