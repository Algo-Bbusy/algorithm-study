import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 	#. 학생 N^2명
 * 	#. 인접 조건 = 4방
 *
 *	1. 빈칸 중 인접한 칸에 좋아하는 학생이 가장 많은 경우 1순위
 *	2. 1번이 여러 개라면 인접한 칸 중 비어있는 칸이 가장 많은 칸
 *	3. 2번이 여러 개라면 행번호가 가장 작은 칸 다음은 열번호가 가장 작은 칸 
 */

public class Main {
	
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	static class Student {
		int[] info;
		
		public Student(int[] info) {
			super();
			this.info = info;
		}
	}
	
	static class Node implements Comparable<Node> {
		int r, c, adjCnt, likeCnt;

		public Node(int r, int c, int adjCnt, int likeCnt) {
			super();
			this.r = r;
			this.c = c;
			this.adjCnt = adjCnt;
			this.likeCnt = likeCnt;
		}
		
		public int compareTo(Node o) {
			if(this.likeCnt == o.likeCnt) {
				if(this.adjCnt == o.adjCnt) {
					if(this.r == o.r) {
						return this.c - o.c;
					} else {
						return this.r - o.r;
					}
				} else {
					return o.adjCnt - this.adjCnt;
				}
			} else {
				return o.likeCnt - this.likeCnt;
			}
		};
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int size = N * N;
		int[][][] map = new int[N + 1][N + 1][5];
		Student[] students = new Student[size + 1];
		for(int i = 1; i <= size; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			students[i] = new Student(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
							, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		// 첫 번째 학생은 항상 (2, 2) 자리에 착석
		map[2][2] = students[1].info;
		
		// 2번 학생부터 자리 배정 시작
		for(int step = 2; step <= size; step++) {
			Student curSt = students[step];
			
			PriorityQueue<Node> pq = new PriorityQueue<Node>();
			// 빈 자리 찾기
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					// 빈 자리를 발견하면
					if(map[i][j][0] == 0) {
						int blankCnt = 0;
						int likeCnt = 0;
						for(int d = 0; d < 4; d++) {
							int nr = i + dr[d];
							int nc = j + dc[d];
							
							if(nr < 1 || nr > N || nc < 1 || nc > N) continue;
							if(map[nr][nc][0] == 0) {
								blankCnt++;
							} else {
								for(int k = 1; k < 5; k++) {
									if(curSt.info[k] == map[nr][nc][0]) {
										likeCnt++;
									}
								}
							}
						}
						
						pq.offer(new Node(i, j, blankCnt, likeCnt));
					}
				}
			}
			
			Node targetNode = pq.poll();
			map[targetNode.r][targetNode.c] = curSt.info; 
		}
		
		int result = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				int likeCnt = 0;
				for(int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					if(nr < 1 || nr > N || nc < 1 || nc > N) continue;
					for(int k = 1; k <= 4; k++) {
						if(map[i][j][k] == map[nr][nc][0]) {
							likeCnt++;
						}
					}
				}
				
				result += (int)Math.pow(10, likeCnt - 1);
			}
		}
		
		System.out.println(result);
	}// end main
}
