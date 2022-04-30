package com.ssafy.study.homework23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1922_네트워크연결 {
	
	static int N, M;
	static int[][] arr;
	static int[] parent; //유니온 파인드 전용 부모배열 생성.
	static int minCost;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine()); //컴퓨터의 수
		M = Integer.parseInt(br.readLine()); //연결가능 한 수
		minCost = 0; //최소비용
		
		parent = new int[N+1];
		arr = new int[M][3]; 
		
		
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken()); //비용
		}
		
		// 첫번째를 cost순으로 오름차순 정렬
		Arrays.sort(arr, Comparator.comparingInt(o->o[2]));
		
		// makeSet 
		for(int i=1; i<=N; i++) {
			parent[i]=i;
		}
		
		//낮은 비용 부터 크루스칼 알고리즘 진행
		for(int i=0; i<M; i++) {
			if(find(arr[i][0]-1) != find(arr[i][1]-1)) {
				union(arr[i][0] -1, arr[i][1]-1);
				minCost += arr[i][2];
				continue;				
			}
		}		
		System.out.println(minCost);
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}

	private static int find(int x) {
		if (parent[x] == x)
			return x;
		else
			return find(parent[x]);
	}
}