package com.ssafy.study.homework16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*

1) 
0: x좌표가 증가하는 방향 (→) : 우
1: y좌표가 감소하는 방향 (↑) : 상
2: x좌표가 감소하는 방향 (←) : 좌
3: y좌표가 증가하는 방향 (↓) : 화

규칙 : 전 세대에서 그린 방향에서 역순으로 진행하면서 반시계 방향으로 돌린다.



 */

public class BOJ_15685_드래곤커브 {
	
	static int N;
	static int[][] arr;
	static int[] di = {1, 0, -1, 0};
	static int[] dj = {0, -1, 0, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        arr = new int[101][101];
        
        for(int i =0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	 int x = Integer.parseInt(st.nextToken());
             int y = Integer.parseInt(st.nextToken());
             int d = Integer.parseInt(st.nextToken());
             int g = Integer.parseInt(st.nextToken());
             
             ArrayList<Integer> list =new ArrayList<Integer>(); // 방향을 담을 리스트를 만듬.
             
             list.add(d);
             
             for(int j=0; j<g; j++) { //세대 만큼 반복을 돌린다.
            	 // 이전 세대에서 최근에 그린 방향에 대한 반시계 방향으로 돌린다.
            	 for(int k = list.size()-1; k >= 0; k--) {
            		 list.add((list.get(k)+1)%4);
            	 }
             }
        }
        

	}

}