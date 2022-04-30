package com.ssafy.study.homework23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2512_예산 {

	static int N, M, num;
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		num = 0;
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		M = Integer.parseInt(br.readLine()); // 총 예산

		int low = 0; // 최소 예산
		int high = arr[N - 1]; //예산 상한액

		int mid, sum;

		while (low <= high) {
			mid = (low + high) / 2;
			sum = 0; // 세금의 합을 담을 변수

			for (int i = 0; i < N; i++) {
				if (arr[i] >= mid) sum += mid; //모든 요청이 배정 될 수 없는 경우 mid값만 배정.
				else sum += arr[i]; // 모든 요청이 배정될 수 있는 경우
			}

			if (sum > M) { //예산을 초과 할 경우
				high = mid - 1;
			} else { // 초과를 안했을경우 최고의 배정값을 찾아 나선다.
				low = mid + 1;
				num = Math.max(num, mid);
			}
		}
		System.out.println(num);
	}
}
