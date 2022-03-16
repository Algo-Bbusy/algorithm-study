package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java_2309 {
	// 일곱 난쟁이
	/* 조합 문제 9난쟁이중 7난쟁이를 뽑는데 그 일곱의 몸무게합이 100인 경우를 찾으면 끝난다 */
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb= new StringBuilder();
	static int N=9;
	static int R=7;
	static int[] smallMans = new int[N];
	static int[] seven = new int[R];
	static int[] answer = new int[R];
	static int SUM=0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		for(int i=0; i<N; i++) {
			int height = Integer.parseInt(in.readLine());
			smallMans[i]=height;
		}
		Arrays.sort(smallMans);
//		for(int a : smallMans) System.out.println(a); System.out.println();
		comb(0,0,SUM);
		for(int a : answer) System.out.println(a); System.out.println();
	}
	private static void comb(int cnt, int start, int sum) {
		if(cnt==R) {
			if(sum==100) {
				for(int i=0; i<R; i++) {
					answer[i]=seven[i];
				}
			}
			return;
		}
		for(int i=start; i<N; i++) {
			seven[cnt]=smallMans[i];
			comb(cnt+1,i+1,sum+smallMans[i]);
		}
	}
}
