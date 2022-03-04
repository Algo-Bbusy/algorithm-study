package com.ssafy.study.homework8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2210_숫자판점프 {

	public static int[][] arr;		
	public static int[] ans;
	public static ArrayList<String> list;
	
	public static int[] di = {-1, 0, 1, 0};
	public static int[] dj = {0, 1, 0, -1};	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		arr = new int[5][5]; 
		ans = new int[6]; 
		list = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				dfs(i, j, 1);
			}
		}

		System.out.println(list.size());
	}

	public static void dfs(int x, int y, int deep) {
	
		if (deep == 7) {
			String num = "";
			for (int i = 0; i < 6; i++)
				num = num + Integer.toString(ans[i]);
			
			if (!list.contains(num)) list.add(num);
			return;
		}

		
		ans[deep - 1] = arr[x][y];

		for (int i = 0; i < 4; i++) {			
			int ni = x + di[i];			
			int nj = y + dj[i];
			
			if (ni >= 0 && ni < 5 && nj >= 0 && nj < 5)
				dfs(ni, nj, deep + 1);

		}
	}
}
