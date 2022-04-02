package sh1n.bj2.ps15685드래곤커브;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/** 
 *	시간 제한 : 1000 ms
 * 	메모리 제한 : 512 MB
 *  
 * 	Case1. 입력 값을 바탕으로 드래곤 커브 구현 후 0,0 부터 100, 100까지 정사각형 서치
 * 	result : 성공 // 메모리(12232 KB), 시간(96 ms)
 *  step 01. 101 x 101 boolean 배열 생성
 *	step 02. 입력 받은 x, y에 해당하는 map을 true로 업데이트
 *	step 03. 0세대부터 입력으로 주어진 세대의 모든 드래곤 커브 방향을 저장할 리스트 생성
 *	step 04. 리스트에서 방향 정보를 90도 회전 시킨 dir 값을 스택에 저장
 *	step 05. 방향 값에 맞게 업데이트된 x, y에 해당하는 map을 true로 업데이트 (마지막으로 true가 된 좌표를 갱신하며 드래곤 커브의 꼬리를 물며 생성)
 *	step 06. step 04. ~ 05. 과정의 반복을 통해 모든 입력 값에 대한 드래곤 커브 구현
 *	step 07. boolean 배열 완전 탐색 진행, [i][j] [i][j+1] [i+1][j] [i+1][j+1]이 모두 true인 곳이 있으면 result++
 */

public class Main {
	
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[101][101];
		for(int i = 0; i < N; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] dragonInfo = new int[4];	// 0: x, 1: y, 2: 방향, 3: 세대
			for(int j = 0; j < 4; j++) {
				dragonInfo[j] = Integer.parseInt(st.nextToken());
			}
			// 각 세대의 드래곤 커브를 구현할 방향 정보를 저장한 스택
			Stack<Integer> dirs = new Stack<>();
			// 0세대부터 입력 값으로 들어온 세대까지의 드래곤 커브를 누적 저장할 리스트
			List<Integer> preDirs = new ArrayList<>();
			dirs.push(dragonInfo[2]);
			
			int x = dragonInfo[0];
			int y = dragonInfo[1];
			map[y][x] = true;
			for(int generation = 0; generation < dragonInfo[3] + 1; generation++) {
				int dir = 0;
				int nx = 0;
				int ny = 0;
				// generation 번째 세대 드래곤 커브 구현
				while(!dirs.isEmpty()) {
					dir = dirs.pop();
					nx = x + dx[dir];
					ny = y + dy[dir];
					map[ny][nx] = true;
					preDirs.add(dir);
					x = nx;
					y = ny;
				}
				
				// 0 ~ generation 번째 세대 드래곤 커브 관리 및 다음 세대 커브 구현을 위한 스택 관리 
				for(int g = 0; g < preDirs.size(); g++) {
					dirs.push((preDirs.get(g) + 1) % 4);
				}
			}
		}
		
		int result = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) result++; 
			}
		}
		System.out.println(result);
		
		
	}// end main
}
