package com.ssafy.study.homework22;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12851_숨바꼭질_신영제 {
	 static int N, K;
	   static int min;
	   static int cnt;
	   static int SIZE = 100001;
	   static boolean[] visited;

	   public static void main(String[] args) throws IOException {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringTokenizer st = null;

	      st = new StringTokenizer(br.readLine());

	      N = Integer.parseInt(st.nextToken());
	      K = Integer.parseInt(st.nextToken());

	      min = Integer.MAX_VALUE;
	      cnt = 1;
	      visited = new boolean[SIZE];

	      BFS();
	      System.out.println(min);
	      System.out.println(cnt);

	   }

	   private static void BFS() {
	      Queue<Point> q = new LinkedList<>();
	      visited[N] = true;
	      int time = 0;
	      q.offer(new Point(N, time)); // 수빈이 위치와 0초에서 시작.

	      while (!q.isEmpty()) {
	         Point p = q.poll();
	         int pollN = p.x;
	         int pollTime = p.y;
	         visited[pollN] = true;

	         // 수빈이 위치
	         int[] arr = new int[4];
	         arr[0] = pollN - 1;
	         arr[1] = pollN + 1;
	         arr[2] = pollN * 2;
	         arr[3] = pollTime + 1; // 1초씩 증가.

	         if (pollN == K) { // 수빈이가 동생의 위치에 도착 했을때
	            if (min > pollTime)   min = pollTime;  // 시간이 더 작다면 갱신 
	            else if (min == pollTime) cnt++; // 같은 시간으로 갈수있는 다른경로 
	         } else { // 1초씩 늘려가면서 Q에 담습니다.
	            for (int i = 0; i < 3; ++i) {
	               if (arr[i] >= 0 && arr[i] < SIZE && !visited[arr[i]]) {
	                  q.offer(new Point(arr[i], arr[3]));            
	               }                  
	            }
	         }
	      }
	   }

	}