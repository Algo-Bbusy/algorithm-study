package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Java_1922 {
	// 네트워크 연결
	//메모리 46775KB 시간 488ms
	// 가중치가 존재하며 모든 간선을 최소 비용으로 연결 해야할 때 적용할 알고리즘은 MST가 있다.
	// 또한 a b 정점과 b c 정점이 연결 상태라면 a c 가 연결 상태가 된다는 것은
	// b의 최상위 부모가 a이고 b와 c가 연결 상태라면 c의 부모는 a로 바꾸는 식으로 연결상태를 표현
	// 유니온파인드를 응용할 수 있는 문제다.
	//Solution 1.
	//1. 모든 정점을 최소비용으로 연결
	//2. 크루스칼 알고리즘 적용
	//3. 가중치를 기준으로 오름차순 정렬 (우선순위 큐 적용)
	//4. 가중치가 가장 낮은 두 정점(가장 먼저 빠져 나온 큐) 선택
	//5. 두 정점의 부모가 같은지 다른지 판별 후 다르면 같은 부모로 결합(union-find)
	//6. union-find가 사용될때마다 해당 간선을 선택했다는 의미로 해당 간선의 가중치를 증가시킨다.
	//7. 모든 큐가 빌 경우 나오게 된 가중치의 합이 최소비용이 된다(우선순위 큐로 오름차순을 하였기때문)
	//풀이시간 21분 1초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N, M, ans;
	static PriorityQueue<Edge> tree;
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight-o.weight;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		MST();
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static void MST() {
		int[] parent = new int[N+1];
		
		for(int i=1; i<=N; i++) parent[i]=i;
		
		while(!tree.isEmpty()) {
			Edge current = tree.poll();
			
			int parentFrom = find(parent, current.from);
			int parentTo = find(parent, current.to);
			if(parentFrom != parentTo) {
				parent[parentTo] = parentFrom;
				
				ans += current.weight;				
			}
		}
	}
	private static int find(int[] parent, int x) {
		if(parent[x]==x) return x;
		return parent[x] = find(parent, parent[x]);
	}
	private static void input() throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		tree = new PriorityQueue<>();
		while(M--> 0) {
			stk = new StringTokenizer(in.readLine());
			
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			int weight = Integer.parseInt(stk.nextToken());
			
			tree.add(new Edge(from, to, weight));
		}
	}

}
