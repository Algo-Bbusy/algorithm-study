package com.ssafy.study.homework7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {
	static int N;
	static int M;
	static int r;
	static int c;
	static int d;
	static int arr[][];
	static int sum = 1;
	static int di[] = { -1, 0, 1, 0 }; // 북 동 남 서
	static int dj[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					num = 9;
				}
				arr[i][j] = num;
			}
		}
		clean(r, c, d);
		System.out.println(sum);
	}

	public static void clean(int i, int j, int dir) {
		arr[i][j] = 1; // 청소 한곳 1로 변환

		for (int d = 0; d < 4; d++) {
			dir = (dir + 3) % 4; // 바라보는 방향의 왼쪽 방향
			int ni = i + di[dir];
			int nj = j + dj[dir];

			if ( 0 <= ni && ni < N && 0 <= nj && nj < M && arr[ni][nj] == 0) {
				sum++;
				clean(ni, nj, dir);
				return; //다른 곳을 청소할 수 있어 방지.
			}
		}
		
		// 방향을 바라본채로 후진 하기 위한.
		int back = (dir + 2) % 4;
		int ddi = i + di[back];
		int ddj = j + dj[back];

		if (0 <= ddj && ddj < M && 0 <= ddi && ddi < N && arr[ddi][ddj] != 9)
			clean(ddi, ddj, dir);
	}

}