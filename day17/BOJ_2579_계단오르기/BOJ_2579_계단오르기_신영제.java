package com.ssafy.study.homework17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579_계단오르기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		int N = Integer.parseInt(br.readLine());
 
		int[] arr = new int[N + 1]; //배열 생성
		int[] D = new int[N + 1];   //동적테이블 생성
		
 
		//값 입력.
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine()); 
		}
		
		D[0] = 0;
		D[1] = arr[1];
		
		if(N >= 2) { // if(N>=2) 해준 이유 : N이 3이하일 경우 출력 결과 ArrayIndexOutOf~~에러가 뜬다. 
			D[2] = arr[1]+arr[2];			
		}
		
		for (int i = 3; i <= N; i++) {	
			D[i] = Math.max(D[i - 2] , D[i - 3] + arr[i - 1]) + arr[i];
		}
 
		System.out.println(D[N]);
		
	}

}
