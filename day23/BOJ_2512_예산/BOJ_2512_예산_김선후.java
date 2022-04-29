package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java_2512 {
	// 예산
	//메모리 16960KB 시간 192ms
	//기본적인 탐색을 진행한다면 최악 탐색을 한번 하는데만 10억=1초가 걸린다. => 일반적인 완탐 문제가 아님.
	//가상의 목표 점수를 구해야 함. => 이진탐색
	//Solution 1.
	//1. 입력을 받는다.
	//2. 이진 탐색을 위해서 지방배열을 오름차순으로 정렬.
	//3. 모든 지방의 합이 주어진 상한선보다 작거나 같다면 지방예산 최댓값을 그대로 출력
	//4. 3의 조건을 만족하지 않다면 이진 탐색 수행
	//5. 지방예산이 목표예산보다 크다면 기준값을 현재 총합값에 더한다.
	//6. 그게 아니라면 현재 지방예산을 현재 총합값에 더한다.
	//7. 현재 총합값이 상한선보다 크다면 최대예산을 목표예산-1로 갱신 후 이진탐색 재수행
	//8. 현재 총합값이 상한선보다 작다면 최소예산을 목표예산+1로 갱신 후 이진탐색 재수행
	//9. 현재 총합값이 상한선과 같다면 최종 출력값으로 출력
	//10. 이진탐색이 전부 끝난다면 마지막으로 갱신된 최대 예산값이 최종 출력값이다.
	//풀이시간 32분 14초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N,M,ans;
	static int[] locate;
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		binarySearch();
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static void binarySearch() {
		int sum=0;
		for(int b : locate) sum+=b;
		if(sum<=M) ans=locate[N-1];
		else {
			int low=0;
			int high = M;
			while(low<=high) {
				int mid=(low+high)/2;
				int total=0;
				for(int i=0; i<N; i++) {
					if(locate[i]>mid) total += mid;
					else total += locate[i];
				}
				if(total>M) high = mid-1;
				else if(total<M) low = mid+1;
				else {
					ans=mid;
					return;
				}
			}
			ans=high;
			return;
		}
	}
	private static void input() throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		locate = new int[N];
		stk = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) locate[i]=Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(in.readLine());
		Arrays.sort(locate);
	}

}
