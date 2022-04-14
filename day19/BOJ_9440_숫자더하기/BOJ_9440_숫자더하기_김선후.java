package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java_9440 {
	// 숫자 더하기
	// 숫자들의 조합으로 두개의 숫자를 만들어야 한다.
	// 간단하게 생각해보면 총 숫자의 개수에서 반을 나눈 두 숫자의 합이 최종결과값이 가장작다. 총 숫자 개수가 홀수인지 짝수인지 분기점.
	// 두 숫자의 합이 최소가 되게 하려면 또다시 각 숫자의 조합을 최대한 작게해야한다.
	// 자릿수가 낮을수록 큰 숫자를 배치해야 한다. => 가장 큰 자릿수에 가장 작은 숫자를 선택해야한다. 주의점 첫자리 0은 오면 안된다.
	// 입력받은 숫자들을 오름차순 정렬하면 먼저 뽑히는 숫자가 더 작은 숫자로 들어간다.
	// 0의 개수+2개(첫번째 자리에 올 숫자 두개)만큼 쉬프트를 고려해야함.
	// 복수의 요소를 배열내에서 쉬프트 하는 방법을 알고자 쉬프트 관련 메소드를 구글링
	//Solution 1.
	//1. 입력을 받을 때 개수를 시행 횟수를 모르므로 while 반복문을 사용해서 받는다. 마지막으로 받는 문자가 0이고 문자열개수가 1개면 반복문 종료
	//2. 오름차순 시 0이 앞으로 가게 된다. 첫자리에 0이 올수 없으므로 0의 개수만큼 숫자배열을 이동시켜야한다.
	//3. 정렬과정과 0의 쉬프트과정이 끝나면 앞에오는 숫자와 뒤에오는 숫자를 숫자배열에서 번갈아가면서 꺼낸후 두 수를 더한다.
	//메모리 14072KB 시간 120ms
	//풀이시간 1시간 34분
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N;
	static String[] str;
	static int[] number;
	public static void main(String[] args) throws IOException {
		solve();
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}
	private static void numberAdd() {
		StringBuilder front = new StringBuilder();
		StringBuilder reer = new StringBuilder();
		for(int i=0; i<number.length; i++) {
			if(i%2==0) front.append(number[i]);
			else reer.append(number[i]);
		}
		int a = Integer.parseInt(front.toString());
		int b = Integer.parseInt(reer.toString());
		sb.append(a+b).append("\n");
	}
	private static void solve() throws IOException {
		while(true) {
			str = in.readLine().split(" ");
			if(str[0].equals("0") && str.length==1) break;
			N=Integer.parseInt(str[0]);
			number = new int[N];
			int zeroCnt=0;
			for(int i=0; i<N; i++) {
				number[i]=Integer.parseInt(str[i+1]);
				if(number[i]==0) zeroCnt++;
			}
			Arrays.sort(number);
			
			if(zeroCnt>0) {
				int[] swapNumber = new int[zeroCnt+2];
				for(int i=0; i<zeroCnt+2; i++) swapNumber[i]=number[i];
				swapZero(swapNumber);
				for(int i=0; i<zeroCnt+2; i++) number[i]=swapNumber[i];
			}
			numberAdd();
		}
	}
	private static void swapZero(int[] swapNumber) {
		int size = swapNumber.length;
		shift(swapNumber, size-2, size);
		shift(swapNumber, 0, size-2);
		shift(swapNumber, 0, size);		
	}
	private static void shift(int[] swapNumber, int front, int reer) {
		reer--;
		while(front<reer) {
			swap(swapNumber,front,reer);
			front++;
			reer--;
		}
	}
	private static void swap(int[] swapNumber, int front, int reer) {
		int swapTemp = swapNumber[front];
		swapNumber[front]=swapNumber[reer];
		swapNumber[reer]=swapTemp;
	}

}
