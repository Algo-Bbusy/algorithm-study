package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Java_2628 {
	// 종이자르기
//	x좌표 리스트와 y좌표 리스트를 만든다.
//	0번 인덱스에 최소값을 넣어준다. 인덱스를 1번부터 사용할 예정.
//	리스트를 오름차순 정렬한다.
//	이중반복문을 돌면서 x*y의 모든 경우를 구한다.
//	그중 가장 최대값을 매순간 갱신해준다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(stk.nextToken());
		int y = Integer.parseInt(stk.nextToken());
		int max = Integer.MIN_VALUE;
		List<Integer> xList = new ArrayList<>();
		List<Integer> yList = new ArrayList<>();
		xList.add(0);
		yList.add(0);
		xList.add(x);
		yList.add(y);
		int N=Integer.parseInt(in.readLine());
		for(int i=0; i<N; i++) {
			stk=new StringTokenizer(in.readLine());
			int cmd = Integer.parseInt(stk.nextToken());
			if(cmd==0) yList.add(Integer.parseInt(stk.nextToken()));
			else xList.add(Integer.parseInt(stk.nextToken()));
		}
		Collections.sort(xList);
		Collections.sort(yList);
		for(int yidx=1; yidx<yList.size(); yidx++) {
			for(int xidx=1; xidx<xList.size(); xidx++) {
				max = Math.max(max, (yList.get(yidx)-yList.get(yidx-1))*(xList.get(xidx)-xList.get(xidx-1)));
			}
		}
		System.out.println(max);
	}

}
