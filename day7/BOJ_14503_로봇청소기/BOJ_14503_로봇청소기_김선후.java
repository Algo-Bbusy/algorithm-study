package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_14503 {
	// 로봇 청소기
//	1. 로봇이 있는 좌표를 청소한다.
//	2. 현재있는 위치에서 방향만 회전시킨 후 다음 좌표의 조건을 체크한다.
//	3. 다음 위치가 빈공간이거나 청소하지 않은 곳이면 1칸 이동후 1로 돌아간다.
//	4. 4방향 전부 탐색해도 3으로 넘어갈 수 없으면 현재 방향을 기준으로 후진한다.
//	5. 4의 조건을 만족할 수 없으면 로봇의 이동을 멈추고 청소된 공간을 체크한다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {-1,0,1,0}; //북동남서
	static int[] dx = {0,1,0,-1};
	static int ans;
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		stk = new StringTokenizer(in.readLine());
		int robotY = Integer.parseInt(stk.nextToken());
		int robotX = Integer.parseInt(stk.nextToken());
		int direct = Integer.parseInt(stk.nextToken());
		for(int y=0; y<N; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=0; x<M; x++) {
				map[y][x]=Integer.parseInt(stk.nextToken());
			}
		}
		ans=0;
		clean(robotY,robotX,direct);
		out.append(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static void clean(int robotY, int robotX, int direct) {
		boolean check = false;
		if(!visited[robotY][robotX]) {
			visited[robotY][robotX]=true;
			ans++;
		}
		for(int d=0; d<4; d++) {
			direct = (direct+3)%4;
			int ny = robotY+dy[direct];
			int nx = robotX+dx[direct];
			
			if(map[ny][nx]==0 && !visited[ny][nx]) {
				clean(ny,nx,direct);
				check = true;
				break;
			}
		}
		if(check==false) {
			int ny = robotY-dy[direct];
			int nx = robotX-dx[direct];
			if(map[ny][nx]==0) {
				clean(ny,nx,direct);
			}
		}
	}

}
