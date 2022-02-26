package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_2578 {
	// 빙고
	// 팔방탐색 배열을 만들어 둔다.
	// 빙고판배열에 입력값으로 초기해둔다.
	// 사회자가 부른 수를 0로 변경해주고 가로 세로 대각선을 돌면서 빙고판이 0인 경우 cnt을 체크한다.
	// cnt가 5가 나오면 선이 하나 그어지므로 그 순간 line을 증가시켜준다.
	// 그와 동시에 line값을 체크해주며 line값이 3이상이면 빙고 처리를 해준다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int[][] bingo;
	public static void main(String[] args) throws IOException {
		bingo=new int[5][5];
		for(int y=0; y<5; y++) {
			stk=new StringTokenizer(in.readLine());
			for(int x=0; x<5; x++) {
				bingo[y][x]=Integer.parseInt(stk.nextToken());
			}
		}
		int cnt=0;
		outer : for(int y=0; y<5; y++) {
			stk=new StringTokenizer(in.readLine());
			for(int x=0; x<5; x++) {
				int num = Integer.parseInt(stk.nextToken());
				cnt+=check(num);
				if(cnt>=3) {
					sb.append(y*5+x+1);
					break outer;
				}
			}
		}
		out.write(sb.toString());
		in.close();
		out.flush();
		out.close();
	}
	private static int check(int num) {
		int y = Integer.MIN_VALUE;
		int x = Integer.MIN_VALUE;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(bingo[i][j]==num) {
					bingo[i][j]=0;
					y=i;
					x=j;
				}
			}
		}
		int line=0;
		//가로
		int cnt=0;
		for(int i=0; i<5; i++) {
			if(bingo[y][i]!=0) break;
			cnt++;
		}
		if(cnt==5) line++;
		//세로
		cnt=0;
		for(int i=0; i<5; i++) {
			if(bingo[i][x]!=0) break;
			cnt++;
		}
		if(cnt==5) line++;
		//우하 대각선
		cnt=0;
		if(y==x) {
			for(int i=0; i<5; i++) {
				if(bingo[i][i]!=0) break;
				cnt++;
			}
		}
		if(cnt==5) line++;
		//좌하 대각선
		cnt=0;
		if(y+x==4) {
			for(int i=0; i<5; i++) {
				if(bingo[i][4-i]!=0) break;
				cnt++;
			}
		}
		if(cnt==5) line++;
		return line;
	}

}
