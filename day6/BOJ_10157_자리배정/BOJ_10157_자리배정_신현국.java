package sh1n.bj.ps10157자리배정;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps10157자리배정/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		int[][] map = new int[R][C];
		
		if(K > R*C) {
			System.out.println(0);
			System.exit(0);
		}
		
		int cnt = 1;
		int r = 0;
		int c = 0;
		int dir = 0;
		map[r][c] = cnt;
		while(cnt != K) {
			if(r+dr[dir]<0 || r+dr[dir]>=R || c+dc[dir]<0 || c+dc[dir]>=C || map[r+dr[dir]][c+dc[dir]] != 0) {
				dir = ++dir%4;
				continue;
			}
			r += dr[dir];
			c += dc[dir];
			map[r][c] = ++cnt;
		}
		
		System.out.println((r+1) + " " + (c+1));
	} // end main()
}
