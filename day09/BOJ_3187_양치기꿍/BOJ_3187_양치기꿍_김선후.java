package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_3187 {
	//양치기 꿍
//	전체 늑대와 양의 수를 저장해둔다.
//	각 영역별로 늑대와 양의 수를 파악한다.
//	영역을 구분할 때 경계체크 울타리의 좌표를 만나면 경계를 벗어나는 조건을 넣어준다.
//	dfs/bfs 어느쪽도 상관없다. 늑대양은 대각선 이동불가(4방탐색)
//	대소비교를 통해서 전체값에서 조절한다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb=new StringBuilder();
	static int R,C,wolves,sheeps,wolf,sheep;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static char[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		R=Integer.parseInt(stk.nextToken());
		C=Integer.parseInt(stk.nextToken());
		map=new char[R][C];
		visited=new boolean[R][C];
		for(int y=0; y<R; y++) {
			String str = in.readLine();
			for(int x=0; x<C; x++) {
				map[y][x]=str.charAt(x);
				if(map[y][x]=='k') sheeps++;
				else if(map[y][x]=='v') wolves++;
			}
		}
		for(int y=0; y<R; y++) {
			for(int x=0; x<C; x++) {
				if(map[y][x]!='#' && !visited[y][x]) {
					wolf=0;
					sheep=0;
					dfs(y,x);
					if(sheep>wolf) wolves -= wolf;
					else sheeps -= sheep;
				}
			}
		}
		out.append(sheeps+" "+wolves);
		out.flush();
		out.close();
		in.close();
	}
	private static void dfs(int y, int x) {
		visited[y][x]=true;
		if(map[y][x]=='k') sheep++;
		else if(map[y][x]=='v') wolf++;
		for(int d=0; d<4; d++) {
			int ny=y+dy[d];
			int nx=x+dx[d];
			if(ny<0 || nx<0 || ny>=R || nx>=C || visited[ny][nx] || map[ny][nx]=='#') continue;
			dfs(ny,nx);
		}
	}

}
