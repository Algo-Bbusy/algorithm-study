package sh1n.bj2.ps20058마법사상어와파이어스톰;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**	문제에 주어진 조건대로 시뮬레이션 구현
 *	step 01. 파이어스톰 Q번 실행 => Q회 반복 
 *	step 02. L 단계의 맵 회전 실행
 *	step 03. 모든 [r][c]의 4방 탐색 진행, 각 [r][c]과 인접해 있는 얼음이 2개 이하인 경우 얼음 [r][c]1감소
 * 	step 04. (step 02. ~ step 03.) 과정 Q회 반복
 * 	step 05. 남은 얼음의 합과, 얼음 중 가장 큰 덩어리를 차지하는 칸의 개수 
 * 		 	 => DFS 탐색을 하면서 합은 누적하고, 큰 덩어리를 차지하는 칸의 개수 최댓값을 갱신하며 동시에 처리
 */

public class Main {
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int mapSize = 1 << N;
		int[][] map = new int[mapSize][mapSize];
		
		for(int i = 0; i < mapSize; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int[] L = new int[Q + 1];
		for(int i = 1; i <= Q; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}
		
		// 파이어스톰 Q번 시전
		for(int i = 1; i <= Q; i++) {
			// L 단계 맵 회전 실행
			if(L[i] != 0) {
				executeLevelL(map, L[i], mapSize);
			}
			meltIce(map, mapSize);
		}
		
		// [0]: 얼음의 합, [1]: 가장 큰 덩어리의 칸의 개수
		int[] result = new int[2];
		boolean[][] visited = new boolean[mapSize][mapSize];
		for(int i = 0; i < mapSize; i++) {
			for(int j = 0; j < mapSize; j++) {
				if(map[i][j] != 0) {
					int[] cntIce = new int[2];
					cntIce[0] += map[i][j];
					cntIce[1]++;
					visited[i][j] = true;
					searchIce(map, cntIce, i, j, mapSize, visited);
					result[0] += cntIce[0];
					result[1] = Math.max(result[1], cntIce[1]);
				}
			}
		}
		
		System.out.println(result[0] + "\n" + result[1]);
		
	}// end main
	
	// 분할 정복으로 구현
	// 레벨 l을 실현하기 위해서는 레벨 1부터 레벨 l까지 아래 로직을 적용하면 적용됨
	private static void executeLevelL(int[][] map, int l, int n) {
		for(int s = 1; s <= l; s++) {
			int step = 1 << s;
			for(int i = 0; i < n; i += step) {
				for(int j = 0; j < n; j += step) {
					rotateMap(map, i, j, n, s);
				}
			}
		}
		
	}
	
	private static void rotateMap(int[][] map, int r, int c, int n, int l) {
		int step = 1 << (l - 1);
		for(int i = r; i < r + step; i++) {
			for(int j = c; j < c + step; j++) {
				int temp1 = map[i][j];
				int temp2 = map[i][j + step];
				int temp3 = map[i + step][j + step];
				int temp4 = map[i + step][j];
				map[i][j] = temp4;
				map[i][j + step] = temp1;
				map[i + step][j + step] = temp2;
				map[i + step][j] = temp3;
			}
		}
	}
	
	// 얼음이 존재하는 곳에 얼음 녹이기 시도
	private static void meltIce(int[][] map, int n) {
		Stack<int[]> meltArea = new Stack<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] != 0) {
					search4Dir(map, i, j, n, meltArea);
				}
			}
		}
		
		while(!meltArea.isEmpty()) {
			int[] pos = meltArea.pop();
			map[pos[0]][pos[1]]--;
		}
	}

	// 4방 탐색으로 얼음 존재 여부 판단
	// 2개 이하인 경우 얼음 감소 대상
	private static void search4Dir(int[][] map, int r, int c, int n, Stack<int[]> meltArea) {
		int cnt = 0;
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == 0) continue;
			cnt++;
		}
		if(cnt <= 2) meltArea.push(new int[] {r, c});
	}

	// 모든 얼음의 합과 해당 구역의 최대 칸수 구하기
	private static void searchIce(int[][] map, int[] cntIce, int r, int c, int mapSize, boolean[][] visited) {
		for(int i = 0 ;i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 0 || nr >= mapSize || nc < 0 || nc >= mapSize || visited[nr][nc] || map[nr][nc] == 0) continue;
			visited[nr][nc] = true;
			cntIce[0] += map[nr][nc];
			cntIce[1]++;
			map[nr][nc] = 0;
			searchIce(map, cntIce, nr, nc, mapSize, visited);
		}
	}
}
