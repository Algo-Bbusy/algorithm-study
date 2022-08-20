package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_1012 {
//유기농 배추
//메모리 15840KB 시간 168ms
//완전탐색, bfs/dfs 문제
//2차원 배열을 탐색하면서 인접한 배추 덩어리 영역 개수를 구하는 문제
//Solution 1.
//1. 배추 밭 2차원 배열 map 입력 (배추가 있는 좌표는 1)
//2. map 전체를 탐색하면서 배추가 있는 장소이면서 한번도 방문한적 없으면 dfs탐색 후 영역 수 1 증가
//3. 완전탐색이 끝난 이후 최종 영역 개수 출력
//풀이시간 34분 18초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N, M, K, T, ans;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] isVisited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			input(); //tc별 배추밭 배열 입력
			solve(); //문제 풀이
		}
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static void input() throws IOException {
		stk = new StringTokenizer(in.readLine());
		M = Integer.parseInt(stk.nextToken()); //배추밭 가로 길이
		N = Integer.parseInt(stk.nextToken()); //배추밭 세로 길이
		K = Integer.parseInt(stk.nextToken()); //배추 개수
		
		map = new int[N][M]; //배추밭 초기화
		isVisited = new boolean[N][M]; //방문체크 초기화
		//배추밭 배추 좌표 입력
		for(int i=0; i<K; i++) {
			stk = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			map[y][x]=1;
		}
	}
	private static void solve() {
		ans = 0; //배추지렁이 최소 개수 초기화
		//완탐을 돌면서 dfs탐색, 한번 탐색이 시작될 때마다 정답값 1 증가
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				if(map[y][x]==1 && !isVisited[y][x]) {
					dfs(y,x);
					ans++;
				}
			}
		}
		sb.append(ans).append("\n");
	}
	private static void dfs(int y, int x) {
		//해당 좌표 방문체크
		isVisited[y][x] = true;
		
		for(int d=0; d<4; d++) {
			int ny = y+dy[d];
			int nx = x+dx[d];
			
			if(ny>=0 && nx>=0 && ny<N && nx<M) {
				if(map[ny][nx]==1 && !isVisited[ny][nx]) {
					dfs(ny,nx);
				}
			}
		}
	}
}
