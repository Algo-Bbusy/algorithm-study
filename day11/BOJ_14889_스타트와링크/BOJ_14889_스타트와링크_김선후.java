package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_14889 {
	// 스타트와 링크
//	시간제한 2초 = O(풀이) 약 2억
//	N=20 스타트팀 혹은 링크팀에 선택하는가 하지않는가 2가지 경우 조합경우 수 최악 O(2^20) 조합사용 가능
//	각 팀의 수 N/2 (N이짝수) 조합선택카운트 N/2가 될때
//	선택된사람팀(스타트팀)과 비선택사람팀(링크팀)별 능력치를 더한 후 최솟값을 구하기
//	메모리 : 18824KB 시간 : 384ms
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb= new StringBuilder();
	static int N,ans;
	static int[][] status;
	static boolean[] selected;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		ans = Integer.MAX_VALUE;
		status = new int[N][N];
		selected = new boolean[N];
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++) {
				status[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		comb(0,0);
		out.append(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static void comb(int start, int cnt) {
		if(cnt==N/2) {
			ans = Math.min(ans, getDiff());
		}
		for(int i=start; i<N; i++) {
			if(!selected[i]) {
				selected[i]=true;
				comb(i+1, cnt+1);
				selected[i]=false;
			}
		}
	}
	private static int getDiff() {
		int start_team = 0;
		int link_team = 0;
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				if(selected[i] && selected[j]) start_team += status[i][j] + status[j][i];
				else if(!selected[i] && !selected[j]) link_team += status[i][j] + status[j][i];
			}
		}
		return Math.abs(start_team-link_team);
	}

}
