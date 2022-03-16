package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_2116 {
	// 주사위 쌓기
	/*
	 * 주사위배열 저장순서 A-B-C-D-E-F 인덱스 0 1 2 3 4 5 마주보는 한쌍 A-F, B-D, C-E 한쌍의 인덱스중 하나만
	 * 탐색하도록 하고 인덱스가 위로 갈경우와 아래로 갈경우를 상정하여 쌓는다. 예를 들어 0인덱스로 들어온 주사위가 이미 쌓아졌으면 5인덱스는
	 * 한쌍이므로 쌓지않도록 한다. 0이 위인경우와 아래인경우 두상황을 고려해본다
	 */
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb= new StringBuilder();
	static int N;
	static int[][] dices;
	static int max = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		dices = new int[N][6];
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(in.readLine());
			for (int j = 0; j < 6; j++) {
				dices[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		boolean[] visited = new boolean[6];
		for (int i = 0; i < 6; i++) {
			int top_bottom = findTopBottom(i);
			visited[top_bottom] = true;
			if (!visited[i]) {
				diceStack(0, 0, dices[0][i], dices[0][top_bottom]);
				diceStack(0, 0, dices[0][top_bottom], dices[0][i]);
			}
		}
		sb.append(max);
		out.write(sb.toString());
		in.close();
		out.flush();
		out.close();
	}
	private static void diceStack(int cnt, int total, int top, int bottom) {
		int sideMax=0;
		if(cnt==N-1) {
			for(int i=0; i<6; i++) {
				if(dices[cnt][i] != top && dices[cnt][i] != bottom) 
					sideMax=Math.max(sideMax, dices[cnt][i]);
			}
//			System.out.println("total : "+total);
			total+=sideMax;
			max=Math.max(total, max);
//			System.out.println("max : "+max);
			return;
		}
		int topIdx=-1;
		for(int i=0; i<6; i++) {
			if(dices[cnt][i] != top && dices[cnt][i] != bottom) 
				sideMax=Math.max(sideMax, dices[cnt][i]);
			if(dices[cnt+1][i]==top) 
				topIdx=i;
		}
//		System.out.println("현재 top : " +top);
//		System.out.println("다음 top : " + dices[cnt+1][findTopBottom(topIdx)]);
		diceStack(cnt+1, total+sideMax, dices[cnt+1][findTopBottom(topIdx)], top);
	}
	private static int findTopBottom(int i) {
		int opposite=-1;
		switch (i) {
			case 0: opposite=5; break;
			case 5: opposite=0; break;
			
			case 1: opposite=3; break;
			case 3: opposite=1; break;
			
			case 2: opposite=4; break;
			case 4: opposite=2; break;
		}
		return opposite;
	}
}
