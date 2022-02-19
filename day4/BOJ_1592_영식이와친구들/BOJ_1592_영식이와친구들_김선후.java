package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_1592 {
	// 영식이와 친구들
	/*
	 * 공을 받은 해당 인덱스의 값이 홀수일 시 시계방향 L만큼 공을 던지고 
	 * 만약 인덱스가 범위를 벗어나면 (인덱스+L)%N 
	 * 짝수일 시 반시계방향 L만큼 공을 던진다 
	 * 만약 인덱스가 범위를 벗어나면(인덱스-L)+N 
	 * 공을 던진 후 공을 받은 인덱스의 배열값을 증가시킨다.
	 */
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N,M,L,ans=0;
	static int[] friend;
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		N=Integer.parseInt(stk.nextToken());
		M=Integer.parseInt(stk.nextToken());
		L=Integer.parseInt(stk.nextToken());
		friend = new int[N+1]; //인덱스 1부터 사용
//		처음 공을 받은 사람은 첫번째 사람 공받은횟수+1
		friend[1]=1;
		int idx=1;
		while(true) {
			if(friend[idx]==M) break;
			if(friend[idx]%2==1) idx+=L;
			else idx -=L;
			if(idx>N) idx%=N;
			else if(idx<1) idx+=N;
			friend[idx]++;
			ans++;
		}
		out.append(ans+"");
		in.close();
		out.flush();
		out.close();
	}

}
