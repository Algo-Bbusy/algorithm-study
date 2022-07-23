package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_2146 {
// 다리 만들기
//메모리 296032KB 시간 1348ms
//풀이시간 1시간 24분 32초
//특정 덩어리(그룹) 탐색, 최단경로 =>bfs/dfs 사용 유추
//섬을 찾은 후 섬의 덩어리(그룹)별로 넘버링을 해준 후 각 섬마다의 최단 경로를 찾는 문제임을 파악.
//Solution 1.
//1. 입력하기(2차원배열 맵, 섬넘버링 초기화, 정답값 초기화)
//2. 육지(섬)찾기
//3. 육지(섬) 넘버링
//4. 정답(최단경로다리 놓을 위치)찾기
//5. 정답만들기(최단경로다리 생성 및 갱신)
//6. 출력하기
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
//	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N, landNum, ans;
	static int[][] map;
	static int[] dy = new int[] {1,-1,0,0};
	static int[] dx = new int[] {0,0,-1,1};
	static boolean[][] isVisited;
	static class Position{
		int y,x;
		int depth;
		public Position(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		public Position(int y, int x, int depth) {
			super();
			this.y = y;
			this.x = x;
			this.depth = depth;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		findLand();
		findAns();
		output();
	}
	//출력
	private static void output() throws IOException {
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	//정답 찾기
	private static void findAns() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(map[y][x]>1) makeAns(new Position(y,x,0));
			}
		}
	}
	//정답 만들기(최단경로 다리)
	private static void makeAns(Position p) {
		isVisited = new boolean[N][N];
		Queue<Position> q = new LinkedList<>();
		q.add(p);
		isVisited[p.y][p.x] = true;
		while(!q.isEmpty()) {
			Position current = q.poll();
			for(int d=0; d<4; d++) {
				int ny = current.y + dy[d];
				int nx = current.x + dx[d];
				//다음 위치가 경계내이고 방문한적 없으며 현재 위치와 번호가 다를경우(바다거나 다른 섬인경우)
				if(isBoundary(ny, nx) && !isVisited[ny][nx] && map[ny][nx] != map[p.y][p.x]) {
					isVisited[ny][nx]=true;
					//바다일때
					if(map[ny][nx]==0) q.add(new Position(ny, nx, current.depth+1));
					else ans = Math.min(ans, current.depth);
				}
			}
		}
	}
	//육지 탐색
	private static void findLand() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(map[y][x]==1) putLandNum(new Position(y, x));
			}
		}
	}
	//육지덩어리(섬) 넘버링
	private static void putLandNum(Position p) {
		Queue<Position> q = new LinkedList<>();
		landNum++;
		map[p.y][p.x]=landNum;
		q.add(p);
		while(!q.isEmpty()) {
			Position current = q.poll();
			for(int d=0; d<4; d++) {
				int ny = current.y + dy[d];
				int nx = current.x + dx[d];
				
				if(isBoundary(ny,nx) && map[ny][nx]==1) {
					q.add(new Position(ny, nx));
					map[ny][nx]=landNum;
				}
			}
		}
	}
	//경계체크
	private static boolean isBoundary(int ny, int nx) {
		if(ny>=0&&nx>=0&&ny<N&&nx<N) return true;
		return false;
	}
	//입력
	private static void input() throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
//		isVisited = new boolean[N][N];
		landNum = 1;
		ans = Integer.MAX_VALUE;
		for(int y=0; y<N; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=0; x<N; x++) {
				map[y][x] = Integer.parseInt(stk.nextToken());
			}
		}
	}

}
