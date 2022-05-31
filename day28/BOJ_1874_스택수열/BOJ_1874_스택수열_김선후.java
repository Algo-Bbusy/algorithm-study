package step19;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Java_1874 {
	// 스택 수열
	//메모리 28344KB 시간 396ms
	//1~n까지의 값을 push와 pop으로 수열을 만드는 문제
	//테케 1은 N이 8이므로 1~8까지의 수열, 원하는 형태는 4,3,6,8,7,5,2,1
	//처음으로 4가 나와야하므로 1234가 스택에 push되어 있어야 한다 ++++
	//배열인덱스 0번값과 스택의 마지막입력값이 같으므로 pop ++++-
	//그다음 인덱스 1번값 3이 스택의 마지막값 3과 같으므로 pop ++++--
	//이런식으로 수열이 성립되거나 되지않을때까지 반복문을 돌린후 최종 결과물을 출력하는 문제
	//Solution 1.
	//1. 현재 비교할 배열의 인덱스와 입력값을 초기화 한 후 배열에 원하는 수열 입력
	//2. 반복문을 수행하면서 스택의 push pop 연산 수행
	//3. 반복문이 종료한 뒤의 최종 idx값이 N과 같다면 끝까지 연산이 수행되었으므로 스트링빌더에 저장된 문자열 출력 아니라면 NO
	//풀이시간 34분 12초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N;
	static int arr[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		int idx = 0;
		int num=1;
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(in.readLine());
		Stack<Integer> stack = new Stack<>();
		
		//n개의 값으로 수열을 만들기 때문에 idx는 0부터 n-1개까지
		while(idx<N) {
			if(!stack.empty() && arr[idx]<stack.get(stack.size()-1)) break;
			else if(!stack.empty() && arr[idx]==stack.get(stack.size()-1)) {
				stack.pop();
				sb.append("-").append("\n");
				idx++;
			}else {
				while(num<=N) {
					if(arr[idx] != num) {
						stack.push(num);
						sb.append("+").append("\n");
						num++;
					}else {
						stack.push(num);
						sb.append("+").append("\n");
						num++;
						break;
					}
				}
			}
			
		}
		if(idx==N) out.write(sb.toString());
		else out.write("NO");
		out.flush();
		out.close();
		in.close();
	}

}
