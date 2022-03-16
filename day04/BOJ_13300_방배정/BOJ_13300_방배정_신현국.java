package sh1n.bj.ps13300방배정;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int GRADE = 6;
	static final int GENDER = 2;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps13300방배정/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] students = new int[GRADE][GENDER];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			students[grade-1][gender]++;
		}
		
		int roomCnt = 0;
		for(int i=0; i<GRADE; i++) {
			for(int j=0; j<GENDER; j++) {
				if(students[i][j] != 0) {
					roomCnt += (students[i][j] / K);
					if(students[i][j] % K != 0) {
						roomCnt++;
					}
				}
			}
		}
		
		System.out.println(roomCnt);
	}// end main()
}
