package sh1n.bj.ps2628종이자르기;

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
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2628종이자르기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());

		List<Integer> rList = new ArrayList<>();
		List<Integer> cList = new ArrayList<>();
		rList.add(0);
		rList.add(R);
		cList.add(0);
		cList.add(C);
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			
			if(dir == 0) {
				rList.add(idx);
			} else {
				cList.add(idx);
			}
		}
		
		Collections.sort(rList);
		Collections.sort(cList);
		
		int maxR = rList.get(0);
		for(int i=0; i<rList.size()-1; i++) {
			if((rList.get(i+1) - rList.get(i)) > maxR) {
				maxR = rList.get(i+1) - rList.get(i);
			}
		}
		
		int maxC = cList.get(0);
		for(int i=0; i<cList.size()-1; i++) {
			if((cList.get(i+1) - cList.get(i)) > maxC) {
				maxC = cList.get(i+1) - cList.get(i);
			}
		}
		
		System.out.println(maxR * maxC);
	} // end main()
}
