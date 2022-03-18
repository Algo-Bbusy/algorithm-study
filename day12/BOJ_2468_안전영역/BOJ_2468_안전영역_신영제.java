package com.ssafy.study.homework12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2468_안전영역 {
	
	static int N;
	static int[][] arr;
	static boolean[][] checked;
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		int maxheight = 0;
		checked = new boolean[N][N];
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] > maxheight) maxheight = arr[i][j];
			}
		}
		
		for(int h = 0; h <= maxheight; h++) {
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(arr[i][j] > h && !checked[i][j]) {
						dfs(i, j, h);
						// bfs(i,j,h);
						cnt++;
					}
				}
			}
			for(boolean a[]: checked)
	        	Arrays.fill(a, false);
			
			max = Math.max(max, cnt);
		}
		System.out.println(max);
	}

	static void dfs(int x, int y, int height) {
		checked[x][y] = true;
		
		for(int k = 0; k < 4; k++) {
			int nx = x + di[k];
			int ny = y + dj[k];
			
			if(nx >= 0 && nx < N && ny >= 0 && ny < N && arr[nx][ny] > height && !checked[nx][ny]) {
				dfs(nx, ny, height);
			}
		}
	}
}
