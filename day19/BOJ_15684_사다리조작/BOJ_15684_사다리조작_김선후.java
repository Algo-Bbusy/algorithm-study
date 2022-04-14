package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java_15684 {
	// 사다리조작
	// i번부터 출발하여 i번으로 다시 돌아오게 하는 사다리게임
	// 추가적으로 배치하는 사다리 가로개수의 최솟값 구하기 문제
	// Solution 1.
	//1. 사다리 맵 배열의 한 점에서 우측으로 연결된 선을 1, 좌츨을 -1로 입력
	//2. dfs를 통해 사다리 개수가 3개 이하일때까지의 모든 경우의 수중 완성이 먼저되면 사다리개수 출력 후 시스템 종료
	//   2-1. 각 사다리 높이 1에서 부터 시작해서 1을만나면 오른쪽으로 이동 -1을 만나면 왼쪽으로 이동 그 후 하강
	//	 2-2. 초기 모든 시작x좌표와 도착점의 x좌표가 같으면 완성 하나라도 같지 않으면 미완성.
	//3. dfs를 전부 돌았음에도 완성이 되지 않았다면 불가능하거나 3개초과인 경우이므로 ans 초기값-1 출력
	//메모리 18796KB 시간 1016ms
	//풀이시간 2시간 44분
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N,M,H,ans=-1;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		input();
		lineMap();
	}
	private static void lineMap() throws IOException {
		for(int i=0; i<4; i++) {
			dfs(0,i);
		}
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static void dfs(int cnt, int depth) {
		if(cnt==depth) {
//			for(int [] b : map) System.out.println(Arrays.toString(b));
			if(complete()) {
				System.out.print(depth);
				System.exit(0);
			}
			return;
		}
		for(int y=1; y<=H; y++) {
			for(int x=1; x<N; x++) {
				if(map[y][x] !=0 || map[y][x+1] >0) continue;
				map[y][x]=1;
				map[y][x+1]=-1;
				dfs(cnt+1,depth);
				map[y][x]=0;
				map[y][x+1]=0;
			}
		}
	}
	private static boolean complete() {
		boolean end = true;
		for(int x=1; x<=N; x++) {
			int current_y = 1;
			int current_x = x;
			while(current_y<H+1) {
				if(map[current_y][current_x]>0) {
					current_x++;
				}
				else if(map[current_y][current_x]<0) {
					current_x--;
				}
				current_y++;
			}
			if(current_x != x) {
				end = false;
				break;
			}
		}
		return end;
	}
	private static void input() throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		H = Integer.parseInt(stk.nextToken());
		
		map = new int[H+1][N+1];
		
		for(int i=0; i<M; i++) {
			stk = new StringTokenizer(in.readLine());
			int y = Integer.parseInt(stk.nextToken());
			int x = Integer.parseInt(stk.nextToken());
			
			map[y][x] = 1;
			map[y][x+1]=-1;
		}
	}

}
