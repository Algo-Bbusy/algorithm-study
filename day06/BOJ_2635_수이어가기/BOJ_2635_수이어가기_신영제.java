package com.ssafy.study.homework6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2635_수이어가기 {
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Integer> max_list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());

		int max = Integer.MIN_VALUE;

		for (int i = 1; i < N + 1; i++) {

			list.add(N);
			list.add(i);

			int idx = 2;

			while (true) {
				int sum = list.get(idx - 2) - list.get(idx - 1); //0이하가 나오기전까지 값을 구해준다.
				idx++;
				if (sum < 0)
					break;
				list.add(sum); // 값을 구할때마다 리스트 뒤에 추가
			}

			// max = Math.max(max, list.size());
			if (max < list.size()) {
				max_list.clear(); // 그 전값들을 초기화
				max = list.size(); // 크기 불러오기
				max_list = new ArrayList<>(list); // 출력할 리스트 넣기
			}
			list.clear(); // 한바퀴 돌았으면 초기화
		}

		System.out.println(max);
		for (int a : max_list) {
			System.out.print(a + " ");
		}

	}
}
