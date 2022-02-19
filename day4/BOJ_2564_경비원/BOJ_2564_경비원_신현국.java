package sh1n.bj.ps2564경비원;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2564경비원T/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int marketN = Integer.parseInt(br.readLine());
		
		List<Integer> positionInfo = new ArrayList<Integer>();
		
		for(int i=0; i<marketN+1; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int position = Integer.parseInt(st.nextToken());
			
			switch(dir) {
			case 1:
				positionInfo.add(position);
				break;
			case 2:
				positionInfo.add(C + R + (C - position));
				break;
			case 3:
				positionInfo.add(C + R + C + (R - position));
				break;
			case 4:
				positionInfo.add(C + position);
				break;
			}
		}
		
		int result = 0;
		for(int i=0; i<marketN; i++) {
			int disCase1 = Math.abs(positionInfo.get(positionInfo.size()-1) - positionInfo.get(i));
			int disCase2 = (2*R) + (2*C) - disCase1; 
			result += Math.min(disCase1, disCase2);
		}
		
		System.out.println(result);
	} // end main()
}
