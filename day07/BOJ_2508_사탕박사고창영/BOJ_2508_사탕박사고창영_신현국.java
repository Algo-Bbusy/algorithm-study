package sh1n.bj.ps2508사탕박사;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2508사탕박사/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			char[][] candy = new char[R][C];
			
			List<int[]> candyIndex = new ArrayList<>();
			for(int i=0; i<R; i++) {
				String candyEl = br.readLine(); 
				for(int j=0; j<C; j++) {
					char el = candyEl.charAt(j);
					candy[i][j] = el;
					if(el == 'o') {
						candyIndex.add(new int[] {i, j});
					}
				}
			}
			
			int candyCnt = 0;
			for(int i=0; i<candyIndex.size(); i++) {
				int r = candyIndex.get(i)[0];
				int c = candyIndex.get(i)[1];
				if(isCandy(candy, r, c)) {
					candyCnt++;
				}
			}
			
			System.out.println(candyCnt);
		}
	} // end main()
	
	public static boolean isCandy(char[][] candy, int r, int c) {
		boolean isRight = false;
		
		for(int checkLine=0; checkLine<2; checkLine++) {
			try {
				if( checkLine==0 && (candy[r][c+1] == '<' && candy[r][c-1] == '>') ) {
					isRight = true;
				}
				if( checkLine==1 && (candy[r+1][c] == '^' && candy[r-1][c] == 'v') ) {
					isRight = true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				// 사탕 조건 성립 불가
			}
		}
		
		return isRight;
	}
}
