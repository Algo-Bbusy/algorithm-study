package sh1n.bj2.ps9935문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/** Case 02. (Case 01.) 방법에서 불필요한 문자열 확인을 제거하기 위해 아래와 같은 방법 고려
 * 			 폭탄 문자열의 합과 StringBuilder에 고려되는 문자열의 합이 같지 않다면 절대 같을 수 없음
 * 
 * 	step 00. 폭탄 문자열의 합 구하기
 * 	step 01. 주어진 문자열의 첫번째 문자부터 처리 시도
 * 	step 02. StringBuilder에 문자 추가 + StringBuilder에 담기는 문자열의 합 관리
 * 	step 03. StringBuilder에 담긴 문자열보다 폭탄 문자열의 길이가 더 큰 경우는 처리X
 *  step 04. StringBuilder의 길이가 폭탄 문자열의 길이보다 크거나 같은 경우 + 폭탄 문자열의 합과 StringBuilder에 담긴 문자열의 합이 같은 경우 비교 시작
 *  		 StringBuilder에 마지막 문자부터 폭탄 문자열의 길이 만큼의 문자까지와 폭탄 문자열이 동일한지 확인
 *  step 05. 동일하면 StringBuilder 길이 재조정(폭탄 문자열의 길이 만큼 제거)
 *  step 06. 마지막 문자열 처리 시도를 할 때까지 (step 01 ~ 05.) 과정을 반복
 *  step 07. 결과 출력 
 */

public class Main_improveMemory {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		String bomb = br.readLine();
		StringBuilder fixedStr = new StringBuilder();
		
		int bombLength = bomb.length();
		int strLength = str.length();
		
		int sumBomb = 0;
		for(int i = 0; i < bombLength; i++) {
			sumBomb += bomb.charAt(i);
		}
		
		int sumStr = 0;
		for(int pointer = 0; pointer < strLength; pointer++) {
			fixedStr.append(str.charAt(pointer));
			sumStr += str.charAt(pointer);
			if(fixedStr.length() < bomb.length()) continue;
			
			if(sumStr == sumBomb) {
				if(bomb.equals(fixedStr.substring(fixedStr.length() - bombLength, fixedStr.length()))) {
					fixedStr.setLength(fixedStr.length() - bombLength);
					sumStr = 0;
					if(fixedStr.length() < bombLength) {
						for(int i = 0; i < fixedStr.length(); i++) {
							sumStr += fixedStr.charAt(i);
						}
					} else {
						for(int i = fixedStr.length() - 1; i > fixedStr.length() - bombLength; i--) {
							sumStr += fixedStr.charAt(i);
						}
					}
					continue;
				}
			}
			
			sumStr -= fixedStr.charAt(fixedStr.length() - bombLength);
		}
		
		if(fixedStr.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(fixedStr.toString());
		}
	}// end main
}
