package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Java_16234 {
	// 인구 이동
//	1. dfs로 전체 map에서 방문하지 않은 곳부터 시작해서 조건에 맞는(차이가 L<=x<=R)인 곳 담아두기
//	2. dfs 탐색이 끝난 후 리스트의 개수를 확인하고 조건에 맞는 데이터가 있을 경우 map 데이터 평균 값으로 수정
//	3. 모두 방문했을 경우, 방문여부를 확인하는 배열을 초기화 하고 인구이동이 필요한지 다시 조건체크. 카운트 증가 후 필요하면 1로 돌아감.
//	메모리 298544KB 시간 580ms
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N,L,R,ans;
	static int map[][];
	static boolean isVisited[][];
	static int dy[] = {1,-1,0,0};
	static int dx[] = {0,0,-1,1};
	static class Area{
		int y,x;

		public Area(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		L = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		map=new int[N][N];
		for(int y=0; y<N; y++) {
			stk=new StringTokenizer(in.readLine());
			for(int x=0; x<N; x++) {
				map[y][x]=Integer.parseInt(stk.nextToken());
			}
		}
		while(true) {
			isVisited = new boolean[N][N];
			if(isContinue()) ans++;
			else break;
		}
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static boolean isContinue() {
		List<Area> unionList;
		boolean isContinue = false;
		int sum;
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(!isVisited[y][x]) {
					unionList = new LinkedList<>();
					unionList.add(new Area(y,x));
					sum = dfs(y,x,unionList,0);
					if(unionList.size()>1) {
						changeMap(sum,unionList);
						isContinue=true;
					}
				}
			}
		}
		return isContinue;
	}
	private static void changeMap(int sum, List<Area> unionList) {
		int avg = sum/unionList.size();
		for(Area node : unionList) map[node.y][node.x]=avg;
	}
	private static int dfs(int y, int x, List<Area> unionList, int sum) {
		isVisited[y][x]=true;
		sum=map[y][x];
		for(int d=0; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			if(isPosible(ny,nx)) {
				int diff = Math.abs(map[y][x]-map[ny][nx]);
				if(diff>=L && diff<=R) {
					unionList.add(new Area(ny,nx));
					sum += dfs(ny,nx,unionList,sum);
				}
			}
		}
		return sum;
	}
	private static boolean isPosible(int ny, int nx) {
		if(ny>=0 && nx>=0 && ny<N && nx<N && !isVisited[ny][nx]) return true;
		return false;
	}

}
