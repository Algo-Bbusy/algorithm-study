package sh1n.bj2.ps11404플로이드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 플로이드 와샬 알고리즘 구현
 *	step 01. N x M 인접행렬을 생성하여 i == j인 경우를 제외한 모든 구간에 INF(플로이드 와샬 계산 과정에서 도달할 수 없는 최댓값)을 저장한다.
 *	step 02. [시작도시][도착도시]가 중복으로 존재할 수 있기 때문에 가장 작은 비용만을 갱신한다. 
 *	step 03. 경유지 i를 거쳐 j에서 k로 갈 수 있는 모든 경우에 접근하며 j에서 k로 가는 비용보다 j -> i -> k로 가는 비용이 더 적으면 갱신한다.
 *	step 04. 인접행렬을 출력한다. 이때 인접행렬의 값이 INF인 경우는 도달할 수 없는 도시이므로 0으로 출력한다.
 */

public class Main {
	
	public static final int INF = 100000 * 99 + 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] adjCity = new int[N + 1][N + 1];
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				adjCity[i][j] = INF;
			}
		}
		
		for(int i = 1; i <= M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if(cost < adjCity[from][to]) {
				adjCity[from][to] = cost;
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				for(int k = 1; k <= N; k++) {
					if(i == k || j == k) continue;
					
					if(adjCity[j][i] + adjCity[i][k] < adjCity[j][k]) {
						adjCity[j][k] = adjCity[j][i] + adjCity[i][k];
					}
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(adjCity[i][j] >= INF) {
					sb.append(0).append(" ");
				} else {
					sb.append(adjCity[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}// end main
}
