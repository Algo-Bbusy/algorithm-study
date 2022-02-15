package sh1n.bj.ps2669직사각형면적;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2669직사각형면적/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[][] map = new boolean[101][101];
		for(int tc=0; tc<4; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());  
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());  
			
			for(int i=y1; i<y2; i++) {
				for(int j=x1; j<x2; j++) {
					map[i][j] = true;
				}
			}
		}
		
		int sum = 0;
		for(int i=1; i<=100; i++) {
			for(int j=1; j<=100; j++) {
				if(map[i][j]) {
					sum++;
				}
			}
		}
		
		System.out.println(sum);
	}
}
