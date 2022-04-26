import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			String mapEl = br.readLine();
			for(int j = 0; j < N; j++) {
				if(mapEl.charAt(j) == '1') {
					map[i][j] = true;
				}
			}
		}

		PriorityQueue<Integer> adjAreaCnt = new PriorityQueue<Integer>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j]) {
					int cnt = searchAdjArea(map, i, j, N);
					adjAreaCnt.offer(cnt);
				}
			}
		}
		
		System.out.println(adjAreaCnt.size());
		while(!adjAreaCnt.isEmpty()) {
			System.out.println(adjAreaCnt.poll());
		}
		
	}// end main

	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	private static int searchAdjArea(boolean[][] map, int r, int c, int size) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		map[r][c] = false;
		int adjCnt = 1;
		
		while(!q.isEmpty()) {
			int[] curPos = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = curPos[0] + dr[i];
				int nc = curPos[1] + dc[i];
				
				if(nr < 0 || nr >= size || nc < 0 || nc >= size || !map[nr][nc]) continue;
				
				map[nr][nc] = false;
				adjCnt++;
				q.offer(new int[] {nr, nc});
			}
		}
		
		return adjCnt;
	}

}
