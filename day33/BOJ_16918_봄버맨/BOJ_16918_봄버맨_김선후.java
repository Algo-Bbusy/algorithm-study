package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_16918 {
	// 봄버맨
	//메모리 108828KB 시간 316ms
	//Solution 1.
	//1. 초기 맵 입력
	//2. 각 시간마다의 붐버맨 행동(2초단위로 폭탄 설치 3초주기로 폭탄 폭발)
	//3. N초까지 반복
	//4. 최종 맵 상태 갱신
	//5. 맵 출력
	//풀이시간 52분 17초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int R, C, N;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[][] map;
	
	private static class Node {
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static Queue<Node> q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		input();
		boomMan();
		drawMap();
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static void drawMap() {
		for(int y=0; y<R; y++) {
			for(int x=0; x<C; x++) {
				sb.append(map[y][x]==0?'.':'O');
			}
			sb.append("\n");
		}
	}
	private static void boomMan() {
		//2초부터 N초까지 1초에는 아무 행동도 안하므로
		for(int time = 2; time<=N; time++) {
			if(time%2 == 0) { //2초 주기로 폭탄 심기
				setBoom(time);
			}
			else {
				findBoom(time);
				fireBoom();
			}
		}
	}
	private static void fireBoom() {
		while(!q.isEmpty()) {
			Node current = q.poll();
			map[current.y][current.x] = 0;
			for(int d=0; d<4; d++) {
				int ny = current.y + dy[d];
				int nx = current.x + dx[d];
				if(ny>=0 && nx>=0 && ny<R && nx<C) map[ny][nx]=0;
			}
		}
	}
	private static void findBoom(int time) {
		for(int y=0; y<R; y++) {
			for(int x=0; x<C; x++) {
				if(map[y][x] <= time-2) {//3초전에 심은 폭탄
					q.add(new Node(y, x));
				}
			}
		}
	}
	private static void setBoom(int time) {
		for(int y=0; y<R; y++) {
			for(int x=0; x<C; x++) {
				if(map[y][x]==0) {
					map[y][x] = time;
				}
			}
		}
	}
	private static void input() throws IOException {
		stk = new StringTokenizer(in.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(stk.nextToken());
		map = new int[R][C];
		for(int y=0; y<R; y++) {
			char[] ch = in.readLine().toCharArray();
			for(int x=0; x<C; x++) {
				if(ch[x] == '.') map[y][x] = 0;
				else map[y][x] = 1;
			}
		}
	}

}
