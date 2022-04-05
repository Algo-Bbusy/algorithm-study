package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_14501 {
	// 퇴사
	//문제를 풀기전에는 다들 어렵다고 하셔서 겁을 먹고 문제를 읽어보았으나 dp라고 생각하면 해당문제를 풀기위한 점화식이 도저히 생각이 나지 않아서 다른 방법을 생각했습니다.
	//입력값이 N 1~15까지이기에 이문제는 재귀로 풀면 쉽게 풀 수 있지 않을까하는 생각이 들었습니다. 
	//최악의 경우 재귀가 15번밖에 반복되지 않으므로 스택오버플로우의 위험이 상대적으로 덜해보였습니다.
	//또한 해당문제에서 결국 구해야하는 것은 '누적'수익이며 조건에 따라서 해당일을 선택하는가 안하는가를 따지는 순열?조합?부분집합?의 문제가 아닌가하는점이 보였습니다.
	//무언가를 누적하는 개념에서는 대개 depth로 표현이 가능하므로 dfs문제로서 문제를 접근해보았습니다.
	//Solution 1.
	//1. 해당일수에 종속되는 수행일수와 수익금을 나타내는 2차배열 dayByTP 선언 0은 수행일수 1은 수익금
	//2. dfs 수행
	//2-1. 기저조건은 누적일수가 N(목표일수)가 될경우 누적수익금을 반환해야 합니다
	//2-2. N(목표일)이 지금까지의 누적일수에서 해당누적일수에 해당하는 수행일수를 더한값보다 크거나 같다면 작업에 착수할 수 있으므로 누적일수와 누적수익금을 추가하고 dfs호출
	//2-3. 작업에 착수할 수 없다면(혹은 선택하지 않는다면) 누적일수에 +1일만 더하고 dfs호출
	//메모리 14312KB 시간 132ms
	//풀이 시간 10분 18초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb= new StringBuilder();
	static int N,ans;
	static int[][] dayByTP;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		dayByTP = new int[N][2]; //[i][0] 수행일수 [i][1] 수익금
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(in.readLine());
			dayByTP[i][0] = Integer.parseInt(stk.nextToken());
			dayByTP[i][1] = Integer.parseInt(stk.nextToken());
		}
		ans=Integer.MIN_VALUE;
		dfs(0,0);
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static void dfs(int stackDay, int stackPay) {
		if(stackDay==N) {
			ans = Math.max(ans, stackPay);
			return;
		}
		//목표퇴사일 >= 누적일수+해당일의 수행일수 (해당 일에 작업을 수행할수있음)
		if(N>=dayByTP[stackDay][0]+stackDay) dfs(stackDay+dayByTP[stackDay][0],stackPay+dayByTP[stackDay][1]);
		//해당일에 작업을 수행하지 않고(선택하지 않고) 다음 일수로 넘어감
		dfs(stackDay+1, stackPay);
	}

}
