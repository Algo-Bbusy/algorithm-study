package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_12904 {
	// A와B
	//solution 1. dfs를 사용하여 S가 T의 길이를 만족할때까지의 모든 조합의 경우를 구한다.
	//실패 : TLE(시간초과)
	//solution 2. solution 1의 방법이 경우의수를 매 경우마다 2가지 중 하나를 선택해야하기에 고려할 데이터 수가 많다.
	//역으로 뒤에서부터 상황에 따라 제거해나가는 방식으로 하면 2가지중 하나를 선택하는 것이 아닌 하나만 선택해도 된다.
	//1. T문자열의 뒤에서 부터 길이가 하나씩 줄어들면서 S문자열의 길이 될때까지 진행한다.
	//2-1. B가 나오면 B를 삭제하고 뒤집는다.
	//2-2. A가 나오면 A를 삭제한다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static StringBuilder S, res;
	public static void main(String[] args) throws IOException {
		S = new StringBuilder(in.readLine());
		res = new StringBuilder(in.readLine());
		solve();
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static void solve() {
		while(res.length() != S.length()) {
			char checkCase = res.charAt(res.length()-1);
			res.deleteCharAt(res.length()-1);
			if(checkCase=='B') res.reverse();
		}
		checkAns();
	}
	private static void checkAns() {
		if(S.toString().equals(res.toString())) sb.append(1+"");
		else sb.append(0+"");
	}
	
	
//	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
//	static StringBuilder sb = new StringBuilder();
//	static StringTokenizer stk;
//	static String S, res;
//	public static void main(String[] args) throws IOException {
//		S = in.readLine();
//		res = in.readLine();
//		dfs(S);
//		System.out.println(0);
//	}
//	private static void dfs(String ans) {
//		if(ans.length()==res.length()) {
//			if(ans.equals(res)) {
//				System.out.println(1);
//				System.exit(0);;
//			}
//			return;
//		}
//		dfs(ans+"A");
//		dfs(reverse(ans)+"B");
//	}
//	private static String reverse(String ans) {
//		return (new StringBuilder(ans).reverse().toString());
//	}
}
