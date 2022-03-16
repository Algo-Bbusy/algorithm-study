package day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1018_체스판다시칠하기_김선후 {

	public static void main(String[] args) throws IOException {
		// 총 경우의 수는 x경우의 수 * y경우의 수 * 2(검읜색, 흰색)
		// 가로세로의 경우의수는 순열식 MP8, NP8 = M-8+1 * N-8+1
		// 총 경우의 수 = (M-8+1) * (N-8+1) * 2
		// 이 중에서 최소값 비교하면 됨
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		StringTokenizer stk = new StringTokenizer(input);
		int M = Integer.parseInt(stk.nextToken());
		int N = Integer.parseInt(stk.nextToken());
		char[][] map = new char[M][N]; //전체 입력보드
		boolean[][] colorCheck = new boolean[M][N]; //색깔 판별보드
		int min = 64;	//최종 결과값
		for(int i=0; i<M; i++) {
			String line = in.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] =  line.charAt(j);
			}
		}
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]=='W') colorCheck[i][j] = true; //흰색이면 true
				else colorCheck[i][j] = false;	//검은색이면 false
			}
		}
		int x_case = N-8+1; //가로의 경우의 수 ex N = 8 x 경우의 수 1개
		int y_case = M-8+1; //세로의 경우의 수
		for(int i=0; i<y_case; i++) {
			for(int j=0; j<x_case; j++) {
				int end_x = j+8;	//j는 시작 x좌표 
				int end_y = i+8;	//i는 시작 y좌표 
				int cnt=0;
				boolean color = colorCheck[i][j];	//첫 칸의 색깔을 비교값으로 줌
				for(int si=i; si<end_y; si++) {
					for(int sj=j; sj<end_x; sj++) {
						if(colorCheck[si][sj] != color) {	//슬라이드 8*8보드의 색깔이 해당 칸의 색과 같지 않으면
							cnt++;	//중복수체크
						}
						color=!color; //다음칸으로 바뀔때 색상반전
					}
					color=!color; //다음줄로 바뀔떄 색상반전
				}
				cnt=Math.min(cnt, 64-cnt);	//흰색 검은색 중 최소값 비교
				min=Math.min(min, cnt);	//결과값과 최소값 비교
			}
		}
		System.out.println(min);
	}
}
