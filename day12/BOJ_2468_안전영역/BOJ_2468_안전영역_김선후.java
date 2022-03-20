package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_2468 {
	// 안전 영역
	// 문제를 보자마자 예전에 연구실문제라던가 이 문제와 흡사한 덩어리를 구하는 문제가 생각나서 
//	바로 dfs문제인걸 눈치챘습니다.
//	이 문제에서의 문제 해결과정은 
//	1.물이 높이에 따라서 침수가 되는 지역과 안전영역을 구분해주고
//	2.그에 따라서 dfs가 한번 수행을 마칠때마다 횟수를 모든 물의높이에서 구해준 뒤
//	3.그중에서 가장 최댓값을 구하면 됩니다.
//	메모리 18932KB 시간 256ms
//5
//6 8 2 6 2
//3 2 3 4 6
//6 7 3 3 2
//7 2 5 3 6
//8 9 5 2 7
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N, ans;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		int max = Integer.MIN_VALUE;
		for(int y=0; y<N; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=0; x<N; x++) {
				map[y][x] = Integer.parseInt(stk.nextToken());
				max = Math.max(max, map[y][x]);
			}
		}
		ans =Integer.MIN_VALUE;
		for(int rain=0; rain<=max; rain++) {
			int cnt=0;
			isVisited = new boolean[N][N];
			rainMap(rain);
			for(int y=0; y<N; y++) {
				for(int x=0; x<N; x++) {
					if(!isVisited[y][x]) {
						dfs(y,x);
						cnt++;
					}
				}
			}
			ans = Math.max(ans, cnt);
		}
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static void dfs(int y, int x) {
		isVisited[y][x]=true;
		for(int d=0; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			if(isSafe(ny,nx)) dfs(ny,nx);
		}
	}
	private static boolean isSafe(int ny, int nx) {
		if(ny>=0&&nx>=0&&ny<N&&nx<N&&!isVisited[ny][nx]) return true;
		return false;
	}
	private static void rainMap(int rain) {
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(map[y][x]<=rain) {
					isVisited[y][x]=true;
				}
			}
		}
	}
}
