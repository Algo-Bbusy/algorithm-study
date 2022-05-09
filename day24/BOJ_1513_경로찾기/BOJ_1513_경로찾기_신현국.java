import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	
	static int N, M, C;
	public static final int K = 1000007;
	static int[] dr = {0, -1};
	static int[] dc = {-1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 격자 크기 R
		M = Integer.parseInt(st.nextToken()); // 격자 크기 C
		C = Integer.parseInt(st.nextToken()); // 오락실 C개
		
		int[][] map = new int[N + 1][M + 1];
		
		for(int i = 1; i <= C; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[r][c] = i;		
		}
		
		int[] result = new int[C + 1];
		int cnt = 0;
		int lastGameRoom = 51;
		if(map[N][M] != 0) {
			cnt++;
			lastGameRoom = map[N][M];
		}
		
		search(map, N, M, result, cnt, lastGameRoom);
		
		for(int c : result) {
			System.out.print(c + " ");
		}
		
	}// end main

	private static void search(int[][] map, int r, int c, int[] result, int cnt, int lastGameRoom) {
		if(r == 1 && c == 1) {
			result[cnt] = (result[cnt] + 1) % K;
			return;
		}
		
		for(int i = 0; i < 2; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(nr < 1 || nr > N || nc < 1 || nc > M) continue;
			
			// 오락실이 아닌 경우
			if(map[nr][nc] == 0) {
				search(map, nr, nc, result, cnt, lastGameRoom);
			}
			
			// 오락실인 경우
			else {
				// 마지막으로 방문한 오락실보다 더 큰 오락실인 경우 방문 불가
				if(lastGameRoom < map[nr][nc]) {
					continue;
				}
				search(map, nr, nc, result, cnt + 1, map[nr][nc]);
			}
		}
	}
}
