package com.ssafy.study.homework8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4963_섬의개수 {
	static int W;
	static int H;
	static int[][] arr;
	static boolean[][] visit;
	static int cnt;

	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		while (true) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if (W == 0 & H == 0)
				break;

			arr = new int[H][W];
			visit = new boolean[H][W];
			cnt = 0;

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (arr[i][j] == 1 && visit[i][j] == false) {
						dfs(i,j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}

	public static void dfs(int x, int y) { 
		visit[x][y] = true; 
		
		for (int dir = 0; dir < 8; dir++) {
			int nx = x + dx[dir]; 
			int ny = y + dy[dir]; 
						
			if (nx >= 0 && nx < H && ny >= 0 && ny < W && arr[nx][ny] == 1 && visit[nx][ny] == false) {
				dfs(nx,ny);
			} 
		}
	}
	
}