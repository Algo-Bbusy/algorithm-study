package sh1n.bj2.ps2110공유기설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] housePos = new int[N];
		for(int i = 0; i < N; i++) {
			housePos[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(housePos);
		
		int minDis = 1;
		int maxDis = 1000000000;
		while(minDis < maxDis - 1) {
			int targetDis = (minDis + maxDis) / 2;
			
			int hubCnt = 1;
			int compareTarget = housePos[0];
			for(int i = 1; i < N; i++) {
				if(housePos[i] - compareTarget >= targetDis) {
					hubCnt++;
					compareTarget = housePos[i];
				}
			}
			
			if(hubCnt < C) {
				maxDis = targetDis;
			} else {
				minDis = targetDis;
			}
		}
		
		System.out.println(minDis);
	}// end main
}
