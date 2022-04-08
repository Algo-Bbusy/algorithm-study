package com.ssafy.study.homework18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21610_마법사상어와비바라기 {

	static int N, M;
	static int count;

	static int arr[][];
	static boolean cloud[][];

	// 전체
	static int dx[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };

	// 대각선
	static int di[] = { -1, -1, 1, 1 };
	static int dj[] = { -1, 1, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][N + 1];
		cloud = new boolean[N + 1][N + 1]; // 구름의 상태 정보

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cloud[N][1] = true;
		cloud[N][2] = true;
		cloud[N - 1][1] = true;
		cloud[N - 1][2] = true;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int D = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
					
			// 1. di방향으로 si칸 이동
			// 2. 이동이 완료되었으면 해당 칸 +1씩
			// 3. 구름제거 (따른 메서드가 굳이 필요없음 자동으로 제거되기 때문)
			moveCloud(D, S);
			// 4. 물복사버그 실행 (대각선 카운트)
			copyWater();
			// 5. 이동이 완료되었다면 새로운 구름으로 변경
			// 현재 구름이었던곳 제외, 물의 양이 2 이상인곳만 구름 생성
			// 생성 된곳 -2
			changeCloud();
		}

		count = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				count += arr[i][j];
			}
		}

		System.out.println(count);
	}

	private static void changeCloud() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cloud[i][j] == true) {
					cloud[i][j] = false;
				} else if (arr[i][j] >= 2) {
					cloud[i][j] = true;
					arr[i][j] -= 2;
				}
			}
		}
	}

	private static void copyWater() {

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cloud[i][j] == true) {
					int sum = 0;
					for (int d = 0; d < 4; d++) {

						int nx = i + di[d];
						int ny = j + dj[d];

						// 대각선 체크경우 범위를 넘어가면 안되므로 범위체크
						if (nx < 1 || nx > N || ny < 0 || ny > N)
							continue;

						// 대각선 0개 ~ 4개
						if (arr[nx][ny] > 0)
							sum++;
					}
					arr[i][j] += sum;
				}
			}
		}

	}

	private static void moveCloud(int d, int s) {
		// 이동할때 사용하는 배열 생성.
		boolean moveCloud[][] = new boolean[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cloud[i][j] == true) {

					int nx = (i + dx[d - 1] * s) % N;
					int ny = (j + dy[d - 1] * s) % N;

					// 1번과 N번은 연결되어있기 때문에.
					if (nx == 0)
						nx = N;
					else if (nx < 0)
						nx = N + nx;

					if (ny == 0)
						ny = N;
					else if (ny < 0)
						ny = N + ny;

					moveCloud[nx][ny] = true;
				}
			}
		}
		// 이동한 배열을 메인구름에 설정.
		cloud = moveCloud;

		// 이동 했다면 정착한 칸에 +1씩 증가.
		for (int k = 1; k <= N; k++) {
			for (int b = 1; b <= N; b++) {
				if (cloud[k][b] == true) {
					arr[k][b]++;
				}
			}
		}

	}

}