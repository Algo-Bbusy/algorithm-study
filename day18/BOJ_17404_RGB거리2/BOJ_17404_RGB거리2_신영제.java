package com.ssafy.study.homework18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1) N번 집의 색은 N-1번과 1번째의 집과 색이 같지 않아야 합니다. 
 * 2) N번의 색을 정하기 위해서 나머지에 INF값을 넣어놨습니다.
 * 3) 1번째 집이 빨강, 초록, 파랑의 경우가 있으므로 3번 반복을 돌렸습니다.
 * 4) 1번째 집을 고정시키고 2번째 집부터 조건에 해당하는 최소값들을 D테이블에 넣습니다.
 * 5) 첫집의 선택에 따라 최소비용을 담아 놓습니다.
 * 6) 파랑까지 반복이 끝났으면 빨강, 초록, 파랑의 최소비용중 최소비용을 선택해 출력 합니다. 
 */

public class BOJ_17404_RGB거리2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int INF = 9999;
		int[][] cost = new int[N + 1][3];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] d = new int[N + 1][3];
		int[] minCost = new int[N + 1];

		// 빨강:0 초록:1 파랑:2
		for (int i = 0; i < 3; i++) {

			// 첫번째집 색깔 선택.
			for (int j = 0; j < 3; j++) {
				if (i == j)	d[1][j] = cost[1][j]; // 첫번째 집에 색칠
				else d[1][j] = INF; // 이외의 집은 X
			}

			// 두번째집 부터 색깔 선택
			for (int k = 2; k <= N; k++) {

				// k개 집을 칠할 때 k번째 집을 빨강으로 칠할 때 최소비용
				d[k][0] = Math.min(d[k - 1][1], d[k - 1][2]) + cost[k][0];
				d[k][1] = Math.min(d[k - 1][0], d[k - 1][2]) + cost[k][1];
				d[k][2] = Math.min(d[k - 1][0], d[k - 1][1]) + cost[k][2];

				if (k == N) { // 마지막집과 첫번째 집은 같은색이면 안된다.
					if (i == 0) minCost[i] = Math.min(d[N][1], d[N][2]);
					if (i == 1) minCost[i] = Math.min(d[N][0], d[N][2]);
					if (i == 2) minCost[i] = Math.min(d[N][0], d[N][1]);				
				}
			}

		}
		System.out.println(Math.min(Math.min(minCost[0], minCost[1]), minCost[2]));
	}
}