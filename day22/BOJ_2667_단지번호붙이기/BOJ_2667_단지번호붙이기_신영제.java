package com.ssafy.study.homework22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2667_단지번호붙이기 {
	
	static int N;
	static int count;
	
	
	
	static int[][] arr;
	static boolean[][] visited;
	
	static int[] di = {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dj = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		
		visited = new boolean[N][N];
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			String ss = br.readLine();
			for(int j=0; j<N; j++) {
				arr[i][j] = ss.charAt(j)-'0';
			}
		}
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] == 1 && !visited[i][j]) {
					count = 0; // 집의 개수 
					cnt++; // 단지수 
					dfs(i,j);
					list.add(count);
				}
			}
		}
		
		System.out.println(cnt); //개수
		
		Collections.sort(list); // 오름차순 정렬
		
		for(int i =0; i<list.size(); i++) {
			System.out.println(list.get(i)); //단지내 집의 수 출력
		}
	}

	private static void dfs(int x, int y) {
		visited[x][y] = true;
		count++;

		
		for(int i=0; i<4; i++) {
			int nx = x + di[i];
			int ny = y + dj[i];
			
			if( nx>=0 && ny>=0 && nx<N && ny<N && arr[nx][ny] == 1 && !visited[nx][ny]) {
				dfs(nx, ny);
			}
		}
		
		
		
	}

}





