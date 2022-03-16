package sh1n.bj.ps4963섬의개수;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 0, 1, 0, 1, 1, -1, -1};
	static int[] dc = {0, 1, 0, -1, 1, -1, 1, -1};
	public static final int LAND = 1;
	public static final int SEA = 0;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps4963섬의개수/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String in;
		while((in = br.readLine()) != null) {
			st = new StringTokenizer(in);
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			if(W == 0 && H == 0) {
				System.exit(0);
			}
			
			boolean[][] map = new boolean[H][W];
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					int el = Integer.parseInt(st.nextToken());
					if(el == LAND) {
						map[i][j] = true;
					}
				}
			}
			
			int landCnt = 0;
			for(int r=0; r<H; r++) {
				for(int c=0; c<W; c++) {
					if(map[r][c]) {
						map[r][c] = false;
						searchLand(map, r, c);
						landCnt++;
					}
				}
			}
			
			System.out.println(landCnt);
		}
		
	}// end main
	
	public static void searchLand(boolean[][] map, int r, int c) {
		int nr, nc;
		for(int i=0; i<8; i++) {
			try {
				nr = r + dr[i];
				nc = c + dc[i];
				
				if(map[nr][nc]) {
					map[nr][nc] = false;
					searchLand(map, nr, nc);
				}
			} catch(ArrayIndexOutOfBoundsException e) {};
		}
	}
}
