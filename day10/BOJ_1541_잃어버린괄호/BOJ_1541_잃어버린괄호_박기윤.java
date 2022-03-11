package Day10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ_1541_잃어버린괄호_박기윤 {
	static int idx;
	static StringBuilder numSb = new StringBuilder();

	public static int getNum(String input) {
		numSb.setLength(0);
		int length = input.length();
		while (true) {
			numSb.append(input.charAt(idx));
			if (idx == length - 1 || input.charAt(idx + 1) == '-' || input.charAt(idx + 1) == '+')
				break;
			idx++;
		}
		return Integer.parseInt(numSb.toString());
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		Deque<Integer> operand = new LinkedList<>();

		int length = input.length();
		idx = -1;
		while (++idx < length) {
			char cur=input.charAt(idx);
			if (cur == '-') {
				continue;
			} else if (cur == '+') {
				int pre = operand.removeLast();
				int next = getNum(input);
				operand.addLast(pre + next);
			} else {
				int curNum = getNum(input);
				operand.addLast(curNum);
			}
		}

		int result = operand.removeFirst();
		while (!operand.isEmpty()) {
			result -= operand.removeFirst();
		}

		bw.write(Integer.toString(result));
		br.close();
		bw.flush();
		bw.close();
	}

}
