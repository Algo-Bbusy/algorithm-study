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

public class Java_1707 {
	// 이분 그래프
	//메모리 272356KB 시간 952ms
	//이분 그래프가 뭘 의미하는건지 문제지문만으로는 이해가 안되서 구글링을 하였습니다.
	//이분 그래프란 각 정점을 두가지 색(그룹)으로 나누었을때 인접한 두 정점(노드)의 색(그룹)이 다른 그래프를 말합니다.
	//Solution 1.
	//1. 두 개의 그룹을 만든다. int배열로 1과 -1로 구분.
	//2. 그래프의 인접요소를 bfs탐색을 통해 차례로 방문하면서 해당정점이 한번도 색칠(그룹화)되지 않을경우 현재정점과 다른색(그룹)으로 칠한다.
	//3. 이미 그룹화 되어있는 정점(노드)라면 현재 정점과 비교한다.
	//4. 그룹이 같다면 빠져나오면서 NO를 출력한다.
	//5. 모든 탐색이 종료될때까지 NO가 출력되지 않았다면 YES를 출력한다.
	//풀이시간 52분 14초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static String ans;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] group;
	static int K,V,E,A,B;
	public static void main(String[] args) throws NumberFormatException, IOException {
		K = Integer.parseInt(in.readLine());
		
		while(K-->0) {
			ans="YES";
			stk = new StringTokenizer(in.readLine());
			V = Integer.parseInt(stk.nextToken());
			E = Integer.parseInt(stk.nextToken());
			
			graph = new ArrayList<>();
			
			for(int i=0; i<V; i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i=0; i<E; i++) {
				stk = new StringTokenizer(in.readLine());
				A = Integer.parseInt(stk.nextToken())-1;
				B = Integer.parseInt(stk.nextToken())-1;
				
				graph.get(A).add(B);
				graph.get(B).add(A);				
			}
			
			group = new int[V];
			for(int i=0; i<V; i++) {
				if(group[i]==0) {
					if(!bfs(i)) break;
				}
			}
			sb.append(ans).append("\n");
		}
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static boolean bfs(int n) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(n);
		group[n]=1;
		
		while(!q.isEmpty()) {
			int node=q.poll();
			for(int i : graph.get(node)) {
				if(group[node]==group[i]) {
					ans="NO";
					return false;
				}
				if(group[i]==0) {
					group[i]=group[node]*-1;
					q.add(i);
				}
			}
		}
		return true;
	}

}
