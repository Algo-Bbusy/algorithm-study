package anystep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Java_10158 {
	// 개미
//	문제를 읽고 그림을 보자마자 간단한 생각이 떠올랐습니다.
//	x축 경계를 만나면 nx(다음에 이동할 x좌표)에 반전(*-1)을주고
//	y축 경계를 만나면 ny(다음에 이동할 y좌표)에 반전(*-1)을주는식으로
//	풀어보면 되지 않을까 생각해보았습니다.
//	그러나 시간제한이 너무 극심하게 짧아서 배열을 사용하거나
//	탐색을 돌리거나 하면 거의 무조건 시간이 초과되서 아예 수식을 찾아야만 했습니다.
//	방식을 바꿔서 아예 배열과 탐색이 필요치 않은 방법을 찾아서 규칙에 맞게 조건식을 걸고 반복문을 돌렸습니다.
//	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
//	static StringTokenizer stk;
//	static StringBuilder sb = new StringBuilder();
//	static int W,H;
//	static int t,p,q;
//	static int moveX,moveY;
//	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int q = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(br.readLine());
		int x = (q+t)%(2*W);
		int y = (p+t)%(2*H);
		
		x = W - Math.abs(W-x);
		y = H - Math.abs(H-y);
		
		System.out.println(x+" "+y);
//		moveY = t % (2*H);
//		moveX = t % (2*W);
//		
//		boolean flagY = true;
//		while(moveY-->0) {
//			if(q==0) flagY = true;
//			else if(q==H) flagY = false;
//			
//			if(flagY) q++;
//			else q--;
//		}
//		
//		boolean flagX = true;
//		while(moveX-->0) {
//			if(p==0) flagX = true;
//			else if(p==W) flagX = false;
//			
//			if(flagX) p++;
//			else p--;
//		}
//		System.out.println(p+" "+q);
//		out.write(p+" "+q);
//		out.flush();
//		out.close();
//		in.close();
//		stk = new StringTokenizer(in.readLine());
//		W = Integer.parseInt(stk.nextToken());
//		H = Integer.parseInt(stk.nextToken());
//		stk = new StringTokenizer(in.readLine());
//		p = Integer.parseInt(stk.nextToken());
//		q = Integer.parseInt(stk.nextToken());
//		t = Integer.parseInt(in.readLine());
////		map = new int[H+1][W+1];
////		for(int y=0; y<=H; y++) {
////			for(int x=0; x<=W; x++) {
////				map[y][x]=x;
////			}
////		}
//		int cnt=0;
//		int y = q;
//		int x = p;
//		boolean flagY = true;
//		boolean flagX = true;
//		while(cnt!=t) {
//			if(y>=H) flagY = false;
//			else {
//				if(y==0) flagY = true;
//			}
//			if(x>=W) flagX = false;
//			else {
//				if(x==0) flagX = true;
//			}
//			if(y<H && flagY == true) y++;
//			else y--;
//			if(x<W && flagX == true) x++;
//			else x--;
//			cnt++;
//		}
//		System.out.print(x+" "+y);
//		out.write(x+" "+y);
//		out.flush();
//		out.close();
//		in.close();
//		Scanner scan = new Scanner(System.in);
//		int w = scan.nextInt();
//		int h = scan.nextInt();
//		int inputW = scan.nextInt();
//		int inputH = scan.nextInt();
//		int time = scan.nextInt();
//		int[][] arr = new int[w + 1][h + 1]; // 배열은 0부터 시작하기 때문에 하나씩 더해줌
//		for (int i = 0; i <= w; i++) {
//			for (int j = 0; j <= h; j++) {
//				arr[i][j] = j;
//			}
//		}
//		int count = 0;
//		int i = inputW;
//		int j = inputH;
//		boolean flagi = true;
//		boolean flagj = true;
//		while (count != time) {
//			if (i >= w) {
//				flagi = false;
//			} else {
//				if (i == 0)
//					flagi = true;
//			} // 행 max 및 min 여부 체크 //max 면 i-- min i++
//			if (j >= h) {
//				flagj = false;
//			} else {
//				if (j == 0)
//					flagj = true;
//			} // 열 max 및 min 여부 체크 //max 면 j-- min j++
//			if (i < w && flagi == true) {
//				i++;
//			} else {
//				i--;
//			}
//			if (j < h && flagj == true) {
//				j++;
//			} else {
//				j--;
//			}
//			count++;
//		}
//		System.out.println(i + " " + j);
	}
}
