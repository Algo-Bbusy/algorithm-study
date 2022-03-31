package com.ssafy.study.homework14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_3190_뱁 {

	static int N;
	static int K;
	static int[][] arr;
	static int L;
	static int[] time;
	static int[] dir;
	static int[] dx = { -1, 0, 1, 0 }; // 북동남서
	static int[] dy = { 0, 1, 0, -1 };
	static Deque<ssnake> snake = new ArrayDeque<ssnake>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());

		arr = new int[N + 1][N + 1];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
		}

		L = Integer.parseInt(br.readLine());
		time = new int[L];
		dir = new int[L];

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());

			time[i] = Integer.parseInt(st.nextToken());
			// (오른쪽 1) (왼쪽 3)
			String ddir = st.nextToken();
			if (ddir.equals("D")) {
				dir[i] = 1;
			} else {
				dir[i] = 3;
			}
		}
		moveSnake();
	}

	static void moveSnake() {
		int snakeDir = 1;
		int cnt = 0;
		int idx = 0;
		
		arr[1][1] = 2; 
		
		snake.add(new ssnake(1, 1));

		while (true) {
			if (idx < L && time[idx] == cnt) {
				snakeDir = (snakeDir + dir[idx]) % 4;
				idx++;
			}

			int nx = snake.getFirst().x + dx[snakeDir];
			int ny = snake.getFirst().y + dy[snakeDir];

			if (nx <= 0 || nx > N || ny <= 0 || ny > N || arr[nx][ny] == 2) {
				System.out.println(cnt + 1);
				break;
			}

			if (arr[nx][ny] == 1) {
				arr[nx][ny] = 2;
				snake.addFirst(new ssnake(nx, ny));
			} else if (arr[nx][ny] == 0) {
				arr[nx][ny] = 2;
				snake.addFirst(new ssnake(nx, ny));
				ssnake tail = snake.removeLast();
				arr[tail.x][tail.y] = 0;
			}
			cnt++;
		}
	}

	static class ssnake {
		int x;
		int y;

		public ssnake(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
