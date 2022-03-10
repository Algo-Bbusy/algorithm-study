package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java_8983 {
	//사냥꾼
//	처음에는 예전에 실습시간에 풀어보았던 충전기 문제와 체스판 문제와 유사하여
//	브루트포스를 가장 먼저 떠올리고 모든 동물과 사대를 탐색하며 풀어본 결과 TLE가 떠서 실패.
//	O(M*N)의 해결방법이 아닌 다른 방법이 필요하다는 걸 알았습니다.
//	그러다가 결국 동물의 y좌표가 L을 넘어가는 경우는 처음부터 고려할 필요자체가 없다는 것을 깨닫고
//	동물을 기준으로 x좌표를 기준으로 반복문을 생각해봤습니다.
//	동물이 모든 사대 중 자신을 잡을 수 있는 사대 한개만 찾으면 되는 문제.
//	가장 확실하게 범위내에 포함되는 사대는 사대의 x좌표와 동물의 x좌표가 같은경우(단 y좌표가 L이하)
//	따라서 동물x좌표에서 가장 가까운 x좌표를 지니는 사대를 찾으면 된다.(단 사정거리 L을 만족하는 범위)
//	x좌표에 따라서 오름차순으로 위치가 정렬되어야 합니다.
//	동물의 x좌표 기준 왼쪽과 오른쪽을 확인해봅니다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int M,N,L,ans;
	static int[] rifles;
	static Animal[] animals;
	static class Animal{
		int x,y;

		public Animal(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		M = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(stk.nextToken());
		L = Integer.parseInt(stk.nextToken());
		rifles = new int[M];
		animals = new Animal[N];
		
		stk = new StringTokenizer(in.readLine());
		for(int i=0; i<M; i++) rifles[i]=Integer.parseInt(stk.nextToken());
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(stk.nextToken());
			int y = Integer.parseInt(stk.nextToken());
			
			animals[i] = new Animal(x, y);
		}
		Arrays.sort(rifles);
		ans=0;
		for(int i=0; i<N; i++) ans += hunt(i);
		out.append(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	//Solution2. 이진탐색 - 개선
	private static int hunt(int idx) {
		if(animals[idx].y>L) return 0;
		int start =0, end=M-1, mid=0;
		int min=animals[idx].x+animals[idx].y-L;
		int max=animals[idx].x-animals[idx].y+L;
		while(start<=end) {
			mid=(start+end)/2;
			if(min<=rifles[mid] && rifles[mid]<=max) return 1;
			else if(rifles[mid]<max) start = mid+1;
			else end = mid-1;
		}
		return 0;
	}
	//Solution1. 이진탐색 기본
//	private static int hunt(int idx) {
//		int start=0;
//		int end=M;
//		int mid=0;
//		while(start<=end) {
//			mid=(start+end)/2;
//			if(mid>=M) return 0;
//			if(L<animals[idx].y) return 0;
//			int distance = getDistance(animals[idx].x, animals[idx].y, rifles[mid]);
//			//동물이 사정거리 범위의 왼쪽 바깥에 있으면
//			if(L<distance && animals[idx].x<rifles[mid]) end = mid-1;
//			//동물이 사정거리 범위의 오른쪽 바깥에 있으면
//			else if(L<distance && animals[idx].x>=rifles[mid]) start = mid+1;
//			//사정거리 안에 들어오는 경우
//			else if(L>=distance) return 1;
//		}
//		return 0;
//	}
	private static int getDistance(int x, int y, int rifle_x) {
		return Math.abs(rifle_x-x)+y;
	}

}
