package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_17142 {
	// 연구소 3
	//메모리 40580KB 시간 268ms
	// 조합과 bfs 혼합 문제였습니다.
	// 조합을 사용해서 활성시킬 바이러스들의 좌표를 큐에 입력하고
	//한번의 depth(1초를 한사이클)내에서 들어간 해당 큐의 개수만큼 사방탐색을 진행하는 문제.
	// 빈 공간 없이 처음부터 전부 벽과 바이러스로 이루어진 반례가 존재하므로 빈공간을 체크하는 변수 필요
	//Solution 1.
	//1. 입력을 받으면서 빈 공간의 개수 (blank)와 바이러스의 좌표(viruses)를 저장
	//2. 만약 빈공간이 없다면 ans값은 0이 되야한다.
	//3. 전체 바이러스 리스트중에서 M개의 바이러스를 선택하여 모든 조합의 경우를 탐색하여 바이러스 확산
	//4. 선택한 바이러스를 큐로 옮긴 후 bfs를 사용하여 확산 수행
	//5. 탐색을 하며 빈 공간인지 확인 후 빈 공간(blankSpace)감소 주의 할 점은 blank는 static 변수이기에 항상 초기화 시킬 변수 필요
	//6. 마찬가지로 isVisited(방문체크) 배열 또한 확산 메서드가 실행될때마다 초기화 필요
	//7. 1초의 한 사이클(각 depth단계)마다 입력된 큐만큼만 사방탐색을 할 필요가 있음. (시간단위 구분이 존재하기 때문)
	//8. 한 사이클의 큐에 있는 모든 바이러스의 확산이 완료되면 time 증가.
	//9. 빈 칸이 더 이상 존재 하지 않는 경우 최단시간 갱신.
	//10. 만약 현재까지 갱신된 최단 시간보다 현 수행과정 중의 최단시간이 더 오래 걸린다면 더 이상 검사할 필요가 없다.
	// 풀이시간 58분 14초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N,M,ans,blank;
	static int[][] map;
	static boolean[][] isVisited;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static class Virus{
		int y, x;

		public Virus(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	static List<Virus> viruses = new ArrayList<>();
	static Virus[] selectedVirus;
	public static void main(String[] args) throws IOException {
		input();
		if(blank==0) {
			ans=0;
			sb.append(ans);
		}
		else {
			virusComb(0,0);
			sb.append(ans==Integer.MAX_VALUE?-1:ans);
		}
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static void virusComb(int depth, int start) {
		if(depth==M) {
			virusSpread();
			return;
		}
		for(int i=start; i<viruses.size(); i++) {
			selectedVirus[depth] = viruses.get(i);
			virusComb(depth+1, i+1);
		}
	}
	private static void virusSpread() {
		Queue<Virus> q = new LinkedList<>();
		isVisited = new boolean[N][N];
		for(Virus select : selectedVirus) {
			q.add(select);
			isVisited[select.y][select.x]=true;
		}
		
		int time=0;
		int blankSpace = blank;
		while(!q.isEmpty()) {
			int nowDepth = q.size();
			for(int i=0; i<nowDepth; i++) {
				Virus current = q.poll();
				for(int d=0; d<4; d++) {
					int ny = current.y+dy[d];
					int nx = current.x+dx[d];
					
					if(isBoundary(ny,nx)) continue;
					if(isPossible(ny,nx)) continue;
					
					if(map[ny][nx]==0) {
						blankSpace--;
					}
					isVisited[ny][nx]=true;
					q.add(new Virus(ny, nx));
				}
			}
			time++;
			if(time >= ans) return;
			if(blankSpace <= 0) ans = time;
		}
	}
	
	private static boolean isPossible(int ny, int nx) {
		if(isVisited[ny][nx] || map[ny][nx]==1) return true;
		return false;
	}
	private static boolean isBoundary(int ny, int nx) {
		if(ny<0 || nx<0 || ny>=N || nx>=N) return true;
		return false;
	}
	private static void input() throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		map = new int[N][N];
		isVisited = new boolean[N][N];
		selectedVirus = new Virus[M];
		
		for(int y=0; y<N; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=0; x<N; x++) {
				map[y][x]=Integer.parseInt(stk.nextToken());
				if(map[y][x]==0) blank++;
				else if(map[y][x]>1) viruses.add(new Virus(y, x));
			}
		}
//		System.out.println(blank);
		ans = Integer.MAX_VALUE;
	}

}
