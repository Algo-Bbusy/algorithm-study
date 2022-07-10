package No_Seven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273_두수의합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// 수열의 크기
		int N = Integer.parseInt(br.readLine());

		// 수열에 포함된 수
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 수열의 합
		int X = Integer.parseInt(br.readLine());
		
		// 정렬
		Arrays.sort(arr);
		
		// 결과 값
		int cnt = 0;
		
		// idx값
		int start = 0;
		int end = N-1;
		
		while(true) {
			// 종료 조건
			if(start >= end) break;
			
			if(arr[start] + arr[end] == X) {
				start++;
				end--;
				cnt++;
			// 결과 값 보다 크다면 정렬되었으니 뒤에 값부터 깎아본다.
			}else if(arr[start] + arr[end] > X ) {
				end--;
			// 결과 값 보다 작다면 정렬되었으니 앞에 값부터 올려본다.
			}else { // ( arr[start]+arr[end] < X ) 
				start++;
			}
			
		}
		System.out.println(cnt);
	}
}
