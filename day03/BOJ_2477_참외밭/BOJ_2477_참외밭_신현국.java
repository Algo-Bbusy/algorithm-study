package sh1n.bj.ps2477참외밭;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2477참외밭/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Queue<int[]> queue = new LinkedList<int[]>();
		for(int i=0; i<6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			queue.offer(new int[] {idx, value});
		}
		
		int blankMap = 0;
		while(true) {
			int[] current = queue.poll();
			int[] next = queue.peek();
			queue.offer(current);
			if(!isPair(current[0], next[0])) {
				blankMap = current[1] * next[1];
				break;
			}
		}
		
		int x = 0;
		int y = 0;
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			if(temp[0] == 4 || temp[0] == 3) {
				if(temp[1] > x) x = temp[1];
			} else if (temp[0] == 2 || temp[0] == 1) {
				if(temp[1] > y) y = temp[1];
			}
		}
		
		int answer = ((x * y) - blankMap)*N;
		System.out.println(answer);
	}
	
	public static boolean isPair(int dir, int targetDir) {
		switch(dir) {
		case 1:
			dir = 4;
			break;
		case 2:
			dir = 3;
			break;
		case 3:
			dir = 1;
			break;
		case 4:
			dir = 2;
			break;
		}
		
		if(dir == targetDir) {
			return true;
		} else {
			return false;
		}
	}
}
