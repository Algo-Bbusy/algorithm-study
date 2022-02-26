package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_10157 {
	// 자리배정
	//그림에는 열행을 기준으로 그림이 나타나 있는데 
	//일반적으로 자주 사용하는 행열로 사용하기 위해 입력을 반대로 받습니다.
	//보충시간때 bfs,dfs 예시를 보면서 dfs로 풀 수 있다는 걸 알았지만 처음에 기억이 안나서
	//기저조건이 없는 dfs식으로 완성이 되었습니다.
//	코드를 작성하고 나서 뒤늦게서야 기저조건만 추가해주면 dfs가 된다는걸 알았습니다.
	//방향별 시작좌표를 start 변수로 초기화해주고 한번의 반복이 끝날때마다
//	start를 증가시켜주면서 배열을 전부 채울때까지 반복합니다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int[][] cheirs;
	static int R,C,K;
	static int cnt=1;
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		C = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(in.readLine());
		cheirs = new int[R][C];
		
		int y = R;
		int x = C;
		int start = 0;
		//만들고 보니 while문을 굳이 사용하지 않고 dfs로 기저조건만 추가해주면 코드가 더 예뻤을것 같습니다.
		while(true) {
			if(y<=0 || x<=0) break;
			number(start, y, x);
			y = y-1;
			x = x-1;
			start++;
		}
//		for(int[] b : cheirs) System.out.println(Arrays.toString(b));
		int[] ans = findCheir();
		if(ans!=null) sb.append((ans[0]+1)).append(" ").append(ans[1]+1);
		else sb.append(0);
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static int[] findCheir() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(cheirs[i][j]==K) {
					return new int[] {j,i};
				}
			}
		}
		return null;
	}
	private static void number(int start, int y, int x) {
		for(int i = start; i<y-1; i++) {
			if(cheirs[i][start] != 0) break;
			cheirs[i][start]=cnt;
			cnt++;
		}
		for(int i=start; i<x-1; i++) {
			if(cheirs[y-1][i] != 0) break;
			cheirs[y-1][i] = cnt;
			cnt++;
		}
		for(int i=y-1; i>start; i--) {
			if(cheirs[i][x-1] != 0) break;
			cheirs[i][x-1] = cnt;
			cnt++;
		}
		for(int i=x-1; i>start; i--) {
			if(cheirs[start][i] != 0) break;
			cheirs[start][i] = cnt;
			cnt++;
		}
	}

}
