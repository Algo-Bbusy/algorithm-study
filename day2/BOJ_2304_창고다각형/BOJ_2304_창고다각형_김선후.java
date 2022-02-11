package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_2304 {
	// 창고다각형
	/* Solution 1
	 * 맨처음 시작 기둥과 그와 비교해서 현재까지 가장 높은기둥 그리고 마지막 기둥의 정보만 알수 있으면 된다. 가장 처음 시작 기둥을 기준으로
	 * 높이비교를 하여 그다음으로 높은 기둥을 찾고 시작기둥과의 x값의 차*시작기둥높이까지를 저장해둔다 그다음으로 높은기둥을 이제 새로운
	 * 시작기둥으로 놓고 가장 높은 기둥이 나올떄까지 반복한다 더이상 새로운 높은 기둥이 나오지 않으면 그 기둥이 모든 기둥중 제일 높은
	 * 기둥이므로 그다음부터는 마지막 기둥에서 부터 반복한다
	 */
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb= new StringBuilder();
	static int N, max_idx, min_idx, max_height_idx, ans;
	static int[] map= new int[1001];	//idx 기둥의 idx 값 : 높이
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N=Integer.parseInt(in.readLine());
		for(int n=0; n<N; n++) {
			stk = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			map[x]=y;
			max_idx=Math.max(max_idx, x);
			min_idx=Math.min(min_idx, x);
			
			if(map[x]>map[max_height_idx]) {
				max_height_idx = x;
			}
		}
		//좌측에서 큰기둥까지
		int h=0;
		for(int i=min_idx; i<max_height_idx; i++) {
			h=Math.max(h, map[i]);
			ans+=h;
		}
		h=0;
		//우측에서 큰기둥까지
		for(int i=max_idx; i>max_height_idx; i--) {
			h=Math.max(h, map[i]);
			ans+=h;
		}
		//큰기둥
		ans+=map[max_height_idx];
		//좌측 영역 + 우측 영역 + 큰기둥넓이 = 최종영역
		System.out.println(ans);
	}
}
