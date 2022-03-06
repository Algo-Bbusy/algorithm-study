package sh1n.bj.ps18352특정거리도시찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Road {
		int city;
		Road link;
		
		public Road(int city, Road link) {
			super();
			this.city = city;
			this.link = link;
		}
		
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps18352특정거리도시찾기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		Road[] roads = new Road[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			roads[from] = new Road(to, roads[from]);
		}
		
		int[] minRoad = new int[N+1];
		Arrays.fill(minRoad, Integer.MAX_VALUE);
		minRoad[X] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((weight1, weight2) -> weight1[1]-weight2[1]);
		pq.offer(new int[] {X, minRoad[X]});
		boolean[] visitedCity = new boolean[N+1];
		while(!pq.isEmpty()) {
			int[] currentCity = pq.poll();
			if(visitedCity[currentCity[0]]) continue;
			visitedCity[currentCity[0]] = true;
			
			for(Road temp = roads[currentCity[0]]; temp != null; temp = temp.link) {
				if(!visitedCity[temp.city] && minRoad[temp.city] > minRoad[currentCity[0]] + 1) {
					minRoad[temp.city] = minRoad[currentCity[0]] + 1;
					pq.offer(new int[] {temp.city, minRoad[temp.city]});
				}
			}
		}
		
		boolean hasMinRoad = false;
		for(int i=1; i<minRoad.length; i++) {
			if(minRoad[i] == K) {
				System.out.println(i);
				hasMinRoad = true;
			}
		}
		
		if(!hasMinRoad) {
			System.out.println(-1);
		}
	}// end main
}
