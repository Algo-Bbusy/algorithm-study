package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java_3273 {
	// 두 수의 합
	//투 포인터 알고리즘의 전형적인 문제.
	//투 포인터와 이분 탐색의 차이점
//	투 포인터 : 시작과 끝, 최악의 경우 O(N), 정렬이 필요할 때도 필요치 않을 때도 있다.
//	이분 탐색 : mid를 활용, 매 탐색마다 범위를 절반으로 좁혀나감 O(logN), 정렬되어 있다는 가정
	//Solution 1.
	//1. 배열 입력 받기
	//2. 목표 수 입력 받기
	//3. 배열 정렬
	//4. 배열의 시작인덱스와 끝인덱스를 더하기
	//5. 목표 수를 조건으로 인덱스 조정
	//6. 정답 쌍 출력
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N,ans,sum,goal;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		stk = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(stk.nextToken());
		
		goal = Integer.parseInt(in.readLine());
		
		Arrays.sort(arr);
		
		int start = 0;
		int end = N-1;
		
		while(start<end) {
			sum = arr[start]+arr[end];
			if(sum==goal) ans++;
			if(sum<=goal) start++;
			else end--;
		}
		System.out.println(ans);
	}

}
