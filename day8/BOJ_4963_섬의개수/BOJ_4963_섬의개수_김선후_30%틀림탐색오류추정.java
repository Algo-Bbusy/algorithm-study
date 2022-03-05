package fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_4963_fail {
	// 섬의 개수
//	2차원 맵 배열에서 연속된 섬의 덩어리(연속된 1의 덩어리)를 센다.
//	탐색에서 한 정점에서 연속으로 연결된 무언가를 찾기위해서 dfs/bfs가 유용하다.
//	dfs/bfs탐색이 한 사이클 종료될때 마다 섬의 개수를 증가시켜준다.
//	탐색한 섬의 덩어리는 방문처리를 해준다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int W,H,ans;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {-1,-1,0,1,1,1,0,-1};
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws IOException {
		while(true) {
			stk=new StringTokenizer(in.readLine());
			W = Integer.parseInt(stk.nextToken());
			H = Integer.parseInt(stk.nextToken());
//			Scanner in = new Scanner(System.in);
//			W = in.nextInt();
//			H = in.nextInt();
			if(W==0 && H==0) break;
			ans=0;
			map=new int[H][W];
			visited=new boolean[H][W];
			for(int y=0; y<H; y++) {
				stk = new StringTokenizer(in.readLine());
				for(int x=0; x<W; x++) {
					map[y][x]=Integer.parseInt(stk.nextToken());
//					map[y][x]=in.nextInt();
				}
			}
//			for(int[] b : map) System.out.println(Arrays.toString(b)); System.out.println();
			for(int y=0; y<H; y++) {
				for(int x=0; x<W; x++) {
					if(map[y][x]==1 && visited[y][x]==false) {
						dfs(y,x);
						ans++;
					}
				}
			}
//			sb.append(ans).append("\n");
			System.out.println(ans);
		}
//		out.write(sb.toString());
//		out.flush();
//		out.close();
//		in.close();
	}
	private static void dfs(int y, int x) {
		visited[y][x]=true;
		for(int d=0; d<8; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			if(ny>=0 && nx>=0 && ny<H && nx<W) {				
				if(map[ny][nx]==1 && visited[y][x]==false) dfs(ny,nx);
			}
		}
	}

}
