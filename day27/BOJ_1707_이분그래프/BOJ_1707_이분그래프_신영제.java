package com.ssafy.study.homework27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1707_이분그래프_신영제 {

	static int N, M;
	static List<Integer>[] list;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int K = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= K; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			visited = new int[N + 1];
			list = new ArrayList[N + 1];
			
			for (int i = 1; i < N + 1; i++) {
				list[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				list[a].add(b);
				list[b].add(a);
			}

			BFS();
		}

	}

	static void BFS() {
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 1; i < N + 1; i++) {
			
			// 0이면 1을 색칠
			if (visited[i] == 0) {
				visited[i] = 1;
				q.add(i);
			}
			
			while (!q.isEmpty()) {
				int poll = q.poll();
				
			
				for (int next : list[poll]) {
					//방문한곳의 위치와 연결된 정점 숫자가 같다면 이분 그래프가 아님.
					if (visited[next] == visited[poll]) {
						System.out.println("NO");
						return;
					}
					
					
					if (visited[next] == 0) q.add(next);				
					
					// 전이 1이면 다음에는 2를 넣어 이분그래프 체크. 
					if (visited[poll] == 1) visited[next] = 2;
					else visited[next] = 1;

					
				}
			}
		}

		System.out.println("YES");
	}
}