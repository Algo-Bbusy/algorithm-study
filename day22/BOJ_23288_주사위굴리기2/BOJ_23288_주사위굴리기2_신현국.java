import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K;
	// 동 남 서 북
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	
	static class Dice {
		int roof, ground, east, west, south, north;
		
		public Dice() {
			super();
			init();
		}

		private void init() {
			this.roof = 1;
			this.ground = 6;
			this.east = 3;
			this.west = 4;
			this.south = 2;
			this.north = 5;
			
		}
		
		private void move(int dir) {
			int[] deltasInfo;
			if(dir % 2 == 1) {
				deltasInfo = new int[] {this.south, this.roof, this.north, this.ground};
			} else {
				deltasInfo = new int[] {this.roof, this.east, this.ground, this.west};
			}
			
			if(dir <= 1) {
				int temp1 = deltasInfo[0];
				for(int i = 1; i < 4; i++) {
					int temp2 = deltasInfo[i];
					deltasInfo[i] = temp1;
					temp1 = temp2;
				}
				deltasInfo[0] = temp1;
			} else {
				int temp = deltasInfo[0];
				for(int i = 0; i < 3; i++) {
					deltasInfo[i] = deltasInfo[i + 1];
				}
				deltasInfo[3] = temp;
			}
			
			if(dir % 2 == 1) {
				this.south = deltasInfo[0];
				this.roof = deltasInfo[1];
				this.north = deltasInfo[2];
				this.ground = deltasInfo[3];
			} else {
				this.roof = deltasInfo[0];
				this.east = deltasInfo[1];
				this.ground = deltasInfo[2];
				this.west = deltasInfo[3];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 맵 생성
		int[][] map = new int[N + 1][M + 1]; // 맵에 적혀 있는 번호 정보
		int[][] dp = new int[N + 1][M + 1]; // 해당 맵을 갔을 때 얻을 수 있는 점수
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j]--;
			}
		}
		// 주사위 생성
		Dice dice = new Dice();
		
		// 초기값 세팅
		int dir = 0;
		int r = 1, c = 1;
		int sum = 0;
		
		// K번 이동 진행 
		for(int step = 1; step <= K; step++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(nr < 1 || nr > N || nc < 1 || nc > M) {
				nr = r - dr[dir];
				nc = c - dc[dir];
				dir = (dir + 2) % 4;
			}
			
			dice.move(dir);
			r = nr;
			c = nc;
			if(map[r][c] < dice.ground) {
				dir = (dir + 1) % 4;
			} else if(map[r][c] > dice.ground) {
				dir = (dir + 3) % 4;
			}
			
			if(dp[r][c] != -1) {
				sum += dp[r][c];
			} else {
				sum += bfs(r, c, map, dp);
			}
		}
		System.out.println(sum);
	}// end main

	private static int bfs(int r, int c, int[][] map, int[][] dp) {
		boolean[][] visited  = new boolean[N + 1][M + 1];
		List<int[]> sameAreaPos = new ArrayList<>();
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		sameAreaPos.add(new int[] {r, c});
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int[] curPos = q.poll();
			int cr = curPos[0];
			int cc = curPos[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(nr < 1 || nr > N || nc < 1 || nc > M || visited[nr][nc] || map[nr][nc] != map[cr][cc]) continue;
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc});
				sameAreaPos.add(new int[] {nr, nc});
			}
		}

		int sameCnt = sameAreaPos.size();
		int point = sameCnt * map[r][c];
		for(int i = 0; i < sameCnt; i++) {
			int[] pos = sameAreaPos.get(i);
			dp[pos[0]][pos[1]] = point;
		}
		
		return point;
	}
}
