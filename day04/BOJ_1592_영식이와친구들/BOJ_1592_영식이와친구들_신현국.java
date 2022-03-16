package sh1n.bj.ps1592영식이;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps1592영식이/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] players = new int[N];
		int cnt = 0;
		int i = 0;
		while(true) {
			if(++players[i] == M) {
				break;
			}
			
			cnt++;
			
			if(players[i] % 2 == 0) {
				i = (i+L) % N;
			} else {
				if(i-L < 0) {
					i = i - L + N;
				} else {
					i = i - L;
				}
			}
		}
		
		System.out.println(cnt);
	}// end main
}
