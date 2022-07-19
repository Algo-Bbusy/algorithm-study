package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_1107 {
	// 리모컨
	//완전탐색으로 0번채널부터 50만채널까지의 모든 경우에서 해당하는 최솟값을 비교한다.
	//Solution 1.
//	1. 입력을 받는다.
//	2. 고장난 버튼 개수 여부를 따진다.
//	3. 고장난 버튼이 있다면 고장버튼 배열을 갱신한다.
//	4. +-로만 움직이는 최솟값을 구한다.
//	5. 50만까지의 채널중에서 목표채널을 이동하기 위한 최단채널 숫자버튼 누르는 수를 구한다.
//	6. 목표채널까지 해당채널에서 +-로만 움직이는 수를 구한다.
//	7. 최솟값을 비교하여 갱신한다.
//	8. 최솟값 출력
	static boolean[] breakdown;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		breakdown = new boolean[10];
		
		if(M != 0) { //고장난 버튼이 있다면
			StringTokenizer stk = new StringTokenizer(in.readLine());
			for(int i=0; i<M; i++) {
				int btnNum = Integer.parseInt(stk.nextToken());
				breakdown[btnNum] = true;
			}
		}
		
		int min = Math.abs(N-100); //현재채널 100에서 +,-로만 움직이는 경우
		for(int i=0; i<=1000000; i++) { //누르는 채널(목표채널과 가장가까운 채널)(50만까지가 목표이므로 0~100만까지에서 최솟값이 나옴)
			int btnLength = checkBtn(i); //누르는 채널 숫자버튼 누르는 횟수
			if(btnLength>0) {
				int push = Math.abs(N-i); //누르는 채널에서 목표채널까지 + -로 움직이는 수
				min = Math.min(min, btnLength+push); //기존 최솟값과 숫자버튼누르는수와 +  +- 누른 수
			}
		}
		out.write(min+"\n");
		out.flush();
		out.close();
		in.close();
	}
	private static int checkBtn(int chanel) {
		if(chanel==0) {
			if(breakdown[0]) return 0;
			else return 1;
		}
		int btnLength = 0;
		while(chanel>0) {
			if(breakdown[chanel%10]) return 0;
			chanel /= 10;
			btnLength += 1;
		}
		return btnLength;
	}

}
