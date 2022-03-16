package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_2210 {
	//숫자판 점프
//	연속된, 연결된 어떤 덩어리, 거리가 1인 무언가 탐색은 bfs가 사용 가능하다.
//	같은좌표중복을 허용 => visited 불필요
//	완성된 문자=>길이가 6이 될때 중복을 불필요
//	hash? 중복제거
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static String[][] map = new String[5][5];
	static int[] dy= {-1,1,0,0};//상하좌우
	static int[] dx= {0,0,-1,1};
	static HashSet<String> ans = new HashSet<>();
	static class AnsString{
		int y,x;
		String str;
		public AnsString(int y, int x, String str) {
			super();
			this.y = y;
			this.x = x;
			this.str = str;
		}
		
	}
	public static void main(String[] args) throws IOException {
		for(int y=0; y<5; y++) map[y]=in.readLine().split(" "); //map이 스트링배열
		for(int y=0; y<5; y++) {
			for(int x=0; x<5; x++) {
				bfs(new AnsString(y,x,map[y][x]));
			}
		}
		System.out.println(ans.size());
	}
	private static void bfs(AnsString start) {
		Queue<AnsString> q = new LinkedList<>();
		q.offer(start);
		while(!q.isEmpty()) {
			AnsString current = q.poll();
			if(current.str.length()==6) {
				ans.add(current.str);
				continue;
			}
			for(int d=0; d<4; d++) {
				int ny=current.y+dy[d];
				int nx=current.x+dx[d];
				if(ny<0 || nx<0 || ny>=5 || nx>=5) continue;
				q.offer(new AnsString(ny, nx, current.str+map[ny][nx]));
			}
		}
	}

}
