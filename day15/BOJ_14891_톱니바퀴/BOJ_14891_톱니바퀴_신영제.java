package com.ssafy.study.homework15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴 {
	static int gear[][];
	static int rotation[]; 
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = null;
		
		gear = new int[4][8];
		
		for(int i=0; i<4 ;i++) {
			String spliT = br.readLine();
			for(int j=0; j<8; j++) {
				gear[i][j] = spliT.charAt(j)-'0';
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		
		while(K > 0) {
			K--;			
			st = new StringTokenizer(br.readLine());
			int choiceGear = Integer.parseInt(st.nextToken())-1;
			int turn = Integer.parseInt(st.nextToken());
			
			rotation = new int[4]; 
			rotation[choiceGear] = turn; 
			checkDir(choiceGear);
			gearTurn();	
		}
		
		int ans =0;
		if(gear[0][0] == 1) ans+=1;
		if(gear[1][0] == 1) ans+=2;
		if(gear[2][0] == 1) ans+=4;
		if(gear[3][0] == 1) ans+=8;
		System.out.println(ans);
	}

	static void checkDir(int choice) {
	
		for(int i=choice+1; i<4; i++) {
			if(gear[i][6] != gear[i-1][2]) {
				rotation[i] = -rotation[i-1];			
			}else {  
				break;
			}
		}	
		
		for(int i=choice-1; i>=0; i--) {
			if(gear[i][2] != gear[i+1][6]) {
				rotation[i] = -rotation[i+1];
			}else {  
				break;
			}
		}
		
	}
	
	static void gearTurn() {
		int temp = 0;
		
		for(int i=0; i<4; i++) { 
			if(rotation[i] == 1) {
				temp = gear[i][7];
				for(int j=7; j>0; j--) {
					gear[i][j] = gear[i][j-1];
				}
				gear[i][0] = temp;
			}
			if(rotation[i] == -1) {
				temp = gear[i][0];
				for(int j=0; j<7; j++) {
					gear[i][j] = gear[i][j+1];
				}
				gear[i][7] = temp;
			}
		}
	}
}