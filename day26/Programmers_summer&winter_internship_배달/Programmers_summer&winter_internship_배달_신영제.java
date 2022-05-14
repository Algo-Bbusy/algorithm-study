package com.ssafy.study.homework26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Pro_배달 {

	static class Node implements Comparable<Node> {
		int no;
		int cost;

		public Node(int no, int cost) {
			this.no = no;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static ArrayList<ArrayList<Node>> list;
	static PriorityQueue<Node> pq;
	static int[] distance;
	static boolean[] visited;
	static int answer;

	public static void main(String[] args) {
		int N = 6;
		int K = 4;
		int[][] road = { { 1, 2, 1 }, { 1, 3, 2 }, { 2, 3, 2 }, { 3, 4, 3 }, { 3, 5, 2 }, { 3, 5, 3 }, { 5, 6, 1 } };

		System.out.println(solution(N, road, K));
	}

	public static int solution(int N, int[][] road, int K) {
		answer = 0;
		distance = new int[N + 1];
		list = new ArrayList<>();
		pq = new PriorityQueue<>();

		// 마을의 개수만큼 리스트 생성.
		for (int i = 0; i < N + 1; i++) {
			list.add(new ArrayList<Node>());
		}
		Arrays.fill(distance, Integer.MAX_VALUE); // 최대경로로 초기화.

		for (int[] ro : road) {
			list.get(ro[0]).add(new Node(ro[1], ro[2]));
			list.get(ro[1]).add(new Node(ro[0], ro[2]));
		}

		distance[1] = 0; // 시작점은 0으로 초기화.
		pq.offer(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			for (Node nNode : list.get(node.no)) {
				if (distance[nNode.no] > distance[node.no] + nNode.cost) {
					distance[nNode.no] = distance[node.no] + nNode.cost;
					pq.offer(nNode);
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (distance[i] <= K) {
				answer++;
			}
		}

		return answer;
	}

}
