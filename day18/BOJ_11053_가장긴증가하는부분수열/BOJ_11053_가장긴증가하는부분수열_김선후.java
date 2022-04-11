package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_11053 {
	// 가장 긴 증가하는 부분 수열
	// 문제를 읽자마자 제목부터 LIS 알고리즘을 사용하라는 문제라는 것을 알았습니다.
	// 일정한 숫자들이 주어질 때 첫 번째 숫자부터 점점 커지는 숫자들을 선택할 때 가질 수 있는 최대의 개수를 구한다.
	// LIS 알고리즘을 사용하여 입력받은 수열 중 최장수열값을 구한다.
	// ans에 최대값을 받아서 출력한다.
	// 메모리 14556KB 시간 148ms
	// 풀이시간 8분 14초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N, ans;
	static int[] dp, arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		dp = new int[N+1];
		arr = new int[N+1];
		
		stk = new StringTokenizer(in.readLine());
		for(int i=1; i<=N; i++) arr[i]=Integer.parseInt(stk.nextToken());
		
		dp[1]=1; //초기 시작점 길이 1로 초기화
		for(int i=2; i<=N; i++) {
			dp[i]=1;
			for(int j=1; j<i; j++) {
				if(arr[i]>arr[j]) {
					dp[i]=Math.max(dp[j]+1, dp[i]);
				}
			}
		}
		ans = Integer.MIN_VALUE;
		for(int i=1; i<=N; i++) {
			if(dp[i]>ans) ans=dp[i];
		}
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}

}
