package sh1n.bj.ps2210숫자판점프;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2210숫자판점프/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int[][] numberMap = new int[5][5];
		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<5; j++) {
				numberMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<String> numberList = new ArrayList<>();
		StringBuilder numbers = new StringBuilder();
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				numbers.append(numberMap[i][j]);
				searchDFS( i, j, numberMap, numberList, numbers, 1);
				numbers.setLength(numbers.length()-1);
			}
		}
		
		System.out.println(numberList.size());
	}// end main
	
	public static void searchDFS(int r, int c, int[][] numberMap, List<String> numberList, StringBuilder numbers, int step) {
		if(step == 6) {
			String number = numbers.toString();
			if(!numberList.contains(number)) {
				numberList.add(number);
			}
			return;
		}
		
		int nr, nc;
		for(int i=0; i<4; i++) {
			try {
				nr = r + dr[i];
				nc = c + dc[i];
				numbers.append(numberMap[nr][nc]);
				searchDFS(nr, nc, numberMap, numberList, numbers, step+1);
				numbers.setLength(step);
			} catch(ArrayIndexOutOfBoundsException e) {};
		}
	}
}
