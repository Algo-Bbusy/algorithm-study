package sh1n.bj.ps2578빙고;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] bingoMap = new int[5][5];
	static int[] rCheck = new int[5];
	static int[] cCheck = new int[5];
	static int[] dCheck = new int[2];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2578빙고/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				bingoMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				int number = Integer.parseInt(st.nextToken());
				if(isBingo(number)) {
					System.out.println(i*5+j+1);
					System.exit(0);
				}
			}
		}
		
	} // end main()
	
	public static boolean isBingo(int number) {
		int bingoCnt = 0;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(bingoMap[i][j] == number) {
					rCheck[i]++;
					cCheck[j]++;
					
					if(i == 2 && j == 2) {
						dCheck[0]++;
						dCheck[1]++;
					} else if(i==j) {
						dCheck[0]++;
					} else if(j == (4-i)) {
						dCheck[1]++;
					}
					
					
				}
			}
		}
		
		for(int i=0; i<5; i++) {
			if(rCheck[i] == 5) {
				bingoCnt++;
			}
			if(cCheck[i] == 5) {
				bingoCnt++;
			}
		}
		
		for(int i=0; i<2; i++) {
			if(dCheck[i] == 5) {
				bingoCnt++;
			}
		}
		
		if(bingoCnt>=3) {
			return true;
		} else {
			return false;
		}
		
	}
}
