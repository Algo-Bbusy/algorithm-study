package AlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1080_행렬 {

	static int N;
	static int M;
	static int cnt;
	static int[][] beforeArr;
	static int[][] afterArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		beforeArr = new int[N][M];
		afterArr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				beforeArr[i][j] = str.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				afterArr[i][j] = str.charAt(j) - '0';
			}
		}

		check();
		System.out.println(compare() == -1 ? -1 : cnt);
	}

	private static int compare() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (beforeArr[i][j] != afterArr[i][j]) {
					return -1;
				}
			}
		}
		return 0;
	}

	private static void check() {
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 2; j++) {
				if (beforeArr[i][j] != afterArr[i][j]) {
					cnt++;
					change(i, j);
				}
			}
		}
	}

	private static void change(int x, int y) {
		for (int i = x; i <= x + 2; i++) {
			for (int j = y; j <= y + 2; j++) {
				beforeArr[i][j] = beforeArr[i][j] == 0 ? 1 : 0;
			}
		}
	}
}
