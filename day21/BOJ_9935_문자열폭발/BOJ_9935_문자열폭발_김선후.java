package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java_9935 {
	// 문자열 폭발
	//메모리 22736KB 시간 320ms
	//풀이시간 56분 42초
	// 문자열의 재입력과 삭제과정이 한번의 문자열탐색과정에서 동시에 일어나야 한다.(메모리 초과때문)
	//Solution 1(메모리초과 실패 아마 시간초과도 나왔을거라 생각됨).
	//1. split()메서드를 이용하여 String배열에 나눠서 입력
	//2. 나누어진 문자열배열을 다시 하나의 문자열로 합침.
	//3. 문자열배열의 길이가 1이고 입력문자열의 길이가 0이거나 최종문자열일 경우 반복문 종료.
	//4. 문자열길이에 따라서 정답값 출력
	//실패 이유 분석
	//최악의 경우 문자열 aaaabbbb 폭탄 ab일경우 한번의 반복문에서 제거되는 문자열 길이는 2밖에 안됨.
	//문자열 길이를 한번 탐색하는데 O(N)이 걸리며 -2씩 계속해서 탐색을 재반복할경우 N까지의 합연산(N(N+1)/2)
	//N의 최대길이는 100만이므로 100만의 제곱이 나온 순간부터 시간초과가 나왔을 거라고 예상됨.
	//Solution 2
	//1. 문자열 길이만큼 탐색을 진행하며 하나의 문자를 탐색할때마다 현재탐색중인 인덱스 길이 증가.
	//2. 폭탄을 사용할수 있는 조건이 만족되면 현재까지 누적된 인덱스 길이에서 폭탄 길이만큼 제거
	//3. 갱신된 인덱스 길이에서 다시 문자를 입력받는것을 반복.
	//4. 문자배열을 String.valueOf()메서드를 사용하여 다시 하나의 문자열로 반환.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
//	static String ans, boom, res;
	public static void main(String[] args) throws IOException {
		String str = in.readLine();
		String boom = in.readLine();
		String ans = solution(str, boom);
		out.write((ans.length()==0)?"FRULA":ans);
		out.flush();
		out.close();
		in.close();		
//		String ans, boom, res;
//		ans = in.readLine();
//		boom = in.readLine();
//		while(ans.length()!=0) {
//			String[] str = ans.split(boom);
//			res="";
////			System.out.println(Arrays.toString(str));
//			if(str.length==1 && str[0].length()==0) break;
//			if(str.length==1 && str[0].length()==ans.length()) break;
//			for(int i=0; i<str.length; i++) {
//				res += str[i];
//			}
//			ans = res;
//		}
//		ans = ans.length()==0?"FRULA":ans;
//		out.write(ans+"");
//		out.flush();
//		out.close();
//		in.close();
	}
private static String solution(String str, String boom) {
	char[] res = new char[str.length()];
	int idx=0;
	for(int i=0; i<str.length(); i++) {
		res[idx]=str.charAt(i);
		if(isBoom(res,idx,boom)) idx -= boom.length();
		idx++;
	}
	return String.valueOf(res, 0, idx);
}
private static boolean isBoom(char[] res, int idx, String boom) {
	if(idx<boom.length()-1) return false;
	for(int i=0; i<boom.length(); i++) {
		if(boom.charAt(i)!=res[i+idx-(boom.length()-1)]) return false;
	}
	return true;
}

}
