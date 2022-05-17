import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 서로 인접한 정점은 다른 그룹에 무조건 다른 그룹에 속해있어야 한다는 조건을 기반으로 접근
// 간선을 바탕으로 adjMatrix 생성
// 임의의 정점으로 부터 BFS 탐색을 시작하며 인접한 노드를 서로 다른 그룹에 지정(한 번의 depth마다 그룹 토글)
// 모든 탐색을 끝냈을 때, 각 그룹의 정점들이 서로 인접하지 않으면 YES, 1개라도 인접하면 NO

public class Main {
	
	static int V, E;
	
	static class Vertex {
		int to;
		Vertex link;
		
		public Vertex(int to, Vertex link) {
			super();
			this.to = to;
			this.link = link;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			// 인접 리스트 초기화
			Vertex[] adjList = new Vertex[V + 1];
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				//adjMatrix[from][to] = adjMatrix[to][from] = 1;
				adjList[from] = new Vertex(to, adjList[from]);
				adjList[to] = new Vertex(from, adjList[to]);
			}
			
			// 그룹 초기화
			List<Integer>[] groups = new List[2];
			for(int i = 0; i < 2; i++) {
				groups[i] = new ArrayList<Integer>();
			}
			
			divGroupByBFS(adjList, groups);
			
			boolean isValid = true;
			for(int i = 0; i <= 1; i++) {
				if(!isValidGroup(groups[i], adjList)) {
					isValid = false;
					break;
				}
			}
			
			if(isValid) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}			
		}
		
	}// end main

	private static boolean isValidGroup(List<Integer> group, Vertex[] adjList) {
		int size = group.size();
		
		for(int i = 0; i < size; i++) {
			int from = group.get(i);
			for(int j = i + 1; j < size; j++) {
				int to = group.get(j);
				
				for(Vertex v = adjList[from]; v != null; v = v.link) {
					if(v.to == to) {
						return false;
					}
				}
			}
		}
		
		return true;
	}

	private static void divGroupByBFS(Vertex[] adjList, List<Integer>[] groups) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[V + 1];
		q.offer(1);
		visited[1] = true;
		groups[0].add(1);
		
		boolean toggleKey = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			
			while(size-- > 0) {
				int current = q.poll();
				cnt++;
				
				for(Vertex v = adjList[current]; v != null; v = v.link) {
					if(visited[v.to]) continue;
					
					addGroup(v.to, toggleKey, groups);
					q.offer(v.to);
					visited[v.to] = true;
				}
			}
			
			toggleKey = !toggleKey;
			
			if(q.isEmpty() && cnt != V) {
				for(int i = 1; i <= V; i++) {
					if(!visited[i]) {
						visited[i] = true;
						q.offer(i);
						break;
					}
				}
			}
		}
	}

	private static void addGroup(int i, boolean toggleKey, List<Integer>[] groups) {
		if(toggleKey) {
			groups[1].add(i);
		} else {
			groups[0].add(i);
		}
	}

}
