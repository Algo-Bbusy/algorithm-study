package sh1n.bj.ps10163색종이;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("./src/sh1n/bj/ps10163색종이/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int number = 1;
		int[][] map = new int[1001][1001];
		for(int tc=0; tc<N; tc++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken()) + x1;
			int y2 = Integer.parseInt(st.nextToken()) + y1;
			for(int i=y1; i<y2; i++) {
				for(int j=x1; j<x2; j++) {
					map[i][j] = number;
				}
			}
			number++;
		}
		
		int[] area = new int[number];
		for(int i=0; i<=1000; i++) {
			for(int j=0; j<=1000; j++) {
				area[map[i][j]]++;
			}
		}
		
		for(int i=1; i<number; i++) {
			System.out.println(area[i]);
		}
	}
}
