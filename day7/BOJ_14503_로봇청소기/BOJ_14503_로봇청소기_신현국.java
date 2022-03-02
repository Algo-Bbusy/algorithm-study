package sh1n.bj.ps14503로봇청소기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps14503로봇청소기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken()); 
		int C = Integer.parseInt(st.nextToken()); 
		
		st = new StringTokenizer(br.readLine());
		int[] robotInfo = new int[3];	// 0: R좌표, 1: C좌표, 2: 방향정보
		for(int i=0; i<3; i++) {
			robotInfo[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] map = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(cleanMap(robotInfo, map));
	} // end main()
	
	public static int cleanMap(int[] robotInfo, int[][] map) {
		int robotR = robotInfo[0];
		int robotC = robotInfo[1];
		int robotDir = robotInfo[2];
		map[robotR][robotC] = 2;
		
		int robotNR, robotNC, searchCnt = 0;
		while(++searchCnt < 5) {
			robotDir = (robotDir + 3) % 4;
			robotNR = robotR + dr[robotDir];
			robotNC = robotC + dc[robotDir];
			
			if(map[robotNR][robotNC] == 0) {
				return 1 + cleanMap(new int[] {robotNR, robotNC, robotDir}, map);
			}
		}
		
		robotNR = robotR - dr[robotDir];
		robotNC = robotC - dc[robotDir];
		if(map[robotNR][robotNC] == 2) {
			return 0 + cleanMap(new int[] {robotNR, robotNC, robotDir}, map);
		} else {
			return 1;
		}
	}
}
