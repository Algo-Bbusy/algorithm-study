package com.ssafy.study.homework14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1167_트리의지름 {
	static int arr[][];
	static int N;
	static boolean visit[];
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		visit = new boolean[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());

			arr[x][y] = val;
			arr[y][x] = val;

			int endNum = Integer.parseInt(st.nextToken());

			while (endNum != -1) {
				y = endNum;
				val = Integer.parseInt(st.nextToken());
				arr[x][y] = val;
				arr[y][x] = val;
				endNum = Integer.parseInt(st.nextToken());
			}
			
		}
	}
}
