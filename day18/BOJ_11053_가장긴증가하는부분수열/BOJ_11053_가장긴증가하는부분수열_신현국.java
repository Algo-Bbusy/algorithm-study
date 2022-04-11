package sh1n.bj2.ps11053가장긴증가하는부분수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Case2. (Case1.)과 동일한 맥락으로 접근하지만 dp 테이블을 관리할 때 바이너리 서치 적용 
 * step 01. 인덱스 크기가 LIS를 의미하는 dp 테이블 생성 
 * 		* dp안에 저장되는 값은 i 번째 수열이 끝 값으로 오는 최대길이 인덱스가 유지되는 값이 들어감(마지막으로 들어온 수가 저장되어 있음)
 * 		* 즉, 새로운 수가 나올 때마다 dp 테이블의 값은 변하기 때문에 큰 의미 X => 3 5 2 6 이라는 수열이 있을 때 3을 끝 값으로 갖는 LIS나 2를 끝 값으로 갖는 LIS나 동일한점
 * step 02. LIS 인덱스를 관리할 포인터 변수 생성 (0으로 시작)
 * step 03. i 번째 수가 dp 테이블 값에 들어갈 위치를 바이너리 서치로 탐색
 * 		03-1. i가 dp 테이블 값들 중 가장 큰 경우는 +1 증가한 LIS가 생성됬단 의미 => 포인터++
 * 		03-2. 위 경우가 아니면 dp[j] < i < dp[j+1]를 만족할 때 dp[j] = i로 설정
 * step 04. 모든 수를 처리했을 때 포인터가 LIS 값이므로 포인터 출력
 */

public class Main_DP_improvement {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N]; 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N];
		int pointer = 0;
		
		for(int i = 0; i < N; i ++) {
			int start = 0;
			int end = pointer;
			while(start < end) {
				int target = (start + end) / 2;
				
				if(dp[target] < numbers[i]) {
					start = target + 1;
				} else {
					end = target;
				}
			}
			
			dp[start] = numbers[i];
			if(start == pointer) {
				pointer++;
			}
		}
		
		System.out.println(pointer);
	}// end main
}
