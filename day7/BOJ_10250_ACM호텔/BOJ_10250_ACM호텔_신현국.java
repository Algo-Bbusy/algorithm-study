package sh1n.bj.ps10250ACM호텔;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps10250ACM호텔/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			int roomH;
			int roomW;
			if(N % H == 0) {
				roomH = H;
				roomW = N/H;
			} else {
				roomH = N % H;
				roomW = N/H + 1;
			}
			
			sb.append(roomH).append(String.format("%02d", roomW)).append("\n");
		}
		System.out.println(sb.toString());
	} // end main()
}
