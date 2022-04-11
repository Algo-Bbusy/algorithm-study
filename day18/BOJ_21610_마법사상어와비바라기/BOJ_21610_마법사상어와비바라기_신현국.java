package sh1n.bj2.ps21610마법사상어와비바라기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**	Case 01. 문제에 주어진 1 ~ 5번까지에 조건을 메소드로 구분하여 로직 구현
 * 	step 01. move() 메소드를 통해 현재 필드 위에 있는 구름을 이동 시키고 이동한 자리에 있는 바구니++
 * 	step 02. waterPasteBug() 메소드를 통해 구름이 있는 필드의 대각 4방 탐색을 진행하며 물이 있으면 구름이 있는 필드의 바구니++
 * 	step 03. createCloud() 메소드를 통해 현재 필드 위에 있는 구름을 제외한 구름이 생성 가능한 곳의 좌표를 생성
 * 	step 04. move() -> waterPasteBug() -> createCloud() -> move() 과정을 반복하며 M번 move()가 끝나는 경우 프로그램 종료
 *  step 05. 결과 출력
 */

public class Main_nonQ {
	
	static int N, M, step;
	static int[][] map, moveInfo;
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		moveInfo = new int[M][2];
		for(int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			moveInfo[i][0] = Integer.parseInt(st.nextToken());
			moveInfo[i][1] = Integer.parseInt(st.nextToken());
		}
		
		List<int[]> cloudPos = new ArrayList<>();
		for(int i = N; i >= N - 1; i--) {
			cloudPos.add(new int[] {i, 1});
			cloudPos.add(new int[] {i, 2});
		}
		
		step = 0;
		move(cloudPos);
		System.out.println(printResult());
	}// end main

	private static void move(List<int[]> cloudPos) {
		if(step == M) return;
		
		for(int i = 0; i < cloudPos.size(); i++) {
			// 구름 이동 시작
			int r = cloudPos.get(i)[0];
			int c = cloudPos.get(i)[1];
			// 이동한 구름의 위치
			int nr = r + dr[moveInfo[step][0]] * (moveInfo[step][1] % N);
			int nc = c + dc[moveInfo[step][0]] * (moveInfo[step][1] % N);
			
			// 범위를 벗어나면 위치 재조정
			if(nr < 1) nr += N;
			else if(nr > N) nr -= N;
			if(nc < 1) nc += N;
			else if(nc > N) nc -= N;
			
			// 구름이 이동한 위치에 물의 양 1 증가
			map[nr][nc]++;
			cloudPos.get(i)[0] = nr;
			cloudPos.get(i)[1] = nc;
		}
		
		waterPasteBug(cloudPos);
	}

	private static void waterPasteBug(List<int[]> moveCloudPos) {
		boolean[][] visited = new boolean[N + 1][N + 1];
		// 구름 입력
		for(int i = 0; i < moveCloudPos.size(); i++) {
			int r = moveCloudPos.get(i)[0];
			int c = moveCloudPos.get(i)[1];
			visited[r][c] = true;
			
			// 대각 4방 탐색
			for(int j = 2; j <= 8; j+=2) {
				int nr = r + dr[j];
				int nc = c + dc[j];
				
				if(nr < 1 || nr > N || nc < 1 || nc > N || map[nr][nc] == 0) continue;
				map[r][c]++;
			}
		}
		
		createCloud(moveCloudPos, visited);
	}

	private static void createCloud(List<int[]> moveCloudPos, boolean[][] visited) {
		List<int[]> newCloudPos = new ArrayList<>();
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(visited[i][j] || map[i][j] < 2) continue;
				map[i][j] -= 2;
				newCloudPos.add(new int[] {i, j});
			}
		}
		
		step++;
		move(newCloudPos);
	}
	
	private static int printResult() {
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				cnt += map[i][j];
			}
		}
		return cnt;
	}
}
