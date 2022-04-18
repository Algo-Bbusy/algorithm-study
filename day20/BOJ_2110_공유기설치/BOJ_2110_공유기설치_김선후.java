package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java_2110 {
	// 공유기 설치
	//메모리 28988KB 시간 300ms
	//풀이시간 32분 53초
	//입력값이 최대 10억(=일반적인 완전탐색에만 평균 1초)가 걸리므로 브루트포스는 배제해야 할 것 같았습니다.
	//문제에서 원하는 결과를 도출하기 위해서는 C개의 한정된 공유기 개수로 현재 입력된 전체 길이에서 거리 간격을 최대 몇으로 할 수 있는가였습니다.
	//공유기의 최대거리 설정은 미정이고 가상의 최대거리(목표)를 설정하는 거라면 이진탐색으로 접근하는 문제가 아닌가하는 생각을 떠올리기까지가 핵심이었던 문제였습니다.
	//Soulution 1.
	//1. 집의 개수와 공유기의 개수를 입력 받은 후 집의 x좌표를 담아놓을 1차원 배열 houseX를 생성 및 초기화.
	//2. 이진탐색을 위해 houseX 오름차순 정렬
	//3. houseX 배열의 최소 길이(가장가까운 다음좌표와의 거리는 1)와 최대 길이(맨끝좌표-맨처음좌표)를 초기화.
	//4. 최대거리 구하기 위한 반복문 실행
	//   4-1. 공유기 설치 간격 가상목표 설정 mid = (최소길이+최대길이)/2
	//   4-2. 공유기 설치 좌표 받고 공유기 개수 증가 (처음은 맨 첫 좌표)
	//   4-3. N개의 집을 탐색하며 목표거리에 해당 집의 좌표-이전에 공유기를 설치한 좌표로 거리를 구하기
	//   4-4. 목표거리가 가상목표 mid보다 크거나 같을 경우에만 공유기를 설치 후 공유기 설치좌표 갱신
	//   4-5. 지금까지 설치된 공유기 개수가 C이상이면 최소거리 재설정 후 정답값 갱신
	//   4-6. C미만이면 최대거리 재설정
	//   4-7. 반복문이 종료되고 가장 마지막으로 갱신된 정답값이 최대간격거리
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N,C,ans;
	static int[] houseX;
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		binarySearch();
	}
	private static void binarySearch() throws IOException {
		Arrays.sort(houseX);
		int minDist=1;
		int maxDist=houseX[N-1]-houseX[0];
		while(minDist<=maxDist) {
			int mid = (minDist+maxDist)/2;
			int frontHouseX=houseX[0];
			int cnt=1;
			for(int x=0; x<N; x++) {
				int distance = houseX[x]-frontHouseX;
				if(distance>=mid) {
					cnt++;
					frontHouseX = houseX[x];
				}
			}
			if(cnt>=C) {
				minDist = mid+1;
				ans=mid;
			}
			else maxDist = mid-1;
		}
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static void input() throws NumberFormatException, IOException {
		stk = new StringTokenizer(in.readLine());
		N=Integer.parseInt(stk.nextToken());
		C=Integer.parseInt(stk.nextToken());
		houseX = new int[N];
		for(int x=0; x<N; x++) houseX[x] = Integer.parseInt(in.readLine());
	}

}
