package com.ssafy.study.homework9_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크 {

	static int N;
	static int[][] arr;
	static boolean[] visit;

	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];
		visit = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		combination(0, 0);
		System.out.println(min);
	}

	static void combination(int cnt, int deep) {

		if (deep == N / 2) {
			team_sum();
			return;
		}

		for (int i = cnt; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				combination(i + 1, deep + 1);
				visit[i] = false;
			}
		}
	}

	static void team_sum() {
		int start_team = 0;
		int link_team = 0;

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (visit[i] == true && visit[j] == true) {
					start_team += arr[i][j];
					start_team += arr[j][i];
				}
				else if (visit[i] == false && visit[j] == false) {
					link_team += arr[i][j];
					link_team += arr[j][i];
				}
			}
		}

		int val = Math.abs(start_team - link_team);

		min = Math.min(val, min);
		
	}

}