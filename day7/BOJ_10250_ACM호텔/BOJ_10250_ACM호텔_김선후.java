package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_10250 {
	// ACM 호텔
//	행열이 아닌 열행을 우선으로 방을 채워나간다.
//	손님의 번호를 열 H로 나눈몫에 1을 더하면 호수를 구할 수 있다.
//	층수는 손님의 번호를 열 H로 %연산해준다.
//	단 이때 N%H의 나머지가 0이면 H층 N?H호에 머문다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(in.readLine());
		for(int tc=1; tc<=T; tc++) {
			stk = new StringTokenizer(in.readLine());
			int H = Integer.parseInt(stk.nextToken());
			int W = Integer.parseInt(stk.nextToken());
			int N = Integer.parseInt(stk.nextToken());
			
			int y = N%H;
			int x = (N/H)+1;
			if(N%H==0) { //손님의 번호가 열과 같을때
				y=H;
				x=N/H;
			}
			int ans = y*100+x;
			sb.append(ans).append("\n");
		}
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}

}
