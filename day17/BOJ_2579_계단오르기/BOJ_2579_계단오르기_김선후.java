package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_2579 {
	// 계단 오르기
	//1. 한칸씩 연속으로 오를수없다.
	//2. n번째 계단의 값은 두가지의 경우로 나뉜다.
	//2-1. n번째 계단의 점수+n-1번째 계단의 점수+n-3까지의 점수 (2->1->n)로 뛰는경우
	//2-2. n번째 계단의 점수+n-2까지의 점수	(2->n)로 뛰는 경우
	//두 경우에서 더 큰값으로 n번째까지의 누적점수를 갱신해준다.
	//!!!. 반례 N이 2 미만인 경우가 존재하므로 세번째 누적점수를 초기화하기 위한 조건식이 꼭 들어가야한다. 여기서 10분넘게 날렸다 ㅠ
	//Solution 1.
	//1. n번째까지의 누적점수와 현재 도착한 계단의 점수를 담을 수 있는 2차원 배열 dp[N][2]를 선언한다. [0] 계단점수 [1] 누적점수
	//2. 1번째 2번째 3번째의 dp값을 초기화하고 배열인덱스 4부터 반복문을 실행한다.
	//3. n번째 계단까지 도달하는 두가지의 경우의 수 중 더 큰값으로 해당 n번째계단까지의 누적점수를 갱신한다.
	//4. 반복문이 n번까지 돌고 나온후의 n번째 dp배열의 누적점수값을 정답값으로 출력한다.
	//메모리 14296KB 시간 140ms
	//풀이시간 30분
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb= new StringBuilder();
	static int N;
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		dp = new int[N+1][2];
		for(int i=0; i<N; i++) dp[i][0]=Integer.parseInt(in.readLine());
		dp[0][1]=dp[0][0];			//1번째
		dp[1][1]=dp[0][0]+dp[1][0];	//2번째
		if(N>=2) dp[2][1]=Math.max(dp[0][0], dp[1][0])+dp[2][0];	//3번째
		for(int n=3; n<=N; n++) dp[n][1]=Math.max(dp[n-3][1]+dp[n-1][0], dp[n-2][1])+dp[n][0];
		out.write(dp[N-1][1]+"");
		out.flush();
		out.close();
		in.close();
	}

}
