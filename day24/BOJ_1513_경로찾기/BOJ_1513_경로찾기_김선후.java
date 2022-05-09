package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_1513 {
	// 경로 찾기
	//메모리 49840KB 시간 272ms
	//크기가 N*M인 직사각형에서 오른쪽, 위쪽 방향으로만 움직이면서 문제의 수를 줄여나가는 dp형 문제
	//경로 개수의 경우의 수를 구하기 위해서 매 순간마다의 상태를 정의해줘야 한다.
	//dp[y][x][현재 방문한 오락실 번호(마지막으로 들린 오락실 번호)][방문한 오락실 개수] = 경로의 수
	//y,x 좌표에 도착했을 때 들렸던 오락실이 방문한 오락실 개수로 계산하고자 하면, 그 전에 들렸던 오락실의 번호보다 현재 오락실 번호가 더 커야하므로
	//매 상태마다 마지막에 들린 오락실의 번호를 저장해둘 필요성이 있어서 4차원 배열로 정의
	//점화식
	//현재 방문한 좌표가 오락실인 경우와 오락실이 아닌 경우로 나눈다.
	//오락실일 경우 방문한 오락실 개수를 증가시켜야 하며 방문한 오락실 번호보다 작은 오락실번호까지의 모든 방문 경우의 수를 더해줘야 한다.
	//오락실이 아닐 경우 별도의 계산 없이 아랫칸좌표와 왼쪽좌표가 지니고 있는 경우의 수를 더하면서 갱신 초기화 과정을 거친다.
	//매 연산 종료시점마다 mod=1000007로 나머지연산을 진행해야 하는 것을 놓치면 안된다.
	//Solution 1.
	//1. 입력을 받는다. dp배열의 최대 크기값은 51로 지정한다. (0부터 50까지 혹은 1부터 50까지가 최대 사용범위)
	//2. 1,1일때의 dp배열을 초기화 한다.
	//   2-1. 만약 1,1 좌표가 오락실일 경우 각 번호에 맞는 상태에 초기화를 시키고 방문 오락실 개수를 1로 한다.
	//   2-2. map배열에 오락실 번호를 초기화한다.
	//3. dp수행
	//   3-1. 현재 방문 좌표가 오락실일 경우 이전까지 방문한 모든 경우의 수 덧셈 연산과 나머지 연산 수행
	//   3-2. 오락실이 아닐경우 현 상태에 초기화 과정 갱신
	//4. 오락실 번호별로 방문한 모든 경우의 수 출력
	//풀이 시간 4시간 56분
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N,M,C;
	static final int mod = 1000007;
	static int[][][][] dp; //[y][x][현재 좌표 오락실 번호][통과한 오락실 개수] = 경로 개수
	static int[][] map;
	public static void main(String[] args) throws IOException {
		input();
		dp();
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static void dp() {
		for(int y=1; y<=N; y++) {
			for(int x=1; x<=M; x++) {
				if(y==1 && x==1) continue;
				if(map[y][x]>0) { //오락실 좌표일 경우
					for(int c=0; c<map[y][x]; c++) { // 방문한 최대 오락실 번호(현재방문 오락실번호 -1까지)
						for(int d=0; d<=c; d++) { //방문 오락실 개수(지금 방문한 오락실 번호-1까지가 최대 방문수)
							dp[y][x][map[y][x]][d+1] += dp[y-1][x][c][d]+dp[y][x-1][c][d]; //현재 방문한 오락실을 제외한 지금까지 방문한 경로의 경우의 수 총합
							dp[y][x][map[y][x]][d+1] %= mod;
						}
					}
				}
				else {
					for(int c=0; c<=C; c++) {
						for(int d=0; d<=c; d++) {
							dp[y][x][c][d] = dp[y-1][x][c][d]+dp[y][x-1][c][d];
							dp[y][x][c][d] %= mod;
						}
					}
				}
			}
		}
		for(int i=0; i<=C; i++) {
			int sum = 0;
			for(int j=0; j<=C; j++) {
				sum += dp[N][M][j][i]; //경우의 수 총 합계
			}
			sum %= mod;
			sb.append(sum).append(" ");
		}
	}
	private static void input() throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		map = new int[N+1][M+1];
		dp = new int[51][51][51][51];
		dp[1][1][0][0]=1;
		for(int c=1; c<=C; c++) {
			stk = new StringTokenizer(in.readLine());
			int y = Integer.parseInt(stk.nextToken());
			int x = Integer.parseInt(stk.nextToken());
			if(y==1 && x==1) {
				dp[1][1][0][0]=0;
				dp[1][1][c][1]=1;
			}
			map[y][x]=c;
		}
	}

}
