package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Java_2635 {
	// 수 이어가기
//	문제에서 주어진 규칙을 살펴보았을 때 만들 수 있는 수들이 최대로 많아지려면
//	가장 먼저 규칙이 종료되는 조건 (기저조건)이 무엇인지부터 파악해야 합니다.
//	규칙이 종료되는 조건을 찾아보았을때 앞에있는 숫자 <= 뒤에있는 숫자 의 상황일때 바로 종료됩니다.
//	모든 숫자는 양의 정수로 와야하기때문에 무조건 1이상의 정수가 되어야만 해서 앞의 숫자와 같은 숫자도 제외합니다.
//	ex) 100 100 2개 종료
//	ex) 100 20 80 3개 종료
//	ex) 100 60 40 30 10 6 4 3 1 9개 종료
//	즉 규칙의 조건에 따라서 최대한 많은 수를 이어 넣기 위해서는 뒤에수 > 앞에수 / 2 의 조건이 성립되어야만 합니다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int max, N;
	static ArrayList<Integer> ansList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N=Integer.parseInt(in.readLine());
		max = Integer.MIN_VALUE;
		int half = N/2;
		for(int i=half+1; i<=N; i++) {
			addAnsList(i);
		}
		sb.append(max).append("\n");
		for(int i=0; i<ansList.size(); i++) {
			sb.append(ansList.get(i)).append(" ");
		}
		sb.setLength(sb.length()-1);
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static void addAnsList(int i) {
		ArrayList<Integer> divideList = new ArrayList<Integer>();
		divideList.add(N);
		divideList.add(i);
		while(true) {
			int front = divideList.get(divideList.size()-2);
			int current = divideList.get(divideList.size()-1);
			if(front-current>=0) divideList.add(front-current);
			else break;
		}
		if(max<divideList.size()) {
			ansList=new ArrayList<Integer>();
			for(int idx=0; idx<divideList.size(); idx++) ansList.add(divideList.get(idx));
			max=divideList.size();
		}
	}
	
}
