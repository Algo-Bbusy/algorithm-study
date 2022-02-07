package algobbusy.bj.ps1018;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static char[][] map;
	static int maxWCnt, maxHCnt;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("./src/algobbusy/bj/ps1018/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 체스판
		map = new char[N][M];
		
		// NxM 체스판 생성
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			map[i] = s.toCharArray();
		}
		
		// 8x8 subset
		for(int i=0; i<N-7; i++) {
			for(int j=0; j<M-7; j++) {
				search(i, j);
			}
		}
		
		// 정답 출력
		answer = 64 - Math.max(maxWCnt, maxHCnt);
		System.out.println(answer);
		
	}
	
	public static void search(int i, int j) {
		
		int WCnt=0, HCnt=0;
		char[] colorCheck = {'W', 'B'};
		int toggleIdx = 0;
		
		for(int r=i; r<i+8; r++) {
			
			for(int c=j; c<j+8; c++) {
				// WBWBWBWB 카운트
				if(map[r][c] == colorCheck[toggleIdx%2]) WCnt++;
				
				// BWBWBWBW 카운트
				if(map[r][c] == colorCheck[++toggleIdx%2]) HCnt++; 
			}
			toggleIdx++;
		}
		
		if(WCnt > maxWCnt) maxWCnt = WCnt;
		if(HCnt > maxHCnt) maxHCnt = HCnt;
		
		
	}
	
}





























