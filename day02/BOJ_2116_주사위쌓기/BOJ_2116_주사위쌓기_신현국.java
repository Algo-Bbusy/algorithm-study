package sh1n.bj.ps2116주사위쌓기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] dice;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2116주사위쌓기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dice = new int[N][6];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
		for(int i=0; i<6; i++) {		
			maxHeap.offer(getMaxDFS(dice[0][i], 0));
		}
		
		System.out.println(maxHeap.poll());
	}
	
	public static int getMaxDFS(int number, int cnt) {
		int maxDiceNumber = 0;
		int pairNumber = getPairNumber(dice[cnt], number);
		
		for(int i=0; i<6; i++) {
			if(dice[cnt][i] == number || dice[cnt][i] == pairNumber) {
				continue;
			}
			if(dice[cnt][i] > maxDiceNumber) {
				maxDiceNumber = dice[cnt][i];
			}
		}
		
		if(cnt == N-1) {
			return maxDiceNumber;
		} else {
			return maxDiceNumber + getMaxDFS(pairNumber, cnt+1);
		}
	}
	
	public static int getPairNumber(int[] dice, int number) {
		int idx=0;
		for(int i=0; i<dice.length; i++) {
			if(dice[i] == number) idx = i; 
		}
		
		switch(idx) {
		case 0:
			idx = 5;
			break;
		case 1:
			idx = 3;
			break;
		case 2:
			idx = 4;
			break;
		case 3: 
			idx = 1;
			break;
		case 4:
			idx = 2;
			break;
		case 5:
			idx = 0;
			break;
		}
		
		return dice[idx];
	}
}
