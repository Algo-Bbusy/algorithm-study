package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_23288 {
	// 주사위 굴리기 2
	//메모리 35620KB 시간 224ms
	//저번 주사위 굴리기 1문제의 응용문제. 이번에는 Dice 클래스를 따로 객체로서 제어함.
	//또 Point를 사용했다가 정말 영문모를 출력값 오류로 인해 그냥 Node 클래스를 따로 선언함.
	//어지간해선 Point 사용을 하지 않거나 좌표를 y, x 가 아닌 x, y로 사용 습관을 들여야 할 것 같음.
	//Solution 1.
	//1. input()으로 정보 입력
	//2. 주사위 Dice 객체 초기 설정 선언 0,0 시작방향 1 -> 방향
	//3. K번 만큼 주사위 굴리고 점수 추가
	//   3-1. dir 방향으로 주사위를 옮길 시 경계를 벗어나면 역방향 갱신
	//   3-2. 이동할 좌표로 현재 좌표 갱신
	//   3-3. 주사위 굴리기로 주사위 멤버변수 갱신
	//   3-4. 현재 칸의 점수 획득
	//   3-5. 주사위 다음 방향 설정
	//풀이시간 1시간 26분 23초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N,M,K,ans;
	static int[][] map;
	static int[] dy = {-1, 0, 1, 0}; //0 1 2 3 위 오른쪽 아래 왼쪽
	static int[] dx = {0, 1, 0, -1};
	static class Dice{
		int top, bottom, left, right, front, reer;
		public Dice() {
			top = 1; bottom = 6; left = 4; right = 3; front = 5; reer = 2;
		}
		public void rollDice(int dir) {
			if(dir==0) rollUp();
			else if(dir==1) rollRight();
			else if(dir==2) rollDown();
			else rollLeft();
		}
		public void rollLeft() {
			int temp = top;
			top = right;
			right = bottom;
			bottom = left;
			left = temp;
		}
		public void rollDown() {
			int temp = top;
			top = reer;
			reer = bottom;
			bottom = front;
			front = temp;
		}
		public void rollRight() {
			int temp = top;
			top = left;
			left = bottom;
			bottom = right;
			right = temp;
		}
		public void rollUp() {
			int temp = top;
			top = front;
			front = bottom;
			bottom = reer;
			reer = temp;
		}
	}
	static class Node{
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	public static void main(String[] args) throws IOException {
		input();
		Dice dice = new Dice();
		int y=0, x=0;
		int dir = 1;
		ans = 0;
		while(K-->0) {
			if(!isBoundary(y+dy[dir],x+dx[dir])) dir = reverseDir(dir);
			
			y = y+dy[dir];
			x = x+dx[dir];
			
			dice.rollDice(dir);
			
			ans += plusAns(y,x);
			dir = nextDir(dice.bottom, map[y][x], dir);
		}
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static int nextDir(int bottom, int num, int dir) {
		if(bottom > num) return (dir+1)%4;
		else if(bottom < num) return (dir+3)%4;
		else return dir;
	}
	private static int plusAns(int y, int x) {
		boolean[][] isVisited = new boolean[N][M];
		isVisited[y][x]=true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(y,x));
		int num = map[y][x];
		int cnt = 1;
		while(!q.isEmpty()) {
			Node current = q.poll();
			for(int d=0; d<4; d++) {
				int ny = current.y + dy[d];
				int nx = current.x + dx[d];
				if(isBoundary(ny, nx) && !isVisited[ny][nx] && map[ny][nx]==num) {
					isVisited[ny][nx]=true;
					cnt++;
					q.add(new Node(ny,nx));
				}
			}
		}
		return num*cnt;
	}
	private static int reverseDir(int dir) {
		return (dir+2)%4;
	}
	private static boolean isBoundary(int ny, int nx) {
		if(ny<0 || ny>=N || nx<0 || nx>=M) return false;
		return true;
	}
	private static void input() throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		
		for(int y=0; y<N; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=0; x<M; x++) {
				map[y][x]=Integer.parseInt(stk.nextToken());
			}
		}
	}

}
