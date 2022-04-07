package com.ssafy.study.homework17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 상담과 금액 배열을 생성
2. 퇴소 날짜가 되기 전 모든 합을 만들어내야함. -> DFS
3. 해당 범위에서 현재 상담의 기간과 금액을 더해 다음 날짜로 넘어간다.
4. 위 재귀 연산이 끝나면 다른 경우의 수를 위해 다음 날짜로 넘어간다.
5. 3,4번을 반복한다.
6. 퇴사날짜가 되면 최고의 금액을 출력한다.
 */
public class BOJ_14501_퇴사 {

	static int N;
	static int result;
	static int[] talk;
	static int[] pay;

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		talk = new int[N + 1];
		pay = new int[N + 1];
		result = 0;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			talk[i] = Integer.parseInt(st.nextToken()); // 날짜 입력
			pay[i] = Integer.parseInt(st.nextToken()); // pay 입력
		}

		dfs(1, 0);

		System.out.println(result);
	}

	private static void dfs(int x, int y) {
		if (x == N + 1) { // 퇴사날짜가 되면 종합금액 을 넣고 return
			result = Math.max(result, y);
			return;
		}

		for (int i = x; i <= N; i++) {
			dfs(i + talk[i], y + pay[i]); // 현재 상담기간, 금액을 더하여 해당 날짜로 이동.
			dfs(i + 1, y); // 다른 경우의 수를 위해 다음 날짜로 이동.
		}
	}
}