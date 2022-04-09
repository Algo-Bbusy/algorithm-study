package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_21610 {
	// 마법사 상어와 비바라기
	// 구름이 M번 이동한 후 남아있는 물의 양의 합을 구하는 문제
	// Solution 1.
	// 1. 초기 구름의 위치 N에 따라 고정 입력
	// 2. 구름 이동 반복
	//    2-1. 구름의 위치를 담아두기 위한 큐에서 값을 순차적으로 꺼내며 이동 수행.
//		  2-2. 이동할 위치를 조건에 맞게 선정 후 해당 위치의 값을 1 증가시킨 후 방문처리
//		  2-3. 이어서 moveCloud큐에 값을 다시 넣어주고 대각선 값을 체크하여 물의양을 증가시키러 감
	// 3. 구름 이동 후 머무른 위치의 물의 값 1증가 후 소멸
//	      3-1. 반복문이 끝나면 좌표값에 카운트값 증가.
	// 4. 구름이 소멸한 위치 기준 대각선 바구니들 중 물의 양이 1 이상인 바구니가 있다면 그 개수만큼 물의 양 더함.
	// 5. 물의 양 2 이상인 위치에서 구름 재생성, 물의 양 2 감소
	// 메모리 29984KB 시간 284ms
	// 풀이시간 2시간 54분
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N,M,ans;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static class Cloud{
		int y, x;

		public Cloud(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static Queue<Cloud> q;
	static ArrayList<int[]> cmd = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		map = new int[N+1][N+1];
		isVisited = new boolean[N+1][N+1];
		
		for(int y=1; y<=N; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=1; x<=N; x++) {
				map[y][x]=Integer.parseInt(stk.nextToken());
			}
		}
		//구름 명령 입력
		for(int i=0; i<M; i++) {
			stk = new StringTokenizer(in.readLine());
			cmd.add(new int[]{Integer.parseInt(stk.nextToken()),Integer.parseInt(stk.nextToken())});
		}
		//초기 구름 생성
		q= new LinkedList<>();
		q.add(new Cloud(N, 1));
		q.add(new Cloud(N, 2));
		q.add(new Cloud(N-1, 1));
		q.add(new Cloud(N-1, 2));
		
		for(int i=0; i<M; i++) {
			int dir = cmd.get(i)[0]-1;
			int dist = cmd.get(i)[1];
			
			ans=0;
			moveCloud(dir, dist);
		}
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static void moveCloud(int dir, int dist) {
		Queue<Cloud> moveCloud = new LinkedList<>();
		while(!q.isEmpty()) {
			Cloud current = q.poll();
			int ny = current.y + (dy[dir]*dist);
			int nx = current.x + (dx[dir]*dist);
			
			while(!isBoundary(ny)) ny = slide(ny);
			while(!isBoundary(nx)) nx = slide(nx);
			
			moveCloud.add(new Cloud(ny,nx));
			map[ny][nx]++;
			isVisited[ny][nx]=true;
		}
		
		while(!moveCloud.isEmpty()) {
			Cloud current = moveCloud.poll();
			int cnt=0;
			for(int d=1; d<=7; d+=2) {
				int ny = current.y + dy[d];
				int nx = current.x + dx[d];
				
				if(!isBoundary(ny) || !isBoundary(nx) || map[ny][nx]<1) continue;
				cnt++;
			}
			map[current.y][current.x] +=cnt;
		}
		cloudMake();
	}
	private static void cloudMake() {
		for(int y=1; y<=N; y++) {
			for(int x=1; x<=N; x++) {
				if((map[y][x]>=2) && !isVisited[y][x]) {
					q.add(new Cloud(y, x));
					map[y][x] -= 2;
				}
				else isVisited[y][x] = false;
				ans += map[y][x];
			}
		}
	}
	private static int slide(int x) {
		return x<1? x+N : x-N;
	}
	private static boolean isBoundary(int x) {
		if(x>0 && x<=N) return true;
		return false;
	}

}
