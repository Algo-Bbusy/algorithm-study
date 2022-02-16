package com.ssafy.study.member;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2477 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> welist = new ArrayList<>();
		ArrayList<Integer> nslist = new ArrayList<>();

		// 참외의 개수.
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String move = st.nextToken();
			String move_to = st.nextToken();
			
			for(int j = 1; j <= 4; j++) {
				if(move.charAt(0) - 48 == j) {					
					if(move.charAt(0) - 48 == 1 || move.charAt(0)-48 == 2) {
						nslist.add(Integer.parseInt(move_to));
					}
					if(move.charAt(0) -48 == 3 || move.charAt(0) - 48 == 4) {
						welist.add(Integer.parseInt(move_to));
					}
				} 
			}			
		}
		
		Collections.sort(welist);
		Collections.sort(nslist);
		
		int wemax = Collections.max(welist);
		int wemin = Collections.min(welist);
		
		int nsmax = Collections.max(nslist);
		int nsmin = Collections.min(nslist);
		
		int sum = ((wemax * nsmax) - (wemin * nsmin)) * N;

		System.out.println(sum);
	}

}