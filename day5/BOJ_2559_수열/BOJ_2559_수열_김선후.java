package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Java_2559 {
	// 수열
	//리스트를 두개 쓰면 시간초과가 나옵니다.
	//N개의 크기를 지닌 배열을 선언해서 N개의 온도를 담아줍니다.
	//연산결과의 수열의 개수는 N-K+1개입니다.
	//반복문에서 연산의 시작값을 인덱스의 초기값으로 두고 초기값+K-1의 인덱스까지 연산을 반복합니다.
	//결과값을 ansList에 넣고 오름차순 정렬을 해주며 최종값은 리스트의 마지막 인덱스입니다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(stk.nextToken());
		int K = Integer.parseInt(stk.nextToken());
		int[] arr = new int[N];
		List<Integer> ansList = new ArrayList<>();
		stk=new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(stk.nextToken());
		}
		for(int i=0; i<N-K+1; i++) {
			int ans=0;
			for(int j=i; j<i+K; j++) {
//				System.out.println("현재 arrList : "+arrList.get(j));
				ans+=arr[j];
//				System.out.println("현재 2번째 수열 : "+j+" 현재 ans : "+ans);
			}
			ansList.add(ans);
		}
		Collections.sort(ansList);
		sb.append(ansList.get(ansList.size()-1));
		out.write(sb.toString());
		in.close();
		out.flush();
		out.close();
	}

}
