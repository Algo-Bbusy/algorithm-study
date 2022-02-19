package com.ssafy.study.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1592 { // 영식이와 친구들

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 사람 수
		int M = Integer.parseInt(st.nextToken()); // 벗어날 횟수
		int L = Integer.parseInt(st.nextToken()); // +,- 할 값

		int[] arr = new int[N + 1]; // 0부터 시작하니깐 복잡함 0 ~ 6까지 만들어준다.0은 무시하듯
		int idx = 1; // 인덱스를 가지고 행동할거기 때문에 인덱스 에 사용할거 선언.

		arr[1] = 1; // 1번째 애가 1로 M을 시작 신호탄

		int cnt = 0;
		while (true) {
			if(arr[1] == M) break;
			
			if (arr[idx] % 2 == 1) { // arr[idx]번째 M이 홀수라면.
				idx = idx + L; // L만큼 시계방향 이통 해야하므로.
				if (idx > N) { // 그런데 idx가 N의 범위를 초과된다면?
					idx = idx % N;
				}
				arr[idx]++; // arr[idx]값에 +1을 해준다.
				cnt++;
				if (arr[idx] == M)
					break;
			} else if (arr[idx] % 2 == 0) { // 홀수가 아니라 N이 짝수라면
				idx = idx - L; // L만큼 반시계 방향 이동.
				if (idx < 1) {
					idx = idx + N;
				}
				arr[idx]++;
				cnt++;
				if (arr[idx] == M)
					break;
			}

		}
		
		System.out.println(cnt);
	}

}
