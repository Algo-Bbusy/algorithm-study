package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Java_15685 {
	// 드래곤커브
	// 입력으로 드래곤커브의 시작 좌표, 시작 방향, 세대가 주어진다.
	// 이전 세대에서 시계방향으로 90도 만큼 회전한 모양이 다음 세대가 된다.
	// 방향 0 1 2 3 우 상 좌 하 (방향+1)%4=반시계방향 90도 회전
	// 주어진 예시에서 n세대인 드래곤 커브의 방향리스트는
	// 세대 0 [0]
	// 세대 1 [0, 1]
	// 세대 2 [0, 1, 2, 1]
	// 세대 3 [0, 1, 2, 1, 2, 3, 2, 1]
	// 이전 세대의 방향 리스트를 역순으로 탐색한뒤 (방향+1)%4의 방향이 다음 선분을 그리게 되는 방향이 된다.
	// Solution 1.
	// 1. 드래곤커브의 정보를 입력받는다.
	// 2. 드래곤커브의 개수만큼 맵에 점들을 표시한다.
	// 2-1. 0세대는 자기 자신의 좌표와 시작좌표+시작방향좌표를 찍는다.
	// 2-2. 1세대부터는 이전 세대의 방향좌표 리스트를 역순으로 탐색하며 세대수만큼 반복해서 점을 찍는다.
	// 3. 맵을 탐색하며 점이 찍혀있는 부분이 네 꼭짓점 전체를 이루고 있으면 정답카운트를 증가시킨다.
	// 메모리 : 14408KB 시간 : 140ms
	// 추가 내용 메모리 : 14436 시간 : 132ms
	// 풀이시간 1시간 4분 06초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N, ans;
	static boolean[][] map = new boolean[101][101];
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { 1, 0, -1, 0 };
	// 문제를 푼 이후 추가 내용
	static int x1 = 100, x2 = 0, y1 = 100, y2 = 0; // 최대 범위 설정

	static class DragonCurve {
		int y, x, dir, gen;

		public DragonCurve(int y, int x, int dir, int gen) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.gen = gen;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		DragonCurve[] dragonCurveList = new DragonCurve[N];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			int dir = Integer.parseInt(stk.nextToken());
			int gen = Integer.parseInt(stk.nextToken());
			
			setRange(y, x);
			dragonCurveList[i] = new DragonCurve(y, x, dir, gen);
		}
		for (int i = 0; i < N; i++)
			fillMap(dragonCurveList[i]);
		ans = count();
		out.write(ans + "");
		out.flush();
		out.close();
		in.close();
	}

//	private static int count() {
//		int cnt = 0;
//		for (int y = 0; y < 100; y++) {
//			for (int x = 0; x < 100; x++) {
//				if (!map[y][x])
//					continue;
//				if (map[y][x] && map[y][x + 1] && map[y + 1][x] && map[y + 1][x + 1])
//					cnt++;
//			}
//		}
//		return cnt;
//	}

	private static int count() {
		int cnt = 0;
		for(int y=y1; y<y2; y++) {
			for(int x=x1; x<x2; x++) {
				if(!map[y][x]) continue;
				if(map[y][x] && map[y][x+1] && map[y+1][x] && map[y+1][x+1]) cnt++;
			}
		}
		return cnt;
	}
//	private static void fillMap(DragonCurve DragonCurve) {
//		int gen = DragonCurve.gen;
//		ArrayList<Integer> dirList = new ArrayList<Integer>();
//		int ny = DragonCurve.y + dy[DragonCurve.dir];
//		int nx = DragonCurve.x + dx[DragonCurve.dir];
//		map[DragonCurve.y][DragonCurve.x] = true;
//		map[ny][nx] = true;
//		dirList.add(DragonCurve.dir);
//		int ndir;
//		while (gen-- > 0) {
//			for (int i = dirList.size() - 1; i >= 0; i--) {
//				ndir = (dirList.get(i) + 1) % 4;
//				ny += dy[ndir];
//				nx += dx[ndir];
//				map[ny][nx] = true;
//				dirList.add(ndir);
//			}
//		}
//	}
	private static void fillMap(DragonCurve dragonCurve) {
		int gen = dragonCurve.gen;
		ArrayList<Integer> dirList = new ArrayList<Integer>();
		int ny = dragonCurve.y + dy[dragonCurve.dir];
		int nx = dragonCurve.x + dx[dragonCurve.dir];
		setRange(ny,nx);
		map[dragonCurve.y][dragonCurve.x] = true;
		map[ny][nx] = true;
		dirList.add(dragonCurve.dir);
		int ndir;
		while (gen-- > 0) {
			for (int i = dirList.size() - 1; i >= 0; i--) {
				ndir = (dirList.get(i) + 1) % 4;
				ny += dy[ndir];
				nx += dx[ndir];
				setRange(ny,nx);
				map[ny][nx] = true;
				dirList.add(ndir);
			}
		}
	}

	private static void setRange(int y, int x) {
		if (x < x1)
			x1 = x;
		if (x > x2)
			x2 = x;
		if (y < y1)
			y1 = y;
		if (y > y2)
			y2 = y;
	}

}
