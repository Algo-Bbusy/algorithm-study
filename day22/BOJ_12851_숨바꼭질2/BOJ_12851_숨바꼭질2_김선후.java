package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_12851 {
	// 숨바꼭질 2
	//메모리 67580KB 시간 232ms
	//Solution 1.
	//1. 최단경로(최단시간)? => bfs
	//2. 현재좌표와 현재시간을 제어할 수 있는 Node 클래스 선언
	//3. 해당 좌표의 방문처리를 방문한 시간으로서 체크할 수 있는 isVisitedByTime 1차원 int 배열 선언
	//4. 해당 좌표에 방문할 시 동일한 시간으로 방문하는 경우는 큐에 넣는다. 그보다 더 늦은 시간에 도착하는 경우는 제외
	//5. 도착한 시간을 비교해서 최단시간이면 caseCnt 증가해주며 큐가 빌때까지 수행해주면 된다.
	//풀이시간 42분 16초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N, K, ans, caseCnt;
	static int[] isVisitedByTime;
	static class Node{
		int x, time;

		public Node(int x, int time) {
			super();
			this.x = x;
			this.time = time;
		}
		
	}
	public static void main(String[] args) throws IOException {
		input();
		bfs();
		sb.append(ans).append("\n").append(caseCnt);
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static void bfs() {
		isVisitedByTime = new int[100001];
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(N,0));
		isVisitedByTime[N] = 1;
		while(!q.isEmpty()) {
			Node current = q.poll();
			if(current.x==K) {
				if(caseCnt==0) ans = current.time; //최단 시간 입력
				if(ans==current.time) caseCnt++; //경우 수 증가
				continue;
			}
			int[] dx = {current.x-1, current.x+1, current.x*2};
			for(int d=0; d<3; d++) {
				int nx = dx[d];
				if(nx<0 || nx>=100001) continue;
				if(isVisitedByTime[nx] == 0 || isVisitedByTime[nx] == current.time+1) {
					isVisitedByTime[nx] = current.time+1;
					q.add(new Node(nx, current.time+1));
				}
			}
		}
	}
	private static void input() throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
	}

}
