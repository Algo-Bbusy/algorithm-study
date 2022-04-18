package fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_17140_fail {
	//이차원 배열과 연산
	//풀이 실패 설계과정 자체는 짜낼 수 있었으나 HashMap에 대한 이해 부족으로 원하는 기능을 사용하지 못하여 구현을 못하였습니다.
	//중복된 숫자의 횟수를 세기 위해서는 hash가 사용될 수 있다는 걸 지문을 읽으면서 염두에 두었습니다.
	//그 중에서도 HashMap을 사용하기에 정말 알맞은 문제라는 생각이 들었고 key값을 숫자 value를 중첩횟수로 두면 될것 같았습니다.
	//등장 횟수 기준 오름차순은 value 기준 HashMap을 오름차순으로 정렬합니다.
	//HashMap에 넣을 때 0은 제외시킵니다. 만약 열 정렬 과정에서 앞의 행보다 뒤의 행의 길이가 길 경우 0을 만나서 종료하는 조건이 걸립니다.
	//Solution 1.
	//1. 가장 초기의 배열 ans = 3*3의 2차원 배열
	//2. 1초마다 연산수행 시간(정답)을 담을 time변수 필요
	//3. if(행 길이 >= 열 길이) 가로줄 개수 >= 세로줄 개수일때 R정렬 else C정렬
	//   3-1. 0무시
	//   3-2. 하나의 행 또는 열에서 숫자의 등장 횟수를 카운트 
	//   3-3. 등장 횟수 기준 오름차순 정렬
	//   3-4. 등장 횟수가 같으면 숫자 기준 오름차순 정렬
	//   3-5. 숫자->등장 횟수 순서대로 배열에 한칸씩 갱신
	//   3-6. 배열 갱신시 행 혹은 열의 길이가 100이 넘어가면 나머지는 버린다.
	//4. 100초 안에 ans[R][C] == K 를 만족할 때 까지 연산 수행 만족 시 time 출력
	//5. if(time>100) -1출력
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int R,C,K,time,finalAns;
	static int[][] ans;
	static final int MAX = 100;
	static int COL=3, ROW=3;
	public static void main(String[] args) throws IOException {
		input();
		solve();
	}
	private static void solve() {
		while(ans[R][C] != K) {
			if(++time>100) break;
			if(ROW>=COL) calcRow();
			else calcCol();
		}
		finalAns = time>100?-1:time;
	}
	private static void calcCol() {
		// TODO Auto-generated method stub
		
	}
	private static void calcRow() {
		// TODO Auto-generated method stub
		
	}
	private static void input() throws IOException {
		stk = new StringTokenizer(in.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		ans = new int[MAX][MAX];
		for(int y=0; y<COL; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=0; x<ROW; x++) {
				ans[y][x] = Integer.parseInt(stk.nextToken());
			}
		}
	}

}
