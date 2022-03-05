package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_14502 {
	// 연구소
//	벽의 개수 3개 조합? dfs?
//	바이러스 인접거리에 퍼진다.->bfs
//	벽의 개수 3개를 세우는 모든 경우의 수에서 각 경우마다 바이러스를 퍼트리는 bfs를 수행
//	각 최종 결과맵에서 0의 개수 탐색
//	안전영역 대소비교
//	최대값 출력
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N,M;
	static int[][] map;
	static int[] dy= {-1,1,0,0}; //상하좌우
	static int[] dx= {0,0,-1,1};
	static int ans = Integer.MIN_VALUE;
	static class Virus{
		int y,x;

		public Virus(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	public static void main(String[] args) throws IOException {
		stk=new StringTokenizer(in.readLine());
		N=Integer.parseInt(stk.nextToken());
		M=Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		for(int y=0; y<N; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=0; x<M; x++) {
				map[y][x]=Integer.parseInt(stk.nextToken());
			}
		}
		dfs(0);
		System.out.println(ans);
	}
	private static void dfs(int depth) {
		if(depth==3) {
			bfs();
			return;
		}
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				if(map[y][x]==0) {
					map[y][x]=1;
					dfs(depth+1);
					map[y][x]=0;
				}
			}
		}
	}
	private static void bfs() {
		int[][] virus_map = new int[N][M];
		Queue<Virus> q = new LinkedList<Virus>();
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				virus_map[y][x] = map[y][x];
			}
		}
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				if(virus_map[y][x]==2) {
					q.offer(new Virus(y, x));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Virus current = q.poll();
			for(int d=0; d<4; d++) {
				int ny = current.y+dy[d];
				int nx = current.x+dx[d];
				
				if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
				if(virus_map[ny][nx]==0) {
					virus_map[ny][nx]=2;
					q.add(new Virus(ny, nx));
				}
			}
		}
		safeCheck(virus_map);
	}
	private static void safeCheck(int[][] virus_map) {
		int cnt=0;
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				if(virus_map[y][x]==0) cnt++;
			}
		}
		ans=Math.max(cnt, ans);
	}

}
