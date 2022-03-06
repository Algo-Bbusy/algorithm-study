package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_18352 {
	//특정 거리의 도시 찾기
//	최단거리 탐색? -> 다익스트라? bfs?
//	최단 거리가 K인 모든 도시들의 번호가 필요하다.
//	입력받은 도시의 연결 정보를 이중리스트로 연결된 그래프에 담는다.
//	모든 도시의 거리를 -1로 초기화.
//	출발 도시 거리는 0으로 초기화.
//	큐에 시작도시를 넣고 bfs 실행.
//	큐가 빌때까지 해당 도시에 연결된 도시에 방문한 적이 없으면 해당 도시에 현재 도시의 거리에서 +1
//	다시 큐에 넣는다.
//	거리배열에서 k값과 같은 도시를 오름차순으로 출력.
//	만약에 존재하지 않으면 -1출력.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int V,E,K,start;
	static ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
	static int[] distance = new int[300001];
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		V=Integer.parseInt(stk.nextToken());
		E=Integer.parseInt(stk.nextToken());
		K=Integer.parseInt(stk.nextToken());
		start=Integer.parseInt(stk.nextToken());
		
		for(int i=0; i<=V; i++) {
			map.add(new ArrayList<Integer>());
			distance[i]=-1;
		}
		for(int i=0; i<E; i++) {
			stk=new StringTokenizer(in.readLine());
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			map.get(from).add(to);
		}
		distance[start]=0;
		bfs();
		boolean check = false;
		for(int i=1; i<=V; i++) {
			if(distance[i]==K) {
				System.out.println(i);
				check=true;
			}
		}
		if(!check) {
			System.out.println(-1);
		}
	}
	private static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);
		while(!q.isEmpty()) {
			int current = q.poll();
			for(int i=0; i<map.get(current).size(); i++) {
				int next = map.get(current).get(i);
				if(distance[next]==-1) {
					distance[next]=distance[current]+1;
					q.offer(next);
				}
			}
		}
	}

}
