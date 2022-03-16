package sh1n.bj.ps2491수열;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2491수열/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int asc = 1;
		int desc = 1;
		int max = 1;
		for(int i=0; i<N-1; i++) {
			if (arr[i] <= arr[i+1]) {
				asc++;
				if (asc > max) {
					max = asc;
				}
			} else {
				asc = 1;
			}
			
			if (arr[i] >= arr[i+1]) {
				desc++;
				if (desc > max) {
					max = desc;
				}
			} else {
				desc = 1;
			}
		}
		System.out.println(max);
	}
}
