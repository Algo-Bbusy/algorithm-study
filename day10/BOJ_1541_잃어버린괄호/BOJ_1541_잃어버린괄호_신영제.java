package com.ssafy.study.homework9_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1541_잃어버린괄호 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int min = Integer.MAX_VALUE; 
		StringTokenizer st_minus = new StringTokenizer(br.readLine(), "-");
				

		while (st_minus.hasMoreTokens()) {
			int temp = 0;

			
			StringTokenizer st_plus = new StringTokenizer(st_minus.nextToken(), "+");

			
			while (st_plus.hasMoreTokens()) {
				temp += Integer.parseInt(st_plus.nextToken());
			}

			
			if (min == Integer.MAX_VALUE) {
				min = temp;
			} else {
				min -= temp;
			}
		}
		System.out.println(min);
	}

}
