package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_1890 {
	//점프
	//메모리 14368KB 시간 136ms
	//풀이시간 1시간 2분
	//지문을 읽으면서 dfs로 해결을 할 생각을 했다가 답이 최악 2^63-1이 나오므로 일반적인 dfs로는 시간초과가 확정.
	//dp와 dfs를 섞어서 사용하여 중복 구간에 대한 연산을 지울 필요가 있음(메모이제이션)
	//Solution 1.
	//1. 2차원 배열 map에 입력을 받고 같은 배열크기의 long타입 dp를 초기화한다.(미연결상태 표시를 위해 -1로 초기화)
	//2. 초기좌표로부터 dfs 연산 수행
	//   2-1. dp[y][x]의 방문처리를 위해 0으로 초기화
	//   2-2. 다음에 갈수있는 좌표는 현재좌표의 값을 각 y,x좌표에 더한 값이다.
	//   2-3. 만약 ny, nx가 N(배열 크기)보다 커지면 0을 반환한다.(즉 해당좌표의 dfs연산값은 0(이동실패)이다)
	//   2-4. 만약 아래나 옆으로 갈수 있다면 현재 연결 처리된 dp[y][x]와 dp[y][x]+dfs(다음좌표) 중 큰것을 dp[y][x]로 갱신한다.
	//   2-5. dp[y][x]를 반환한다.
	//   2-6. 만약 dp[y][x]가 이미 연결된 곳이라면 dp[y][x]를 반환한다.
	//   2-7. 만약 y x의 좌표가 끝점이라면 1을 반환한다. (dfs 더하기 연산에서 끝까지 연결된 경우만 더해주는 경우)
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N;
	static int[][] map;
	static long[][] dp;
	static long ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		ans = dfs(1,1);
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static long dfs(int y, int x) {
		if(dp[y][x] != -1) return dp[y][x];
		if(y==N && x==N) return 1;
		dp[y][x]=0;
		int ny = y+map[y][x];
		int nx = x+map[y][x];
		if(ny>N && nx>N) return 0;
		if(ny<=N) dp[y][x] = Math.max(dp[y][x], dp[y][x]+dfs(ny, x));
		if(nx<=N) dp[y][x] = Math.max(dp[y][x], dp[y][x]+dfs(y,nx));
		return dp[y][x];
	}
	private static void input() throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		map = new int[N+1][N+1];
		for(int y=1; y<=N; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=1; x<=N; x++) {
				map[y][x] = Integer.parseInt(stk.nextToken());
			}
		}
		dp = new long[N+1][N+1];
		for(int y=1; y<=N; y++) {
			for(int x=1; x<=N; x++) {
				dp[y][x]=-1;
			}
		}
	}

}
