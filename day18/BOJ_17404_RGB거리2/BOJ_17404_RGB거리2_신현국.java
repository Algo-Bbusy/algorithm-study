package sh1n.bj2.ps17404RGB거리2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**	Case 01. 기존 RGB 거리 문제와 동일하게 접근하면서 1번집과 N번 집의 색이 달라야 되는 조건 추가
 *	step 01. 1번 집을 특정 색으로 칠하는 경우로 고정 (다른 2개 색 최대값 + 1로 설정하여 선택되지 않도록함)
 *  step 02. 이 후 i(i>=2) 번째 집부터는 해당 색을 선택하기 위한 최적해를 구하며 N 번째 집까지 dp 테이블을 업데이트
 *	step 03. 1번 집에서 색칠한 색을 제외한 다른 2개의 색중 최솟값을 minCost에 저장
 *	step 04. 다시 (step 01.) 과정부터 다른 색으로 칠하는 경우를 고정하고 반복
 *	step 05. 3가지 경우를 모두 한 경우 minCost에 저장된 값이 정답
 */

public class Main {
	
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N + 1][3];
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dp => [i][0]: Red min cost, [1][1]: Green min cost, [1][2]: Blue min cost
		int[][] dp = new int[N + 1][3];
		int[] minCost = new int[3];
		Arrays.fill(minCost, Integer.MAX_VALUE);
		for(int fixRGB = 0; fixRGB < 3; fixRGB++) {
			for(int i = 0; i < 3; i++) {
				dp[1][i] = 1000 + 1;
			}
			dp[1][fixRGB] = cost[1][fixRGB];
			
			for(int i = 2; i <= N; i++) {
				dp[i][RED] = Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]) + cost[i][RED];
				dp[i][GREEN] = Math.min(dp[i - 1][RED], dp[i - 1][BLUE]) + cost[i][GREEN];
				dp[i][BLUE] = Math.min(dp[i - 1][RED], dp[i - 1][GREEN]) + cost[i][BLUE];
			}
			
			minCost[fixRGB] = Math.min(dp[N][(fixRGB + 1) % 3], dp[N][(fixRGB + 2) % 3]);
		}
		
		int result = 1000 * 1000 + 1;
		for(int i = 0; i < 3; i++) {
			if(result > minCost[i]) result = minCost[i];
		}
		
		System.out.println(result);
	}// end main
}
