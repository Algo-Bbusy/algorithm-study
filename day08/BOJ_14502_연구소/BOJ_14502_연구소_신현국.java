package sh1n.bj.ps14502연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("./src/sh1n/bj/ps14503로봇청소기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[] isWall = new boolean[N*M];
		
		List<int[]> virusPos = new ArrayList<>();
		int wallCnt = 3;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int el = Integer.parseInt(st.nextToken());
				map[i][j] = el;
				if(el == 0) {
					isWall[M*i + j] = true;
				} else if(el == 1) {
					wallCnt++;
				} else if(el == 2) {
					virusPos.add(new int[] {i, j});
				}
			}
		}

		int maxSafeZoneCnt = 0;
		for(int i=0; i<isWall.length-2; i++) {
			if(isWall[i]) {
				map[i/M][i%M] = 1;
			} else {
				continue;
			}
			for(int j=i+1; j<isWall.length-1; j++) {
				if(isWall[j]) {
					map[j/M][j%M] = 1;
				} else {
					continue;
				}
				for(int k=j+1; k<isWall.length; k++) {
					if(isWall[k]) {
						map[k/M][k%M] = 1;
						maxSafeZoneCnt = searchSafeZone(map, maxSafeZoneCnt, virusPos, wallCnt);
					} else {
						continue;
					}
					map[k/M][k%M] = 0;
				}
				map[j/M][j%M] = 0;
			}
			map[i/M][i%M] = 0;
		}
		
		System.out.println(maxSafeZoneCnt);
	} // end main()
	
	public static int searchSafeZone(int[][] map, int maxSafeZoneCnt, List<int[]> virusPos, int wallCnt) {
		int R = map.length;
		int C = map[0].length;
		int safeZoneCnt = R * C - wallCnt;
		boolean[][] visited = new boolean[map.length][map[0].length];
		Queue<int[]> bfsQ = new LinkedList<int[]>();
		int nr, nc;
		
		for(int i=0; i<virusPos.size(); i++) {
			bfsQ.offer(virusPos.get(i));
			visited[virusPos.get(i)[0]][virusPos.get(i)[1]] = true;
			safeZoneCnt--;
		}
		
		while(!bfsQ.isEmpty()) {
			int[] curVirusPos = bfsQ.poll();
			
			for(int j=0; j<4; j++) {
				nr = curVirusPos[0] + dr[j];
				nc = curVirusPos[1] + dc[j];
				
				if(nr>=0 && nr<map.length && nc>=0 && nc<map[0].length && !visited[nr][nc] && map[nr][nc] == 0) {
					visited[nr][nc] = true;
					safeZoneCnt--;
					bfsQ.offer(new int[] {nr, nc});
				}
			}
		}
		
		return Math.max(safeZoneCnt, maxSafeZoneCnt);
	}
}
