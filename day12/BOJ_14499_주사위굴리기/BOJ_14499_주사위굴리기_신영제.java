package com.ssafy.study.homework12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_주사위굴리기 {
	
	static int N; //세로크기
	static int M; //가로크기
	static int x; // 좌표 0<= x <= N-1 // 세로
	static int y; // 좌표 0<= y <= M-1 // 가로
	static int K; // 명령의 개수	
	
	static int[][] arr; //칸
	static int[] dice = new int[7];
	
	static int[] di = {0, 0, -1, 1}; //동 서 북 남
	static int[] dj = {1, -1, 0, 0};
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++) {
			int move = Integer.parseInt(st.nextToken());
			
			int nx = x + di[move-1];
			int ny = y + dj[move-1];
			
			if( nx < 0 || nx >= N || ny < 0 || ny >= M ) continue;
			
			search(move, nx, ny);
			x = nx;
			y = ny;
		}
		
		
		
	}
	private static void search(int move, int x, int y) {
		int sky = dice[1]; 
		switch (move) {
		case 1:
			dice[1] = dice[3];
			dice[3] = dice[6];
			dice[6] = dice[4];
			dice[4] = sky;
			break;
		case 2:
			dice[1] = dice[4];
			dice[4] = dice[6];
			dice[6] = dice[3];
			dice[3] = sky;
			break;
		case 3:
			dice[1] = dice[5];
			dice[5] = dice[6];
			dice[6] = dice[2];
			dice[2] = sky;
			break;
		case 4:
			dice[1] = dice[2];
			dice[2] = dice[6];
			dice[6] = dice[5];
			dice[5] = sky;
			break;
		}
		if (arr[x][y] == 0) {
			arr[x][y] = dice[6];
		} else {
			dice[6] = arr[x][y];
			arr[x][y] = 0;
		}
		System.out.println(dice[1]);
	}
}