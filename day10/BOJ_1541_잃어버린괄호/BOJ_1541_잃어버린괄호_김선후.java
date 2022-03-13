package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_1541 {
	// 잃어버린 괄호
//	1. 문제에서 요구하는 조건은 계산 결과가 최소값이 나오는 것이다.
//	2. 1+2+3+4-5+6+7+8-9+10 의 임의의 식에서 최소 값이 나오려면 -를 구분자로 분리시켜야 한다.
//	3. (1+2+3+4)-(5+6+7+8)-(9+10) -로 구분된 식에서 첫번째 괄호영역은 결과값에 더해주고 나머지는 전부 빼준다.
//	주의 : regex.PatternSyntaxException : Dangling meta character 에러에 유의할 것
//	사실상 이스케이프 문제였던 것 같습니다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int ans;
	public static void main(String[] args) throws IOException {
		String[] res1 = in.readLine().split("-");
		for(int i=0; i<res1.length; i++) {
			int sum=0;
			String[] res2 = res1[i].split("\\+");
			for(int j=0; j<res2.length; j++) sum += Integer.parseInt(res2[j]);
			ans = i==0?ans+sum:ans-sum;
		}
		out.write(ans+"\n");
		out.flush();
		out.close();
		in.close();
	}

}
