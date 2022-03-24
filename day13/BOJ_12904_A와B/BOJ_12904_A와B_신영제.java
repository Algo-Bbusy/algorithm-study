package com.ssafy.study.homework13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * (1) 그리디 알고리즘
 * (2) T를 기준으로 제거해 간다.
 */

public class BOJ_12904_A와B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		
		int Tlen = T.length();
		
		for(int i = 0; i < Tlen; i++) {
			if (T.charAt(T.length() - 1) == 'A') {
				T = T.substring(0, T.length() - 1);
                        } else {
				T = T.substring(0, T.length() - 1);
				StringBuffer sb = new StringBuffer(T);
				T = sb.reverse().toString();
			}			
			if (S.length() == T.length()) {
				if (S.equals(T)) System.out.println(1);
				else System.out.println(0);
			}
		}

	}
}
