package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_17951 {
	// 흩날리는 시험지 속에서 내 평점이 느껴진거야
	// 문제풀이 실패 
	//처음에는 순열, 조합, 부분집합 문제라고 생각했으나 문제의 조건을 자세히 살펴보면 시험지의 순서는 이미 정해져 있다.
	//이 문제에서 구해야할 것은 그룹별 최소점수의 최댓값이다.
	//완탐을 돌리면 최악의 경우 O(10만 * 10만)이 나오므로 사용해선 안된다는 것 까진 알 수 있었다.
	//log의 시간효율을 지니는 접근법을 사용해야하는 것 까지는 알 수 있었다.
	//풀이시간 2시간 13분 12초 1시간30분동안 못풀었을때 도무지 방법자체를 찾을수없어서 구글링함.
	
	//문제풀이 실패 후 구글링 풀이
	//Solution 1.
	//문제를 이진 탐색으로 접근하였을때 이 문제에서의 mid가 의미해야 하는 것은 그룹별 최대 점수다.
	//이 그룹별 최대 점수를 넘어갈 경우에만 그룹을 나눈다.
	//0에서부터 모든 시험지의 점수 합까지 이진 탐색을 돌리면서 차례차례 더해가면서 정해놓은 그룹별 최대점수보다 커지면 구간을 나눈다.
	//나누어진 구간의 수가 K개 이상이라면 문제의 조건을 만족하므로 그룹 목표최소점을 그룹별 최대점수에서 1만큼 더한값으로 바꾼다.
	//나누어진 구간의 수가 K개 미만이라면 문제의 조건을 만족하지 못하므로(그룹 목표최고점이 너무 높아서) 그룹 목표최고점을 그룹별 최대점수에서 1만큼 줄인 값으로 바꾼다.
	//그룹 목표최소점이 그룹목표 최고점보다 커질때까지 반복한다.
	//해설을 보고도 완벽하게 이해하지 못하였다.
	//mid의 기준이 왜 그룹별 최대 점수이며 반복문의 조건이 왜 저렇게 되는지를 이해하지 못하겠다
	//누군가 해설을 해주었으면 좋겠다..
	//메모리 : 23228 시간 : 300ms
	static BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N,K,groupLowScore=0,groupHighScore=0;
	static int[] testScore;
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		testScore = new int[N];
		stk = new StringTokenizer(in.readLine());
		for(int i=0; i<N; i++) {
			testScore[i] = Integer.parseInt(stk.nextToken());
			groupHighScore += testScore[i];
		}
		while(groupLowScore<=groupHighScore) {
			int mid = (groupLowScore+groupHighScore)/2;
			int groupSum=0;
			int groupCnt=0;
			for(int k=0; k<N; k++) {
				groupSum+=testScore[k];
				if(groupSum>=mid) {
					groupCnt++;
					groupSum=0;
				}
			}
//			System.out.println("groupLowScore : "+groupLowScore+" groupHighScore : "+groupHighScore+" groupCnt : "+groupCnt+" mid : "+mid);
			if(groupCnt>=K) groupLowScore = mid+1;
			else groupHighScore = mid-1;
		}
		out.write(groupHighScore+"");
		out.flush();
		out.close();
		in.close();
	}

}
