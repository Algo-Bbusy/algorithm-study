package sh1n.bj.ps2635수이어가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("./src/sh1n/bj/ps2635수이어가기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int maxCnt = 0;
		List<Integer> numberList = new ArrayList<>();
		int firstNumber = Integer.parseInt(br.readLine());
		for(int secondNumber=firstNumber; secondNumber>0; secondNumber--) {
			int temp1 = firstNumber;
			int temp2 = secondNumber;
			numberList.add(temp1);
			numberList.add(temp2);
			while(true) {
				int nextNumber = temp1 - temp2;
				if(nextNumber < 0) break;
				numberList.add(nextNumber);
				temp1 = temp2;
				temp2 = nextNumber;
			}
			
			if(maxCnt < numberList.size()) {
				sb.setLength(0);
				maxCnt = numberList.size();
				sb.append(maxCnt).append("\n");
				for(int i : numberList) {
					sb.append(i).append(" ");
				}
			}
			
			numberList.clear();
		}
		
		System.out.println(sb.toString());
	}
}
