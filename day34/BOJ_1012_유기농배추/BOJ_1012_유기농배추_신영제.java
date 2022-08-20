package AlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012_유기농배추 {
	static int M, N, K; // 가로 세로 배추개수
	static int[][] arr; // 배추
	static boolean[][] isVisited;

	   static int[] dx = {-1,0,1,0};
	   static int[] dy = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			arr = new int[M][N];

			isVisited = new boolean[M][N];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());

				arr[X][Y] = 1;
			}

			int cnt = 0;

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 1 && !isVisited[i][j]) {
						bfs(i,j);
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		isVisited[i][j] = true;
		q.add(new int[] { i, j });

		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int x = poll[0];
			int y = poll[1];

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
					if (arr[nx][ny] == 1 && !isVisited[nx][ny]) {
						isVisited[nx][ny] = true;
						q.add(new int[] { nx, ny });
					}
				}
			}
		}

	}

}