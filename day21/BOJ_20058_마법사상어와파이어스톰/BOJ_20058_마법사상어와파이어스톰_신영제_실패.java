package com.ssafy.study.homework21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058_마법사상어와파이어스톰 {

	static int N, Q;
	static int size = 0;
	static int max = 0; // 칸의 개수

	static int[][] arr;
	static boolean[][] visited;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static public class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		size = (int) Math.pow(2, N); // 제곱저장할 변수
		arr = new int[size][size];

		// 배열에 값들을 담음.
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 반복해야하는 공간
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
		}

		// 얼음덩이에 속해있는지 확인하는 방문 체크
		visited = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (!visited[i][j] && arr[i][j] > 0) {
					visited[i][j] = true;
					max = Math.max(max, dfs(i, j)); // 가장 큰 얼음덩어리가 차지하는 칸수 갱신
				}
			}
		}

		System.out.println(max);
	}
	
	// 얼음 칸수 구하기
	static int dfs(int x, int y) {
		int count = 1; // 시작 좌표를 포함해야 함으로 1로 초기화

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= size || ny < 0 || ny >= size) continue;

			if (arr[nx][ny] > 0 && !visited[nx][ny]) {
				visited[nx][ny] = true;
				count += dfs(nx, ny);
			}

		}
		return count; // 칸수 return
	}
	
	// 얼음 녹이기.
	static int bfs() {		
		Queue<Node> q = new LinkedList<>();
		// 사방탐색 하면서 얼음이 있는 칸 확인? 
		// 얼음이 없는칸이 2칸 이상이면 얼음을 -1
	}

}