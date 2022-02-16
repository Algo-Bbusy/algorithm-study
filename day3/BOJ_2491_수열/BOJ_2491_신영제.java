package com.ssafy.study.member;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2491 { //수열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		// System.out.println(Arrays.toString(arr));

		int over_max = 0;
		int down_max = 0;
		
		int over_cnt = 1;
		int down_cnt = 1;
		
		for(int i = 0; i < N-1; i++) { 
			if(arr[i] <= arr[i+1]) { // 1번째가 0번째보다 크거나 작다면 카운트 +1  2번째가 1번째보다 크거나 작다면 +1 +1-----
				over_cnt++;
			} else over_cnt = 1; // 그렇지 않다면 카운트는 1 -> 어차피 나머지는 over_max에 저장되어있음.
			if(over_max < over_cnt) over_max = over_cnt;

			if(arr[i] >= arr[i+1]) {
				down_cnt++;
			} else down_cnt = 1;
			if(down_max < down_cnt) down_max = down_cnt;
		}		
		if(over_max > down_max) System.out.println(over_max);
		else System.out.println(down_max);
		
	}

}
