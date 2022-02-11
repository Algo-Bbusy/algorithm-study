package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_16926 {
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb=new StringBuilder();
	static int N, M, R, TEMP;
	//하 우 상 좌
	static int[] dy= {1,0,-1,0};
	static int[] dx= {0,1,0,-1};
	static int[][] visited;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		stk=new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		arr = new int[N][M];
		visited = new int[N][M];
		for(int n=0; n<N; n++) {
			stk=new StringTokenizer(in.readLine());
			for(int m=0; m<M; m++) {
				arr[n][m] = Integer.parseInt(stk.nextToken());
			}
		}
		for(int n=0; n<N; n++) {
			for(int m=0; m<M; m++) {
				visited[n][m] = 0;
			}
		}
		bfs(0,0,0);
		for(int[] b : arr) System.out.println(Arrays.toString(b)); System.out.println();
	}
	
	private static void bfs(int y, int x, int cnt) {
		if(cnt==R) return;
		Queue<int[]> q = new LinkedList<int[]>();
		visited[y][x]++;
		q.offer(new int[] { y, x });
		while (!q.isEmpty()) {
			int[] current = q.poll();
			y = current[0];
			x = current[1];
			
//			for (int[] b : a) System.out.println(Arrays.toString(b)); System.out.println();
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (0 <= ny && ny < N && 0 <= nx && nx < N && visited[ny][nx]==cnt) {
					TEMP=arr[ny][nx];
					if(x==0&&y==0) arr[ny][nx] = arr[y][x];
					else arr[ny][nx] = TEMP;
					q.offer(new int[] { ny, nx });
				}
			}
		}
		int[] current = q.poll();
		y = current[0];
		x = current[1];
		bfs(y,x,cnt+1);
	}

}
