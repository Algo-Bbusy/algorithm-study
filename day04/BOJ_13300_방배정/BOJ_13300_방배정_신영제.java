package com.ssafy.study.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13300 {// 방배정

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 학생수
		int K = Integer.parseInt(st.nextToken()); // 방배정

		int[][] room = new int[2][7]; // 7 = 0제외하고 하는게 편함
		int cnt = 0;
		int s; // 성별
		int y; // 학년

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			room[s][y]++; // 성별별, 학년별로 나누어 해당 인덱스에 ++하면 쉽게 구분해놀수 있다.
		}

//		if (0 < room[i][j] && room[i][j] <= K)
//			cnt++;
		for (int i = 0; i < 2; i++) { // 성별 여자 먼저 삭 돌고 남자 돌고.
			for (int j = 1; j < 7; j++) { // 여자먼저 1~6학년까지 남자 1~6학년까지 탐방
				if(room[i][j] % K !=0) { //0명이 아니면 방을 하나 올려라.
					cnt++;
				}
				cnt = cnt + (room[i][j] / K); //딱 맞아 떨어지는애들 추가해줘라
				
			}
		}
		System.out.println(cnt);

	}

}

/*
 * 조건 : 한방에 한명만 배정 가능. 한방에 배정할 수 있는 최대 인원 수 K 한방에 같은 학년의 학생들을 배정해야 함.
 * 
 * N : 학생수 (만약 3이라면) K : 최대 인원 수 * 1) S : 성별 (여자 : 0, 남자 : 1) Y : 학년 2) 3)
 * arr[N][2] arr[i][0] = Integr.parser(st.nextToken()); 성별 담기 arr[i][1] =
 * Integr.parser(st.nextToken()); 학년 담기
 * 
 * 
 * 
 * if(arr[i][0] == "1" || arr[i][0] == "0" && arr[i][1] == "i+1"){ arr[i][0]
 * 
 * // 남자 if(arr[i][0] == "1" && arr[i][1] == "i+1"){ cnt++;
 * 
 * // 여자 if(arr[i][0] == "0" && arr[i][1] == "i+1"){
 * 
 * 
 * 0이면 x 0 < A <= K 이면 +1 K <= A 중 짝수면 A/K 홀수면 A/K +1
 * 
 * 
 * 
 */

/*
		int one_m = 0, one_g = 0, two_m = 0, two_g = 0, thr_m = 0, thr_g = 0, fou_m = 0, fou_g = 0, fiv_m = 0,
				fiv_g = 0, six_m = 0, six_g = 0;

		int[][] arr = new int[N][2]; // 학생 수 만큼의 성별과 학년 저장.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 1; j <= 6; j++) {
				if (arr[i][1] == j) {
					if (j == 1) {
						if (arr[i][0] == 1) {
							one_m++;
						} else if (arr[i][0] == 0) {
							one_g++;
						}
					}
					if (j == 2) {
						if (arr[i][0] == 1) {
							two_m++;
						} else if (arr[i][0] == 0) {
							two_g++;
						}
					}
					if (j == 3) {
						if (arr[i][0] == 1) {
							thr_m++;
						} else if (arr[i][0] == 0) {
							thr_g++;
						}
					}
					if (j == 4) {
						if (arr[i][0] == 1) {
							fou_m++;
						} else if (arr[i][0] == 0) {
							fou_g++;
						}
					}
					if (j == 5) {
						if (arr[i][0] == 1) {
							fiv_m++;
						} else if (arr[i][0] == 0) {
							fiv_g++;
						}
					}
					if (j == 6) {
						if (arr[i][0] == 1) {
							six_m++;
						} else if (arr[i][0] == 0) {
							six_g++;
						}
					}
				}
			}
		} */