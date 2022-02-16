package com.ssafy.study.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_14696 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 라운드 수
		int N = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= N; tc++) {
			st = new StringTokenizer(br.readLine());
			// 딱지의 개수 입력
			int A = Integer.parseInt(st.nextToken());
			int[] A_arr = new int[A];
			for (int i = 0; i < A; i++) {
				A_arr[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			int B = Integer.parseInt(st.nextToken());
			int[] B_arr = new int[B];
			for (int i = 0; i < B; i++) {
				B_arr[i] = Integer.parseInt(st.nextToken());
			}

			// 4-별, 3-동그라미, 2-네모, 1-세모
			for (int i = 4; i > 0; i--) {
				// 숫자 갯수 담을 공간
				int Acnt = 0;
				int Bcnt = 0;
				
				for (int a = 0; a < A; a++) {
					if (A_arr[a] == i) { // A가 원소가 4이면 3이면 2이면 1이면....
						Acnt++;
					}
				}

				for (int b = 0; b < B; b++) {
					if (B_arr[b] == i) { // A가 원소가 4이면 3이면 2이면 1이면....
						Bcnt++;
					}
				}
				
				if( Acnt == Bcnt) {
					if(i == 1) {
						System.out.println("D");
					} else if( i > 1) {
						continue;
					}
				}

				if (Acnt > Bcnt) { // 4를 기준으로 1 > 0
					System.out.println("A");
					break;
				} else if(Acnt < Bcnt) { // Acnt < Bcnt
					System.out.println("B");
					break;
				}				
			}
		}
	}
}
