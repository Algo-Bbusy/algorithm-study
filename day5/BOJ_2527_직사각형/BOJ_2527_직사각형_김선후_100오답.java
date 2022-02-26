package fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_2527_fail {
	// 직사각형
//	먼저 공통부분이 없는 부분을 기준으로 공통부분인 부분을 찾았다.
//	첫 직사각형의 x2보다 두번째 직사각형의 x1값이 큰 경우
//	첫 직사각형의 x1이 두번째 직사각형의 x2값보다 큰 경우
//	첫 직사각형의 y2보다 두번째 직사각형의 y1값이 큰 경우
//	첫 직사각형의 y1이 두번째 직사각형의 y2값보다 큰 경우
//	그다음 각 면에 대해서 검사해서 점인지 선인지를 확인한다.
	// 100%에서 틀립니다. ㅠㅠㅠ
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		int N = 4;
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(in.readLine());
			int first_x1 = Integer.parseInt(stk.nextToken());
			int first_y1 = Integer.parseInt(stk.nextToken());
			int first_x2 = Integer.parseInt(stk.nextToken());
			int first_y2 = Integer.parseInt(stk.nextToken());

			int second_x1 = Integer.parseInt(stk.nextToken());
			int second_y1 = Integer.parseInt(stk.nextToken());
			int second_x2 = Integer.parseInt(stk.nextToken());
			int second_y2 = Integer.parseInt(stk.nextToken());

			sb.append(ans(first_x1, first_y1, first_x2, first_y2, second_x1, second_x2, second_y1, second_y2) + "\n");
//			System.out.println(ans(first_x1, first_y1, first_x2, first_y2, second_x1, second_x2, second_y1, second_y2));
		}
		sb.setLength(sb.length() - 1);
		out.write(sb.toString());
		in.close();
		out.flush();
		out.close();
	}

	private static String ans(int fx1, int fy1, int fx2, int fy2, int sx1, int sx2, int sy1, int sy2) {
//		String result="a";
//		//공통부분이 없는 경우
//		if(fx2<sx1 || sx2<fx1 || fy2<sy1 || sy2<fy1) {
//			return "d";
//		}
//		if(fx2==sx1) {
//			if(fy2==sy1 || fy1==sy2) result="c";
//			else result="b";
//		}
//		if(fx1==sx2) {
//			if(fy1==sy2 || fy2==sy1) result="c";
//			else result="b";
//		}
//		if(fy2==sy1) {
//			if(fx2==sx1 || sx2==fx1) result="c";
//			else result="b";
//		}
//		if(sy2==fy1) {
//			if(fy2==sx1 || sy2==fx1) result="c";
//			else result="b";
//		}
//		return result;
		//우상, 좌상, 우하, 좌하 꼭짓점
		if((fx2==sx1 && fy2==sy1)||(fx1==sx2&&fy2==sy1)||(fx2==sx1&&fy1==sy2)||(fx1==sx2&&fy1==sy2)) {
			return "c";
		}
		//마찬가지로 우상, 좌상, 우하, 좌하중 꼭짓점이 겹치지 않는 범위면 선분이 되는 범위다.
		else if((fx2==sx1 && fy2!=sy1)||(fx1==sx2&&fy2!=sy1)||(fx2!=sx1&&fy1==sy2)||(fx1!=sx2&&fy1==sy2)) {
			return "b";
		}
		else if(fx2<sx1 || sx2<fx1 || fy2<sy1 || sy2<fy1) {
			return "d";
		}
		//꼭짓점도 선분도 아닌 모든 겹치는 경우
		else {
			return "a";
		}
	}

}
