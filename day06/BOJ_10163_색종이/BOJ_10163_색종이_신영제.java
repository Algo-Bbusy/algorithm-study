package com.ssafy.study.homework6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10163_색종이 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] arr = new int[101][101]; // 0 으로 설정된 칸
		int N = Integer.parseInt(br.readLine()); // 종이의 개수 입력.
		int[] count = new int[N]; // 종이의 개수만큼 배열을 만든다

		/*
		 * 앞에 두개는 시작점 0 0 뒤에 두개는 넓이와 높이 10 10 입력 받아야 된다. -> 근데 겹치는 부분이 있다면 새로운것들이 덮어야된다.
		 * N 만큼 덮친다
		 * 
		 */

		for (int num = 1; num <= N; num++) { // 종이 개수만큼 덮어야 하니깐 반복.
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int x_length = Integer.parseInt(st.nextToken());
			int y_length = Integer.parseInt(st.nextToken());

			for (int i = x; i < x + x_length; i++) {
				for (int j = y; j < y + y_length; j++) {
					arr[i][j] = num;
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] != 0) { // 종이의 개수마다 갯수 카운트
					count[arr[i][j] - 1]++;
				}
			}
		}

		// for(int i = 0; i <= count.length; i++) {
		// System.out.print(count[i]);
		// }
		for (int num_arr : count) {
			System.out.println(num_arr);
		}

	}

}
