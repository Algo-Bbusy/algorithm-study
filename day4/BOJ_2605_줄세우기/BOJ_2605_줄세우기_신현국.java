package sh1n.bj.ps2605줄세우기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2605줄세우기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int studentN = Integer.parseInt(br.readLine());
		List<Integer> waitingInfo = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<studentN; i++) {
			int number = Integer.parseInt(st.nextToken());
			waitingInfo.add(i-number, i+1);
		}
		
		for(int waiting : waitingInfo) {
			System.out.print(waiting + " ");
		}
	}
}
