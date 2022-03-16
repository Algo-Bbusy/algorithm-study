package com.ssafy.study.homework5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_2628_종이자르기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		int W = Integer.parseInt(st.nextToken()); //가로
		int H = Integer.parseInt(st.nextToken()); //세로

		int N = Integer.parseInt(in.readLine()); // 분할할 갯수

		LinkedList<Integer> list_x = new LinkedList<Integer>();
		LinkedList<Integer> list_y = new LinkedList<Integer>();

		list_x.add(0); // 시작점 
		list_y.add(0); // 시작점 
		list_x.add(H); // 끝점
		list_y.add(W); // 끝점
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int check = Integer.parseInt(st.nextToken()); // 가로인지 세로인지
			int number = Integer.parseInt(st.nextToken()); // 범위 

			if (check == 0) {
				list_x.add(number);
			}
			if (check == 1) {
				list_y.add(number);
			}
		}

		//다 들어갔으면 정렬.
		Collections.sort(list_x); 
		Collections.sort(list_y);

		int max_x = 0;		
		for( int i = 0; i < list_x.size()-1; i++) { // 자른 크기 값 구하기.
			int temp = list_x.get(i+1) - list_x.get(i);
			if (max_x < temp) {
				max_x = temp;
			}
		} 

		int max_y = 0;
		for( int i = 0; i < list_y.size()-1; i++) { // 자른 크기 값 구하기.
			int temp = list_y.get(i+1) - list_y.get(i);
			if (max_y < temp) {
				max_y = temp;
			}
		}

		System.out.println(max_x * max_y);
	}
}