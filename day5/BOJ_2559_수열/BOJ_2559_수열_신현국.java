package sh1n.bj.ps2559수열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2559수열/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] Temperature = new int[N];
		int sum = 0;
		for(int i=0; i<N; i++) {
			Temperature[i] = Integer.parseInt(st.nextToken());
			if(i<K) {
				sum += Temperature[i];
			}
		}
		
		int max = sum;
		
		for(int i=0; i<N-K; i++) {
			sum -= Temperature[i];
			sum += Temperature[i+K];
			if(sum > max) {
				max = sum;
			}
		}
		
		System.out.println(max);
	}
}
