package sh1n.bj.ps3187양치기꿍;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int wolfCnt, sheepCnt;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps3187양치기꿍/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		List<int[]> wolfPos = new ArrayList<int[]>();
		for(int i=0; i<R; i++) {
			String els = br.readLine();
			for(int j=0; j<C; j++) {
				char el = els.charAt(j);
				map[i][j] = el;
				if(el == 'v') {
					wolfPos.add(new int[] {i, j});
					wolfCnt++;
				} else if(el == 'k') {
					sheepCnt++;
				}
			}
		}
		
		for(int i=0; i<wolfPos.size(); i++) {
			int wolfR = wolfPos.get(i)[0];
			int wolfC = wolfPos.get(i)[1];
			kill(map, wolfR, wolfC);
		}
		
		System.out.println(sheepCnt + " " + wolfCnt);
	}// end main
	
	public static void kill(char[][] map, int wolfR, int wolfC) {
		int wCnt = 1;
		int sCnt = 0;
		int nr, nc;
		Queue<int[]> bfsQ = new LinkedList<>();
		bfsQ.offer(new int[] {wolfR, wolfC});
		map[wolfR][wolfC] = '#';
		
		while(!bfsQ.isEmpty()) {
			int[] curWolfPos = bfsQ.poll();
			
			for(int i=0; i<4; i++) {
				nr = curWolfPos[0] + dr[i];
				nc = curWolfPos[1] + dc[i];
				
				if(map[nr][nc] == '#') {
					continue;
				} else if(map[nr][nc] == 'v') {
					wCnt++;
				} else if(map[nr][nc] == 'k') {
					sCnt++;
				}
				map[nr][nc] = '#';
				bfsQ.offer(new int[] {nr, nc});
			}
		}
		
		if(wCnt >= sCnt) {
			sheepCnt -= sCnt;
		} else {
			wolfCnt -= wCnt;
		}
	}
}
