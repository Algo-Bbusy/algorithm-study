package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_2508 {
	// 사탕박사
//	2차원 배열에 입력된 문자들을 >o<의 경우엔 행열 탐색으로
//	v
//	o
//	^
//	은 열행 탐색으로 각각 완전탐색을 돌리면서 해당 문자들이 나란히 있는 경우에만
//	정답 카운트를 증가시켜 준다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static char[][] box;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(in.readLine());
		for(int tc=1; tc<=T; tc++) {
			in.readLine();
			stk = new StringTokenizer(in.readLine());
			int R = Integer.parseInt(stk.nextToken());
			int C = Integer.parseInt(stk.nextToken());
			box = new char[R][C];
			ans=0;
			for(int y=0; y<R; y++) {
				String st = in.readLine();
				for(int x=0; x<C; x++) {
					box[y][x]=st.charAt(x);
				}
			}
			for(int y=0; y<R; y++) {
				for(int x=0; x<C-2; x++) {
					if(box[y][x]=='>' && box[y][x+1]=='o'&&box[y][x+2]=='<') ans++;
				}
			}
			for(int y=0; y<R-2; y++) {
				for(int x=0; x<C; x++) {
					if(box[y][x]=='v' && box[y+1][x]=='o'&&box[y+2][x]=='^') ans++;
				}
			}
			sb.append(ans).append("\n");
		}
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
}
