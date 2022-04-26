import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		int K = Integer.parseInt(st.nextToken()); // 동생 위치
		// 같은 경우 진행 X
		if(N == K) {
			System.out.println(0);
			System.out.println(1);
			System.exit(0);
		}
		// 수빈이의 위치가 더 크다면 무조건 뒤로 가는 경우밖에 없음
		else if(N > K) {
			System.out.println(N - K);
			System.out.println(1);
			System.exit(0);
		}
		
		
		int[] result = search(N, K);
		for(int r : result) {
			System.out.println(r);
		}
		
	}// end main
	
	private static int[] search(int N, int K) {
		boolean[] visited = new boolean[100001];
		visited[N] = true;
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {N, 1});
		
		int time = 0;
		boolean isFinish = false;
		while(!q.isEmpty()) {
			time++;
			HashMap<Integer, Integer> hashMap = new HashMap<>();
			int size = q.size();
			while(size-- > 0) {
				int[] curPos = q.poll();
				
				if(curPos[0] == K) {
					isFinish = true;
					q.offer(curPos);
					break;
				}
				
				for(int i = 0; i < 3; i++) {
					int nextPos = getNextPos(i, curPos[0]);
					if(rangeCheck(nextPos)) continue;
					
					// 방문한 경우는 진행 X
					if(visited[nextPos]) {
						// 해당 시간에 다른 곳에서 먼저 방문한 경우에는 경우의 수 합치기
						if(hashMap.containsKey(nextPos)) {
							hashMap.put(nextPos, hashMap.get(nextPos) + curPos[1]);
						}
						continue;
					}
					
					visited[nextPos] = true;
					q.offer(new int[] {nextPos, 1});
					hashMap.put(nextPos, curPos[1]);
				}
				
			}
			
			if(!isFinish) {
				// 해당 지점에 도착한 경우의 수 갱신
				int temp_size = q.size();
				while(temp_size-- > 0) {
					int[] temp_curPos = q.poll();
					temp_curPos[1] = hashMap.get(temp_curPos[0]);
					q.offer(temp_curPos);
				}
			} else break;
		}
		
		// 경우의 수 합계
		int[] result = new int[2];
		result[0] = time - 1;
		int cnt = 0;
		while(!q.isEmpty()) {
			int[] checkPos = q.poll();
			if(checkPos[0] == K) {
				cnt += checkPos[1];
			}
		}
		result[1] = cnt;
		return result;
	}
	
	public static int getNextPos(int type, int pos) {
		if(type == 0) {
			pos--;
		} else if(type == 1) {
			pos++;
		} else {
			pos *= 2;
		}
		return pos;
	}
	
	public static boolean rangeCheck(int pos) {
		return (pos < 0 || pos > 100000);
	}
}
