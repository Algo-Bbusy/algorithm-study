package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_14696 {
	//딱지놀이
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(in.readLine());
		round: for (int tc = 1; tc <= T; tc++) {
			//딱지의 갯수 저장
			int[] cardA = new int[101];
			int[] cardB = new int[101];
			//a의 딱지
			stk = new StringTokenizer(in.readLine());
			int temp = Integer.parseInt(stk.nextToken());
			for (int j = 0; j < temp; j++) {
				cardA[Integer.parseInt(stk.nextToken())]++;
			}
			//b의 딱지
			stk = new StringTokenizer(in.readLine());
			temp = Integer.parseInt(stk.nextToken());
			for (int j = 0; j < temp; j++) {
				cardB[Integer.parseInt(stk.nextToken())]++;
			}
			//뒤에서 부터 딱지갯수 체크
			for (int k = 100; k > 0; k--) {
				if (cardA[k] > cardB[k]) {
					sb.append("A").append("\n");
					continue round;
				} else if (cardA[k] < cardB[k]) {
					sb.append("B").append("\n");
					continue round;
				}
			}
			//무승부
			sb.append("D").append("\n");
		}
		out.write(sb.toString());
		in.close();
		out.flush();
		out.close();
	}
}
