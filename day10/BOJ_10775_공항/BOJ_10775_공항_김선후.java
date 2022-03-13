package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_10775 {
	// 공항
//	1.어떤 규칙으로 게이트에 비행기를 도킹해야지 가장 많은 비행기를 도킹시킬수 있는지 조건을 찾는다.
//	2.그림판으로 최선의 규칙을 찾아본 결과 g번 비행기는 g번 게이트에 도킹을 한다.
//	3.해당 게이트가 이미 도킹이 된 상태일 경우 g-1 게이트를 도킹한다.
//	4.g-1 게이트도 불가능 할 시 차례대로 g-2, g-3, g-4, ...0번게이트까지 확인한다.
//	5.g-1번 게이트를 선택해야하는 경우에 0번게이트가 선택되면 더 이상 빈게이트가 없으므로 종료된다.
//	ex) 4번비행기 4번게이트에 도킹
//		두번째 4번비행기 4<-이미 도킹됨. 4-1=3번게이트에 도킹
//		1번비행기 1번게이트에 도킹
//		두번째 1번비행기 1-0번게이트에 도킹(불가능) 종료.
//	최선이 아닌 차선의 선택을 하는 도중 게이트가 비었는지를 확인하는 과정에서 유니온파인드가 사용됨.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int G,P,ans;
	static int[] parent;
	public static void main(String[] args) throws NumberFormatException, IOException {
		G = Integer.parseInt(in.readLine());
		P = Integer.parseInt(in.readLine());
		parent = new int[G+1]; //0번게이트부터 G번게이트까지 사용
		for(int i=1; i<=G; i++) parent[i] = i;
		for(int i=0; i<P; i++) {
			int gi = Integer.parseInt(in.readLine());
			int dokingGate = find(gi);
			if(dokingGate==0) break;
			ans++;
			union(dokingGate, dokingGate-1);
		}
		out.write(ans+"\n");
		out.flush();
		out.close();
		in.close();
	}
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a!=b) parent[a]=b;
	}
	private static int find(int x) {
		if(parent[x]==x) return x;
		return parent[x] = find(parent[x]);
	}

}
