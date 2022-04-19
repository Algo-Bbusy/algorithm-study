package fail;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_20058_fail {
	// 마법사 상어와 파이어스톰
	//Solution 1.(실패)
	//1. 2^N만큼 맵배열을 받는다.
	//2. 맵배열 전체를 마법단계L을 기준으로 회전시킨다.
	//3. 회전된 맵배열을 전체 탐색하며 얼음을 녹여준다.
	//4. 2,3단계를 주어진 횟수만큼 반복수행한다.
	//5. 최종적으로 나온 맵배열에서 제일 큰 덩어리(bfs)와 얼음의 합(브루트포스)을 출력한다.
	//실패 원인 분석
	//6번 테케에서 가장 큰 덩어리값이 9가 아닌 7이 나온다. 원인 파악 못하였음 ㅠ
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N,Q,ans,ansLarge;
	static int[][] map;
	static boolean[][] meltIce;
	static boolean[][] isChecked;
	static int[] magicLevel;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		input();
		rotate();
		getAns();
		getAnsLarge();
		sb.append(ans).append("\n").append(ansLarge);
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static void getAnsLarge() {
		isChecked = new boolean[N][N];
		ansLarge = Integer.MIN_VALUE;
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(isChecked[y][x]) continue;
				int adjCnt=0;
				Queue<Point> q = new LinkedList<Point>();
				q.add(new Point(y,x));
				while(!q.isEmpty()) {
					Point current = q.poll();
					for(int d=0; d<4; d++) {
						int ny= current.y+dy[d];
						int nx= current.x+dx[d];
						if(isBoundary(ny, nx)) continue;
						if(isChecked[ny][nx] || map[ny][nx]<=0) continue;
						isChecked[ny][nx]=true;
						adjCnt++;
						q.add(new Point(ny, nx));
					}
					ansLarge = Math.max(adjCnt, ansLarge);
				}
			}
		}
	}
	private static void getAns() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(map[y][x]>0) ans+=map[y][x];
			}
		}
	}
	private static void rotate() throws IOException {
		magicLevel = new int[Q];
		stk = new StringTokenizer(in.readLine());
		for(int i=0; i<Q; i++) {
			int level = Integer.parseInt(stk.nextToken());
			level = 1<<level;
			magicLevel[i] = level;
			map = storm(magicLevel[i]);
			melt();
		}
	}
	private static void melt() {
		meltIce = new boolean[N][N];
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				int adjCnt=0;
				if(map[y][x]==0) continue;
				for(int d=0; d<4; d++) {
					int ny = y+dy[d];
					int nx = x+dx[d];
					if(isBoundary(ny,nx)) continue;
					if(map[ny][nx]<=0) continue;
					adjCnt++;
				}
				if(adjCnt<3) meltIce[y][x]=true;
			}
		}
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(meltIce[y][x]) map[y][x]--;
			}
		}
	}
	private static boolean isBoundary(int ny, int nx) {
		if(ny<0 || nx<0 || ny>=N || nx>=N) return true;
		return false;
	}
	private static int[][] storm(int range) {
		int[][] tempMap = new int[N][N];
		for(int startY=0; startY<N; startY += range) {
			for(int startX=0; startX<N; startX += range) {
				for(int y=0; y<range; y++) {
					for(int x=0; x<range; x++) {
						tempMap[startX+y][startY+x]=map[startX+range-1-x][startY+y];
					}
				}
			}
		}
		return tempMap;
	}
	private static void input() throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		Q = Integer.parseInt(stk.nextToken());
		
		N=1<<N;
		map = new int[N][N];
		for(int y=0; y<N; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=0; x<N; x++) {
				map[y][x]=Integer.parseInt(stk.nextToken());
			}
		}
	}

}
