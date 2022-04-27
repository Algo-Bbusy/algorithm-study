package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java_2667 {
	// 단지번호붙이기
	//메모리 14604KB 시간 132ms
	//Solution 1.
	//1. 지도에서 집이 있는 좌표(1)을 시작점으로 dfs 수행 시작
	//2. dfs 내부에서 사방탐색
	//3. 한번의 dfs 수행이 끝나면 하나의 연결덩어리가 완성되므로 단지 번호 ans값 증가
	//4. 최종 단지 번호 수만큼의 크기를 지닌 단지 개수 체크 배열 생성
	//5. 완탐을 통해서 맵 좌표가 0이 아닌곳을 해당좌표의 값을 인덱스로 개수 증가
	//6. 배열 오름차순 정렬 후 시작 인덱스 1번을 기준으로 최종 단지 번호만큼 단지 개수 출력 
	// 풀이시간 42분 12초
	static int N, ans;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[] houseCnt;
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		isVisited = new boolean[N][N];
		for(int y=0; y<N; y++) {
			String[] str = in.readLine().split("");
			for(int x=0; x<N; x++) {
				map[y][x] = Integer.parseInt(str[x]);
			}
		}
		ans=1;
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(map[y][x]==1 && !isVisited[y][x]) {
					dfs(y,x);
					ans++;
				}
			}
		}
		sb.append(ans-1).append("\n");
		houseCnt = new int[ans];
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(map[y][x]!=0) houseCnt[map[y][x]]++;
			}
		}
		Arrays.sort(houseCnt);
		for(int cnt=1; cnt<ans; cnt++) {
			sb.append(houseCnt[cnt]).append("\n");
		}
		sb.setLength(sb.length()-1);
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static void dfs(int y, int x) {
		map[y][x]=ans;
		isVisited[y][x]=true;
		for(int d=0; d<4; d++) {
			int ny= y+dy[d];
			int nx= x+dx[d];
			if(isBoudary(ny,nx)) continue;
			if(map[ny][nx]==1 && !isVisited[ny][nx]) dfs(ny,nx);
		}
	}
	private static boolean isBoudary(int ny, int nx) {
		if(ny<0 || nx<0 || ny>=N || nx>=N) return true;
		return false;
	}

}
