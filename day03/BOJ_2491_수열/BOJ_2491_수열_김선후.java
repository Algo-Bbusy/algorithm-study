package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_2491 {
	// 수열
	/*
	 * 수열을 담을 배열 혹은 리스트를 담고 
	 * 현재인덱스의 값과 다음인덱스의 값이 >=, <관계 즉 내림차순일 때 정답값++, 혹은 1로 초기화
	 * 오름차순일 때는 <=, > 관계 일때 정답값++, 혹은 1로 초기화 
	 * 내림차순 오름차순을 각각 반복한 뒤에 최대 정닶값을 찾는다.
	 */
	
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] BigSmall, suyeol;
	static int ans = Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		suyeol=new int[N];
		BigSmall=new int[N];
		stk=new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) {
			suyeol[i]=Integer.parseInt(stk.nextToken());
		}
		BigSmall[0]=1;
		for(int i=1; i<N; i++) {
			if(suyeol[i-1]<=suyeol[i]) {
				BigSmall[i] = BigSmall[i-1]+1;
			}
			else BigSmall[i]=1;
		}
		for(int i=0; i<N; i++) ans=Math.max(ans, BigSmall[i]);
		for(int i=1; i<N; i++) {
			if(suyeol[i-1]>=suyeol[i]) {
				BigSmall[i] = BigSmall[i-1]+1;
			}
			else BigSmall[i]=1;
		}
		for(int i=0; i<N; i++) ans=Math.max(ans, BigSmall[i]);
		sb.append(ans);
		out.write(sb.toString());
		in.close();
		out.flush();
		out.close();
	}

}
