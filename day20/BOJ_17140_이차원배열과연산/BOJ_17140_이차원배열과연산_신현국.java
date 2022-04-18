package sh1n.bj2.ps17140이차원배열과연산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class MatrixInfo implements Comparable<MatrixInfo> {
		int number;
		int cnt;
		
		public MatrixInfo(int number, int cnt) {
			super();
			this.number = number;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(MatrixInfo o) {
			return this.cnt == o.cnt ? this.number - o.number : this.cnt - o.cnt; 
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] matrix = new int[101][101];
		for(int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= 3; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		start(R, C, K, matrix, 0, 3, 3);
	}// end main

	private static void start(int r, int c, int k, int[][] matrix, int time, int RCnt, int CCnt) {
		// 결과 출력
		if(matrix[r][c] == k) {
			System.out.println(time);
			System.exit(0);
		}
		
		// 타임 오버
		if(time == 100) {
			System.out.println(-1);
			System.exit(0);
		}
		
		// R 연산을 하는 경우
		if(RCnt >= CCnt) {
			int maxCCnt = 0;
			for(int i = 1; i <= RCnt; i++) {
				// 어떤 수가 몇번 존재하는지 인덱스를 활용하여 체크
				int[] numberCnt = new int[101];
				for(int j = 1; j <= CCnt; j++) {
					numberCnt[matrix[i][j]]++;
					matrix[i][j] = 0;
				}
				
				// 1번 이상 불린 수를 pq에 삽입
				// 카운트 오름차순, 같을 경우 숫자 오름차순
				PriorityQueue<MatrixInfo> pq = new PriorityQueue<MatrixInfo>();
				for(int j = 1; j <= 100; j++) {
					if(numberCnt[j] != 0) {
						pq.offer(new MatrixInfo(j, numberCnt[j]));
					}
				}
				
				// R 연산 후 결과 값 갱신
				int curCCnt = 0;
				while(!pq.isEmpty()) {
					if(curCCnt == 100) break;
					MatrixInfo curInfo = pq.poll();
					matrix[i][++curCCnt] = curInfo.number;
					matrix[i][++curCCnt] = curInfo.cnt;
				}
				
				// 각 행을 돌면서 최대 C값 갱신
				maxCCnt = Math.max(maxCCnt, curCCnt);
			}
			
			start(r, c, k, matrix, time + 1, RCnt, maxCCnt);
		}
		
		// C 연산을 하는 경우
		else {
			int maxRCnt = 0;
			for(int i = 1; i <= CCnt; i++) {
				
				int[] numberCnt = new int[101];
				for(int j = 1; j <= RCnt; j++) {
					numberCnt[matrix[j][i]]++;
					matrix[j][i] = 0;
				}
				
				PriorityQueue<MatrixInfo> pq = new PriorityQueue<MatrixInfo>();
				for(int j = 1; j <= 100; j++) {
					if(numberCnt[j] != 0) {
						pq.offer(new MatrixInfo(j, numberCnt[j]));
					}
				}
				
				int curRCnt = 0;
				while(!pq.isEmpty()) {
					if(curRCnt == 100) break;
					MatrixInfo curInfo = pq.poll();
					matrix[++curRCnt][i] = curInfo.number;
					matrix[++curRCnt][i] = curInfo.cnt;
				}
				
				maxRCnt = Math.max(maxRCnt, curRCnt);
			}
			
			start(r, c, k, matrix, time + 1, maxRCnt, CCnt);
		}
	}
}
