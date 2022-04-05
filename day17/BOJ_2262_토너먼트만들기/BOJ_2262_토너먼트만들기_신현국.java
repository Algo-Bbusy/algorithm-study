package sh1n.bj2.ps2262토너먼트만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Case2 {
	
	static class Player implements Comparable<Player> {
		int rank;
		int parentIdx;
		
		public Player(int rank, int parentIdx) {
			super();
			this.rank = rank;
			this.parentIdx = parentIdx;
		}
		
		@Override
		public int compareTo(Player p) {
			return p.rank - this.rank;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		Player[] players = new Player[N];
		PriorityQueue<Player> pq = new PriorityQueue<Player>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			Player player = new Player(Integer.parseInt(st.nextToken()), i);
			players[i] = player;
			pq.offer(player);
		}
		
		int result = 0;
		while(pq.peek().rank != 1) { // 다음 차례가 1등이면 종료
			Player player = pq.poll();
			int matchPlayerLeftIdx = -1; 
			int matchPlayerRightIdx = -1;
			
			// 게임을 진행할 플레이어의 왼쪽 상대 찾기
			for(int i = player.parentIdx - 1; i >= 0; i--) {
				if(players[i].rank != -1) {
					matchPlayerLeftIdx = i;
					break;
				}
			}
			
			// 게임을 진행할 플레이어의 오른쪽 상대 찾기			
			for(int i = player.parentIdx + 1; i < N; i++) {
				if(players[i].rank != -1) {
					matchPlayerRightIdx = i;
					break;
				}
			}
			
			// 양쪽 플레이어 중 차이가 더 작은 유저 -1로 만들기
			// 양 끝에 있는 선수인 경우는 무조건 반대편으로 상대 지정
			if(matchPlayerLeftIdx == -1) {
				result += Math.abs(player.rank - players[matchPlayerRightIdx].rank);
			} else if(matchPlayerRightIdx == -1) {
				result += Math.abs(player.rank - players[matchPlayerLeftIdx].rank);
			} else {
				result += Math.min(Math.abs(player.rank - players[matchPlayerLeftIdx].rank), Math.abs(player.rank - players[matchPlayerRightIdx].rank));
			}
			players[player.parentIdx].rank = -1;
		}
		
		System.out.println(result);
	}// end main
}
