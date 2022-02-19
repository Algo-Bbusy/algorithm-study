package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Java_2564 {
	// 경비원
	/* fail
	 * 블록을 가로지를 수 없기때문에 동근이와 상점의 거리공식은 언제나 3가지의 경우로 나눠진다. 동근이와 상점이 같은 열에 있을 경우 동근이의
	 * x좌표와 상점의 x좌표사이의 거리를 구한다. 동근이와 상점이 같은 행에 있을 경우 동근이의 y좌표와 상점의 y좌표사이의 거리를 구한다.
	 * 동근이와 상점이 같은 열과 행에 없을 경우 두가지의 경우로 또 나뉜다. 동근이와 상점이 정반대편에 있을 경우와 인접한 변에 있을 경우.
	 * 정반대편의 경우 다시 동근이가 행에 있는지 열에 있는지를 체크하고 행에 있을경우 동근이의 x좌표 + 열의높이 + 상점의 x좌표를
	 * 두가지경우로 구해서 최솟값을 구한다. 열에 있을경우는 반대로 y좌표를 기준으로 한다. 인접변의 관계에서는 x좌표의 차이와 y좌표의 차이를
	 * 더해준다.
	 * 
	 * solution
	 * 동근이와 상점의 관계는 항상 세가지 경우로 나뉜다.
	 * 1. 같은 방향
	 * 서로의 거리의 차이의 절대값
	 * 2. 인접한 방향 
	 * 방향(type)의 합이 항상 4,5,6으로 나뉜다.
	 * 해당 경우에 맞게 계산해준다.
	 * 3. 반대 방향
	 * 방향(type)의 항상 3또는 7이 된다. 3의 경우 북남관계, 7일경우 동서관계
	 */
	static class Index {
		int type, dis;

		public Index(int type, int dis) {
			super();
			this.type=type;
			this.dis = dis;
		}
	}

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int ans, X, Y, N;
	static Index dong;
	static List<Index> storeList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		X = Integer.parseInt(stk.nextToken());
		Y = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(in.readLine());
			int type = Integer.parseInt(stk.nextToken());
			int dis = Integer.parseInt(stk.nextToken());
			storeList.add(new Index(type, dis));
		}
		stk = new StringTokenizer(in.readLine());
		int type = Integer.parseInt(stk.nextToken());
		int dis = Integer.parseInt(stk.nextToken());
		dong = new Index(type, dis);
		calcSum();
		out.append(ans + "");
		in.close();
		out.flush();
		out.close();
	}

	private static void calcSum() {
		for(int i=0; i<N; i++) {
			Index store = storeList.get(i);
			int flag=dong.type+store.type;
			//같은 방향인경우
			if(dong.type==store.type) ans += Math.abs(dong.dis-store.dis);
			//인접한 방향의 경우
			else if(4<=flag&&flag<=6) {
				if(dong.type==1) {
					if(store.type==3) ans += dong.dis+store.dis;
					if(store.type==4) ans += (X-dong.dis)+store.dis;
				}else if(dong.type==2) {
					if(store.type==3) ans += dong.dis+(Y-store.dis);
					if(store.type==4) ans += (X-dong.dis)+(Y-store.dis);
				}else if(dong.type==3) {
					if(store.type==1) ans += dong.dis+store.dis;
					if(store.type==2) ans += (Y-dong.dis)+store.dis;
				}else if(dong.type==4) {
					if(store.type==1) ans += dong.dis+(X-store.dis);
					if(store.type==2) ans += (Y-dong.dis)+(X-store.dis);
				}
			}
			//반대 방향의 경우
			else if(flag==3 || flag==7) {
				if(dong.type==1 || dong.type==2) {
					ans+=Math.min(dong.dis+Y+store.dis, (X-dong.dis)+Y+(X-store.dis));
				}
				if(dong.type==3 || dong.type==4) {
					ans+=Math.min(dong.dis+X+store.dis, (Y-dong.dis)+X+(Y-store.dis));
				}
			}
		}
	}
}
