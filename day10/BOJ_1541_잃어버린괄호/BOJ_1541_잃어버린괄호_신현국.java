package sh1n.bj.ps1541잃어버린괄호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	public static final int PLUS = -1;
	public static final int MINUS = -2;
	public static final char ENDEXPRESSION = 'E';
	
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("./src/sh1n/bj/ps8983사냥꾼/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String expression = br.readLine() + ENDEXPRESSION;
		StringBuilder operator = new StringBuilder();
		Queue<Integer> operators = new LinkedList<>();
		
		int index = -1;
		while(++index != expression.length()) {
			char op = expression.charAt(index);
			if(op == '-' || op == '+' || op == ENDEXPRESSION) {
				int multiplier = operator.length();
				int opIndex = 0;
				int number = 0;
				
				while(--multiplier >= 0) {
					number += (operator.charAt(opIndex++) - '0') * Math.pow(10, multiplier);
				}
				
				operator.setLength(0);
				operators.offer(number);
				if(op == '-') {
					operators.offer(MINUS);
				} else if(op == '+') {
					operators.offer(PLUS);
				}
				
			} else {
				operator.append(expression.charAt(index));
			}
		}
		
		Queue<Integer> minOperators = new LinkedList<>();
		int curNumber = operators.poll();
		while(!operators.isEmpty()) {
			int curOP = operators.poll();
			if(curOP == MINUS) {
				minOperators.offer(curNumber);
				curNumber = operators.poll();
			} else {
				curNumber += operators.poll();
			}
		}
		minOperators.offer(curNumber);
		
		int answer = minOperators.poll();
		while(!minOperators.isEmpty()) {
			answer -= minOperators.poll();
		}
		System.out.println(answer);
	}// end main
}
