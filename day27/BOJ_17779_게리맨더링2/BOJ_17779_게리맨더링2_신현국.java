import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 격자 크기 N
		
		int[][] map = new int[N + 1][N + 1];
		int totalPopulation = 0;
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int ppl = Integer.parseInt(st.nextToken());
				map[i][j] = ppl;
				totalPopulation += ppl; 
			}
		}
		
		int minSum = Integer.MAX_VALUE;
		// (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
		for(int x = 1; x <= N; x++) {
			for(int y = 2; y < N; y++) { // 조건 갱신
				for(int d1 = 1; d1 < N; d1++) {
					for(int d2 = 1; d2 < N; d2++) {
						if(x + d1 + d2 > N) continue;
						if(1 > y - d1) continue;
						if(y + d2 > N) continue;
						
						int[][] div_map = new int[N + 1][N + 1];
						makeArea(x, y, d1, d2, div_map);
						
						int[] div_populationCnt = new int[6];
						getPopulationCnt(div_map, div_populationCnt, totalPopulation, map);
						
						int min = div_populationCnt[1];
						int max = div_populationCnt[1];
						for(int i = 1; i <= 5; i++) {
							if(min > div_populationCnt[i]) min = div_populationCnt[i];
							if(max < div_populationCnt[i]) max = div_populationCnt[i];
						}
						
						minSum = Math.min(minSum, max - min);
					}
				}
			}
		}
		
		System.out.println(minSum);
	}// end main

	private static void getPopulationCnt(int[][] div_map, int[] div_populationCnt, int totalPopulation, int[][] map) {
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				if(div_map[r][c] == 5 || div_map[r][c] == 0) continue;
				div_populationCnt[div_map[r][c]] += map[r][c];
			}
		}
		
		div_populationCnt[5] = totalPopulation; 
		for(int i = 1; i < 5; i++) {
			div_populationCnt[5] -= div_populationCnt[i];
		}
	}

	private static void makeArea(int x, int y, int d1, int d2, int[][] div_map) {
		// 1. (x, y), (x+1, y-1), ..., (x+d1, y-d1)
		for(int dis = 0; dis <= d1; dis++) {
			div_map[x + dis][y - dis] = 5;
		}
			
		// 2. (x, y), (x+1, y+1), ..., (x+d2, y+d2)
		for(int dis = 0; dis <= d2; dis++) {
			div_map[x + dis][y + dis] = 5;
		}
		
		
		// 3. (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
		for(int dis = 0; dis <= d2; dis++) {
			div_map[x + d1 + dis][y - d1 + dis] = 5;
		}
		
		// 4. (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
		for(int dis = 0; dis <= d1; dis++) {
			div_map[x + d2 + dis][y + d2 - dis] = 5;
		}
		
		// 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
		for(int r = 1; r < x + d1; r++) {
			for(int c = 1; c <= y; c++) {
				if(div_map[r][c] == 5) break;
				div_map[r][c] = 1;
			}
		}
		
		// 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
		for(int r = 1; r <= x + d2; r++) {
			for(int c = N; c > y; c--) {
				if(div_map[r][c] == 5) break;
				div_map[r][c] = 2;
			}
		}
		
		// 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
		for(int r = x + d1; r <= N; r++) {
			for(int c = 1; c < y - d1 + d2; c++) {
				if(div_map[r][c] == 5) break;
				div_map[r][c] = 3;
			}
		}
		
		// 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
		for(int r = x + d2 + 1; r <= N; r++) {
			for(int c = N; c >= y - d1 + d2; c--) {
				if(div_map[r][c] == 5) break;
				div_map[r][c] = 4;
			}
		}
	}
}
