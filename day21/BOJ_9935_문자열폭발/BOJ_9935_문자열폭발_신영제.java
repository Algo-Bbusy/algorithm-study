package com.ssafy.study.homework21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935_문자열폭발 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		String bomb = br.readLine();

		Stack<Character> stack = new Stack<>();

		int inputSize = input.length();
		int bombSize = bomb.length();

		if (inputSize <= 1000000 && inputSize > 1) {
			// 문자열의 단어 하나씩 넣고 비교.
			for (int i = 0; i < inputSize; ++i) {
				stack.push(input.charAt(i));
				// 문자열 상단 값과 폭발물의 단어 비교.
				if (stack.peek() == bomb.charAt(bombSize - 1) && stack.size() >= bombSize) {
					boolean flag = true;
					// 문자열이 스택에 들어와있는 길이만큼 반복 하며 폭발물과 같은지 비교
					for (int j = 1; j <= bombSize; ++j) {
						if (stack.get(stack.size() - j) != bomb.charAt(bombSize - j)) {
							flag = false; // 같지 않다면 폭발할수 없으므로 변환 후 해당 길이 종료.
							break;
						}
					}

					if (flag) {
						for (int j = 0; j < bombSize; ++j) {
							stack.pop();
						}
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		if (stack.isEmpty()) System.out.println("FRULA");

		else {
			for (int i = 0; i < stack.size(); ++i) {
				// System.out.print(stack.get(i));
				sb.append(stack.get(i));
			}
		}
		System.out.println(sb);
	}
}
