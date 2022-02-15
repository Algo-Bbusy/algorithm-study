package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_2669 {
	//직사각형 네개의 합집합의 면적 구하기
	/*
	 * 예전에 과제에서 풀었던 색종이 문제의 용응문제라고 생각했고 풀었음.
	 * 시작 x,y 좌표를 끝 x,y좌표에서 빼주면 가로와 세로의 길이를 구할수 있다.
	 * 1~100까지의 좌표를 담을수 있도록 int[101][101]의 종이 2차원 배열을 만들고 
	 * 그 안에 시작 y,x좌표를 초기값으로 두고 가로와 세로길이만큼 종이면적을 체크해준다.
	 * 전체 종이 배열중에서 체크된곳에서만 cnt를 증가시켜준다.
	 */
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[][] start_black = new int[101][101];
		int cnt = 0;
		int start_x=0; int start_y=0;
		int end_x=0; int end_y=0;
		int w=0; int h=0;
		for(int i=0; i<4; i++) {
			stk = new StringTokenizer(in.readLine());
			start_x = Integer.parseInt(stk.nextToken());
			start_y = Integer.parseInt(stk.nextToken());
			end_x = Integer.parseInt(stk.nextToken());
			end_y = Integer.parseInt(stk.nextToken());
			w= end_x-start_x;
			h= end_y-start_y;
			for(int y=start_y; y<start_y+h; y++) {
				for(int x=start_x; x<start_x+w; x++) {
					start_black[y][x]=1;
				}
			}
		}
		for (int y = 0; y < 101; y++) {
			for (int x = 0; x < 101; x++) {
				if (start_black[y][x]!=0)
					cnt++;
			}
		}
		sb.append(cnt);
		out.write(sb.toString());
		in.close();
		out.flush();
		out.close();
	}

}
