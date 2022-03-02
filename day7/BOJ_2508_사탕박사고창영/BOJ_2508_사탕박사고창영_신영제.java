package com.ssafy.study.homework7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2508_사탕박사고창영 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine()); // 테스트케이스.

		for (int tc = 1; tc <= T; tc++) {

			//System.out.print("\n");
			br.readLine();
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()); // 행
			int c = Integer.parseInt(st.nextToken()); // 열

			char[][] arr = new char[r][c];
			for (int i = 0; i < r; i++) {
				String a = br.readLine();
				for (int j = 0; j < c; j++) {
					arr[i][j] = a.charAt(j);
					// System.out.print(arr[i][j] + " ");
				}
			}

			int cnt = 0;

			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (i < r-2 && arr[i][j] == 'v' && arr[i+1][j] == 'o' && arr[i+2][j] == '^') cnt++; 
					if (j < c-2 && arr[i][j] == '>' && arr[i][j+1] == 'o' && arr[i][j+2] == '<') cnt++;					
				}
			}
			System.out.println(cnt);
		}

	}

}
