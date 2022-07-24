package AlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 모든 육지는 1로 주어 진다 => 식별을 해야함.
 * 2. 식별된 섬 끼리의 최단 거리를 구한다. 
 */
public class BOJ_2146_다리만들기 {
	static int N;
	static int[][] arr;
	static boolean[][] visit;
	static Queue<int[]> q;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// 지도의 크기
		N = Integer.parseInt(br.readLine());
		// 지도
		arr = new int[N][N];
		visit = new boolean[N][N];
		q = new LinkedList<>();
		// 지도에 섬 넣기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬 분리하는 BFS
		isLand();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void isLand() {
		// 1인 섬을 각각 섬으로 분리.
		// 섬이 1이므로 식별2부터
		int islandIdx = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j] && arr[i][j] != 0) {
					arr[i][j] = islandIdx;
					visit[i][j] = true;
					q.add(new int[] { j, i });

					while (!q.isEmpty()) {
						int[] poll = q.poll();
						int pollX = poll[0];
						int pollY = poll[1];

						for (int d = 0; d < 4; d++) {
							int nx = pollY + dy[d];
							int ny = pollX + dx[d];

							if (ny < 0 || ny > N - 1 || nx < 0 || nx > N - 1 || visit[nx][ny])
								continue;

							if (arr[nx][ny] == 1) {
								visit[nx][ny] = true;
								arr[nx][ny] = islandIdx;
								q.add(new int[] { ny, nx });
							}
						}
					}
					islandIdx++;
				}
			}

		}
	}

}
