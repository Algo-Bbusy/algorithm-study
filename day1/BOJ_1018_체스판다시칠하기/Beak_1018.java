package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * 브루트 포스
 * 
 * 내생각. 
 * 1) 스터디조원분들이 대부분 월요일 전에 제출해서 놀랐다.
 * 2) 접근 방법 : 
 *  	1) 8*8 정사각형 체스판 W시작 B시작으로 만드려고 하였으니 만들어 놓으면 편하겠다!
 *      2) 배열을 생각하면 조금더 쉬울거 같긴하겠다..
 *      3) 백준에서 브루트포스 문제라고 하였으니 무식하게 한번해보고 스터디 하고나서 조금 더 논리적으로 알아봐야겠다. 
 * 
 * 
 */
public class Beak_1018 {
	
		static final char[][] chess_white = {
				{'W','B','W','B','W','B','W','B'},
				{'B','W','B','W','B','W','B','W'},
				{'W','B','W','B','W','B','W','B'},
				{'B','W','B','W','B','W','B','W'},
				{'W','B','W','B','W','B','W','B'},
				{'B','W','B','W','B','W','B','W'},
				{'W','B','W','B','W','B','W','B'},
				{'B','W','B','W','B','W','B','W'}
		};
		
		static final char[][] chess_black = {
				{'B','W','B','W','B','W','B','W'},
				{'W','B','W','B','W','B','W','B'},
				{'B','W','B','W','B','W','B','W'},
				{'W','B','W','B','W','B','W','B'},
				{'B','W','B','W','B','W','B','W'},
				{'W','B','W','B','W','B','W','B'},
				{'B','W','B','W','B','W','B','W'},
				{'W','B','W','B','W','B','W','B'}
		};
		
		public static void main(String[] args) throws IOException{
			// TODO Auto-generated method stub
			
			// 값을 입력 받기 위한 BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			StringTokenizer str = new StringTokenizer(br.readLine());			
			
			int N = Integer.parseInt(str.nextToken());
			int M = Integer.parseInt(str.nextToken());
			
			
			String[] chess = new String[N]; //체스판
			int min = 100;      			//최소값
			
			// 체스판 작성.
			for(int i = 0 ; i < N ; i++){
				chess[i] = br.readLine(); 
			}
			
			// 전체를 탐색.
			for(int i = 0 ; i < N - 7; i++) {
				for(int j = 0 ; j < M - 7; j++) {
					int cnt1 = 0; //chess1과 비교했을 때 바꿔야하는 횟수
					int cnt2 = 0; //chess2와 비교했을 때 바꿔야하는 횟수
					// 8*8 만큼 탐색.
					for(int k = j ;k < j + 8; k++) {
						for(int b = i;b < i + 8; b++) {
							if(chess[b].charAt(k) != chess_white[b-i][k-j]) cnt1++; 
							if(chess[b].charAt(k) != chess_black[b-i][k-j]) cnt2++; // 체스 배열과 다른거 갯수 카운트 	
						}
					}
					if(cnt1 < min || cnt2 < min) { // 비교. 
						min = Math.min(cnt1, cnt2); 
					}
				}
			}
			System.out.println(min);	
		}

	}
