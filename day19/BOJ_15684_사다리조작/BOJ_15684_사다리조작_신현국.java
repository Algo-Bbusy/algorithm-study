package sh1n.bj2.ps15684사다리조작;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**	0개부터 3개의 사다리를 놓는 경우를 조합으로 구하고 사다리를 놓았을 때, 시뮬레이션을 통해 i to i가 성립되는지 확인
 *	step 01. i to i가 성립되기 위해서는 각 세로선 사이의 놓인 가로선의 개수가 짝수여야만 한다.
 *	step 02. 각 세로선 사이의 가로선 개수를 카운트한다.
 *	step 03. 카운트된 개수가 홀수인 개수를 카운트한다.
 *	step 04. (step 03.)에서 카운트된 개수가 적어도 i to i를 성립시키기 위한 사다리의 필요 개수인 점을 고려하여 해당 개수부터 조합을 진행한다.
 *		 04-1. 이때, 카운트된 개수가 4개 이상이면 -1을 출력하고 프로그램을 종료시킨다.
 *	step 05. 선택된 가로선을 바탕으로 시뮬레이션을 진행하여 i to i가 성립하면 프로그램 종료, 성립되지 않으면 (step 04.) 과정부터 다시 진행한다.
 */

public class Main {
	
	static int N, M, H;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		boolean[][] ladderInfo = new boolean[H + 1][N + 1];
		int[] ladderCnt = new int[N];
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int targetH = Integer.parseInt(st.nextToken());
			int targetN = Integer.parseInt(st.nextToken());
			ladderInfo[targetH][targetN] = true;
			ladderCnt[targetN]++;
		}
		
		// 사다리가 필요한 구역 체크
		int needLadderCnt = 0;
		for(int i = 1; i < N; i++) {
			if(ladderCnt[i] % 2 == 1) needLadderCnt++;
		}
		
		// 사다리를 4개 이상 필요로 하는 경우 시스템 종료
		if(needLadderCnt >= 4) {
			System.out.println(-1);
			System.exit(0);
		}
		
		// 사다리 놓기 0개 ~ 3개
		// 각각 조합으로 뽑기
		for(int i = needLadderCnt; i <= 3; i++) {
			makeLadder(i, 0, 1, 1, ladderInfo);
		}
		
		System.out.println(-1);
		
	}// end main

	private static void makeLadder(int C, int cnt, int startH, int startN, boolean[][] ladderInfo) {
		if(cnt == C) {
			if(go(ladderInfo)) {
				System.out.println(cnt);
				System.exit(0);
			}
			return;
		}
		
		for(int i = startH; i <= H; i++) {
			for(int j = startN; j < N; j++) {
				if(ladderInfo[i][j]	|| ladderInfo[i][j - 1] || ladderInfo[i][j + 1]) continue;
				
				ladderInfo[i][j] = true;
				makeLadder(C, cnt + 1, i, j + 1, ladderInfo);
				ladderInfo[i][j] = false;
			}
			startN = 1;
		}
		
	}

	private static boolean go(boolean[][] ladderInfo) {
		boolean isValid = true;
		for(int i = 1; i <= N; i++) {
			int curHPos = 1;
			int curNPos = i;
			while(true) {
				// 내려가는 중에 가로선을 만나면 해당 세로선으로 이동
				if(ladderInfo[curHPos][curNPos]) {
					curNPos++;
				} else if(ladderInfo[curHPos][curNPos - 1]) {
					curNPos--;
				}
				
				// 맨 밑에 도착한 경우 시뮬레이션 종료
				if(++curHPos == H + 1) break;;
			}
			
			// i to i가 성립되지 않으면 false;
			if(i != curNPos) {
				isValid = false;
				break;
			};
		}
		
		// 여기까지 오면 i to i 성립
		return isValid;
	}
}
