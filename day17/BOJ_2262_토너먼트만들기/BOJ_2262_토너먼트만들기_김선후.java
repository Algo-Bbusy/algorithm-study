package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Java_2262 {
	// 토너먼트 만들기
	// 처음에 문제 예시를 보고 B예시의 답이 11이라는 점에서 아무리 봐도 10이 나와야 하지 않은지 의문이 들어서 문제에 대해서 파악하는데 오래걸렸습니다.
	// 1시간동안 문제에 대한 명확한 접근법을 찾을수 없어서 알고리즘 분류를 보고서 그리드로 풀어야 한다는 것을 알았습니다.
	// 처음에는 모든 선수들을 3명씩 끊은후 중앙에서 양쪽의 차이를 비교한 후 부전승으로 오를 선수를 결정한 뒤 랭킹차를 더해나가는 식으로 접근을 해보았으나
	// 양쪽 선수들과의 랭킹차이가 같은 경우의 예외처리를 두는것이 복잡해서 해결하지 못하였습니다.
	// 문제에서 주어진 절차를 생각해보았을때 매칭이 된 두 선수 중에서 랭킹이 더 높은(숫자가 낮은) 선수가 무조건 승리합니다.
	// 선수들이 매칭되는 순서는 변하지 않는 경우 랭킹이 가장 높은 선수들순서대로 토너먼트 순서를 진행한다고 한다면 양쪽에서 랭킹 차이가 적은 선수들을 선택해도
	// 갈수록 점차 이전 선수보다 더 랭킹이 낮은 선수들과 경기가 진행되므로 랭킹 차가 점점 더 커집니다.
	// 반대로 랭킹이 가장 낮은 선수부터 토너먼트 순서를 진행시킨다면 랭킹이 낮은 선수는 반드시 경기에서 승리하지 못하기 때문에 그 선수를 남은 선수목록에서 제외시키며 진행할수 있게됩니다.
	//Solution 1.
	//1. 선수들을 순서대로 담을 수 있는 리스트 선언
	//2. 가장 랭킹이 낮은 선수의 랭크를 담을 변수 lastRank에 선언된선수수(=가장낮은랭킹)를 저장
	//3. 리스트를 탐색하기위한 인덱스 변수에 랭킹이 가장 낮은 선수의 인덱스를 저장
	//4. 반복문을 돌면서 현재 가장 랭킹이 낮은선수의 위치에 따른 매칭선수와의 랭킹차를 구한 후 정답값에 더하기
	//5. 패배한 선수 제외 후 가장 낮은 랭킹 조정.
	//6. 최종적으로 나온 정답값 출력
	// 풀이시간 2시간 52분
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb= new StringBuilder();
	static int N,ans,lastRank;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		ArrayList<Integer> memberList = new ArrayList<Integer>();
		stk = new StringTokenizer(in.readLine());
		for(int n=0; n<N; n++) memberList.add(Integer.parseInt(stk.nextToken()));
		lastRank = N;
		for(int n=0; n<N-1; n++) {
			int idx = memberList.indexOf(lastRank);
			if(idx==0) ans+= memberList.get(idx) - memberList.get(idx+1); //가장 낮은 랭킹선수가 첫번째순서
			else if(idx==memberList.size()-1) ans+=memberList.get(idx)-memberList.get(idx-1); //가장 낮은 랭킹선수가 마지막 순서
			else ans+=Math.min(memberList.get(idx)-memberList.get(idx+1), memberList.get(idx)-memberList.get(idx-1)); //가장 낮은 랭킹선수가 가운데
			memberList.remove(idx); //패배한 선수 제외
			lastRank--; //남은 선수중 가장 랭킹 낮은선수 랭킹조정
		}
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}

}
