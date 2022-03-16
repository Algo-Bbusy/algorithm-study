package com.ssafy.study.homework5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2527_직사각형 { // 직사각형

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 4; tc++) {
			int[] arr = new int[8];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			if (arr[2] < arr[4] || arr[6] < arr[0] || arr[3] < arr[5] || arr[7] < arr[1]) {
				System.out.println("d");
			} else if ((arr[2] == arr[4] && arr[3] == arr[5]) || (arr[0] == arr[6] && arr[3] == arr[5])
					|| (arr[2] == arr[4] && arr[1] == arr[7]) || (arr[0] == arr[6] && arr[1] == arr[7])) {
				System.out.println("c");
			} else if ((arr[6] == arr[0] && arr[7] > arr[1] && arr[3] > arr[5])
					|| (arr[7] == arr[1] && arr[2] > arr[4] && arr[6] > arr[0])
					|| (arr[4] == arr[2] && arr[7] > arr[1] && arr[3] > arr[5])
					|| (arr[5] == arr[3] && arr[2] > arr[4] && arr[6] > arr[0])) {
				System.out.println("b");
			} else {
				System.out.println("a");
			}
		}
	}

}
