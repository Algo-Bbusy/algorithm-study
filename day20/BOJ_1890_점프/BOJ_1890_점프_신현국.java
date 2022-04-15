package sh1n.bj2.ps1890점프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static long[][][] jumpMap;
	public static final int JUMPSIZE = 0;
	public static final int ISVISITED = 1;
	public static final int CASE = 2;
	
	
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		jumpMap = new long[N][N][3];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				jumpMap[i][j][JUMPSIZE] = Integer.parseInt(st.nextToken());
			}
		}
		
		long result = jump(0, 0);
		
		System.out.println(result);
		
	}// end main

	private static long jump(int r, int c) {
		if(r == N - 1 && c == N - 1) {
			return 1;
		}
		
		for(int i = 0; i < 2; i++) {
			int nr = r + dr[i] * (int) jumpMap[r][c][JUMPSIZE];
			int nc = c + dc[i] * (int) jumpMap[r][c][JUMPSIZE];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if(jumpMap[nr][nc][ISVISITED] == 0 || (nr == N - 1 && nc == N - 1)) {
				jumpMap[nr][nc][ISVISITED] = 1;
				jumpMap[r][c][CASE] += jump(nr, nc);
			} else {
				jumpMap[r][c][CASE] += jumpMap[nr][nc][CASE];
			}
				
		}
		
		return jumpMap[r][c][CASE];
	}
}
