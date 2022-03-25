package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_1167 {
	// 트리의 지름
//	트리와 bfs dfs 가중치와 관련해서 기억이 흐릿해져서 예전에 라이브 시간때 배웠던 내용들을 복습하는 시간을 가졌습니다.
//	트리에 존재하는 모든 경로 중 가장 긴것의 길이가 트리의 지름
//	Solution 1.
//	1. 각 정점에 대한 인접리스트와 간선가중치를 저장하는 배열을 만든다.
//	2. dfs 탐색을 수행하여 각 정점에 해당하는 가중치 값들을 계속 더해가며 모든 정점에서의 거리의 최대값을 구한다.
//	실패 TLE(시간초과) 모든 정점에서 전부 dfs수행이 일어나게 되면서 시간초과가 일어난다.
//	Solution 2.
//	1. 가장 긴 지름을 만드는 정점 A와 정점 B가 있다고 가정했을 때, 임의의 노드 1개(여기선 루트정점)에서 가장 먼 정점은 정점 A거나 B다.
//	2. dfs나 bfs를 수행해서 루트로부터 가장 긴 거리를 지닌 정점 A혹은 B를 찾아낸다.
//	3. 해당 정점을 기준으로 다시 bfs/dfs를 수행하면 나머지 정점 한개를 구할 수 있다.
//	메모리 : 98516KB 시간 : 996ms
//	풀이시간 2시간 14분 13초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N;
	static class Edge{
		int to, weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		List<Edge>[] tree = new LinkedList[N+1];
		int[] dist = new int[N+1];
		for(int i=0; i<=N; i++) tree[i] = new LinkedList<Edge>();
		
		for(int v=0; v<N; v++) {
			stk=new StringTokenizer(in.readLine());
			int from = Integer.parseInt(stk.nextToken());
			while(true) {
				int to = Integer.parseInt(stk.nextToken());
				if(to==-1) break;
				int weight = Integer.parseInt(stk.nextToken());
				tree[from].add(new Edge(to, weight));
			}
		}
		dist = bfs(tree,1);
		int start=1;
		for(int i=2; i<=N; i++) {
			if(dist[start]<dist[i]) start=i;
		}
		dist = bfs(tree, start);
		Arrays.sort(dist);
		out.write(dist[N]+"");
		out.flush();
		out.close();
		in.close();
	}
	private static int[] bfs(List<Edge>[] tree, int start) {
		boolean[] isVisited = new boolean[N+1];
		int[] dist = new int[N+1];
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		isVisited[start]=true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			for(Edge e : tree[v]) {
				int to = e.to;
				int weight = e.weight;
				if(!isVisited[to]) {
					isVisited[to]=true;
					q.add(to);
					dist[to]=dist[v]+weight;
				}
			}
		}
		return dist;
	}

}
