package sh1n.bj.ps10158개미;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps10158개미/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int antC = Integer.parseInt(st.nextToken()); 
		int antR = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(br.readLine());
		
		int drt = (T + antR) % (2 * R);
		antR = (drt/R == 0) ? drt : R - (drt%R);
		sb.append(antR).append(" ");
		
		int dct = (T + antC) % (2 * C);
		antC = (dct/C == 0) ? dct : C - (dct%C);
		sb.append(antC);
		
		System.out.print(sb.toString());
		
	} // end main()
}
