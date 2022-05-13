package summer_winter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Programmers_summer_winter_internship_배달 {
	//배달
	//1번 마을에서 K이하의 시간으로 배달을 갈 수 있는 마을의 개수를 구하는 문제.
	//1번 마을에서 각 마을간의 최단경로(최단시간)을 구해서 K시간 이하가 몇 개 있는지를 구하는 문제.
	//Solution 1.
	//1. 간선리스트와 최단경로배열 초기화.
	//2. 최단경로배열이 갱신되지 않았을 때 무한대로 초기화.
	//3. 간선리스트에 간선 정보 갱신
	//4. 1번도시의 최단경로비용을 0으로 갱신(자기 자신으로 돌아오는 최단 경로는 0이므로)
	//5. 다익스트라 알고리즘
	//6. 1번마을에서 K이하 비용인 도시 개수만큼 정답수 증가 후 반환.
	//풀이시간 52분 48초
	private static class Edge implements Comparable<Edge>{
		int number; //정점번호
		int weight; //배달주문시간(가중치)
		public Edge(int number, int weight) {
			super();
			this.number = number;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return weight - o.weight;
		}
		
	}
	private static ArrayList<Edge>[] edgeList;
	private int[] distance; //1번 도시와 다른 도시간 최단 경로
	
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        //초기화 과정
        edgeList = new ArrayList[N+1];
        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        for(int i=1; i<=N; i++) edgeList[i] = new ArrayList<>();
        for(int i=0; i<road.length; i++) {
        	//from to , to from 간선 갱신
        	edgeList[road[i][0]].add(new Edge(road[i][1], road[i][2]));
        	edgeList[road[i][1]].add(new Edge(road[i][0], road[i][2]));
        }
        //1번 도시 - 자기 자신 최단 경로 0
        distance[1]=0;
        dijkstra();
        for(int time : distance) {
        	if(time<=K) answer++;
        }
        return answer;
    }

	private void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1,0));
		//꺼낸 간선은 현재 최단경로비용을 갖는 간선이다. 
		//즉 해당 간선의 최단경로비용이 현재 정점번호의 최단경로 배열에 기록된 내용보다 크다면 스킵한다.
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int number = edge.number;
			int weight = edge.weight;
			if(distance[number]<weight) continue;
			for(int i=0; i<edgeList[number].size(); i++) {
				int number2 = edgeList[number].get(i).number;
				int weight2 = edgeList[number].get(i).weight+weight;
				if(distance[number2]>weight2) {
					distance[number2] = weight2;
					pq.add(new Edge(number2, weight2));
				}
			}
		}
	}
	
}
