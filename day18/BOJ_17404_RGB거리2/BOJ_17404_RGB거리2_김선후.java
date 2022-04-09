package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_17404 {

	// RGB거리2
	// dp[i][j]=ans => 1~i번째 집까지 색을 칠하는데 i번째 집을 j색으로 칠했을 때의 최소비용=ans
	// j 0 R 1 G 2 B
	// RGB거리 문제와는 달리 첫번째 집과 마지막 번째 집이 원형으로 연결되어 있는 형태이다.
	// 첫번째 집의 색을 고정한다. =>선택한 색깔이 최소비용이라고 가정시킨다.
	// 첫집 : 빨간색 => 첫집의 초록색,파란색의 dp값을 무한대로 설정 => 마지막 집을 초록색,파란색으로 칠한 dp값중 최솟값 갱신
	// 첫집 : 초록색 => 첫집의 빨간색,파란색의 dp값을 무한대로 설정 => 마지막 집을 빨간색,파란색으로 칠한 dp값중 최솟값 갱신
	// 첫집 : 파란색 => 첫집의 초록색,빨간색의 dp값을 무한대로 설정 => 마지막 집을 초록색,빨간색으로 칠한 dp값중 최솟값 갱신
	// 첫집의 색깔에 따른 각각의 dp 최솟값 결과중 가장 최소가 되는 값을 ans로 출력
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int num, ans;
	static int[][] dp, house;
	static final int INF = 1000*1000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		num = Integer.parseInt(in.readLine());
		dp = new int[num+1][3];
		house = new int[num+1][3];
		for (int i = 1; i <= num; i++) {
			stk = new StringTokenizer(in.readLine());
			for (int j = 0; j < 3; j++) {
				house[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		ans=INF;
		dp();
		out.write(ans + "");
		out.flush();
		out.close();
		in.close();
	}

	private static void dp() {
		for(int color=0; color<3; color++) {
			for(int i=0; i<3; i++) {
				if(i==color) dp[1][i]=house[1][i];
				else dp[1][i]=INF;
			}
			for(int i=2; i<=num; i++) {
				dp[i][0]=Math.min(dp[i-1][1], dp[i-1][2])+house[i][0];
				dp[i][1]=Math.min(dp[i-1][0], dp[i-1][2])+house[i][1];
				dp[i][2]=Math.min(dp[i-1][0], dp[i-1][1])+house[i][2];
			}
			for(int i=0; i<3; i++) if(i != color) ans=Math.min(ans, dp[num][i]);
		}
	}
}
