package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_14500 {
	// 테트로미노
//  0차 예상 무작정 완전탐색을 돌릴경우 TLE가 발생할 가능성이 높다고 판단하여 배제. (시도는 해보지 않음)
//	1차 풀이법 시도 1*4 긴작대기 케이스, 2*2 정사각형 케이스, 2*3 그외의 케이스로 세경우로 나눠 완탐을 돌렸으나 TLE로 실패
//	2차 풀이법 시도 dfs를 사용하면 깊이 4일때 블록별 max값을 구해서 풀수 있는 문제라고 생각하였으나 ㅗ자 모양의 튀어나온 부분에서 기본적인 dfs형식으론 풀 수 없어서 실패
//	3차 풀이법 시도 ㅗ자 모양 블록을 제외한 다른경우는 전부 dfs를 수행하고 ㅗ자만 따로 ㅗ,ㅜ ㅓ,ㅏ 배열을 만들어서 풀어봄. 메모리 45860KB 시간 1016ms 성공
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N, M, max;
	static int[][] paper;
	static boolean[][] visited;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		paper = new int[N+3][M+3];
		visited = new boolean[N][M];
		max = Integer.MIN_VALUE;
		for(int y=0; y<N; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=0; x<M; x++) {
				paper[y][x]=Integer.parseInt(stk.nextToken());
			}
		}
		solve();
		out.write(max+"");
		out.flush();
		out.close();
		in.close();
	}
	private static void solve() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<M; x++) {
				dfs(y, x, 0, 0);
				blockO(y,x);
			}
		}
	}
	private static void blockO(int y, int x) {
		boolean[][] shape_blockO = {{false, true, false},{true, true, true}};
		int sum=0;
		//ㅗ자
		for(int sy=0; sy<2; sy++) {
			for(int sx=0; sx<3; sx++) {
				if(shape_blockO[sy][sx]) sum+=paper[y+sy][x+sx];
				if(sum>max) max=sum;
			}
		}
		sum=0;
		//ㅜ자
		for(int sy=0; sy<2; sy++) {
			for(int sx=0; sx<3; sx++) {
				if(shape_blockO[1-sy][2-sx]) sum+=paper[y+sy][x+sx];
				if(sum>max) max=sum;
			}
		}
		sum=0;
		//ㅓ자
		for(int sy=0; sy<2; sy++) {
			for(int sx=0; sx<3; sx++) {
				if(shape_blockO[sy][sx]) sum+=paper[y+sx][x+sy];
				if(sum>max) max=sum;
			}
		}
		sum=0;
		//ㅏ자
		for(int sy=0; sy<2; sy++) {
			for(int sx=0; sx<3; sx++) {
				if(shape_blockO[1-sy][2-sx]) sum+=paper[y+sx][x+sy];
				if(sum>max) max=sum;
			}
		}
	}
	private static void dfs(int y, int x, int depth, int sum) {
		if(depth==4) {
			max = Math.max(max, sum);
			return;
		}
		for(int d=0; d<4; d++) {
			int ny = y+dy[d];
			int nx = x+dx[d];
			
			if(checkBoundary(ny,nx) && !visited[ny][nx]) {
				visited[ny][nx]=true;
				dfs(ny,nx,depth+1,sum+paper[ny][nx]);
				visited[ny][nx]=false;
			}
		}
		return;
	}
	private static boolean checkBoundary(int ny, int nx) {
		if(ny<0 || nx<0 || ny>=N || nx>=M) return false;
		return true;
	}

}
