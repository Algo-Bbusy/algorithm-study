package com.ssafy.study.homework25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro_문자열압축_신영제 {
	
	static int min;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		String str = br.readLine();
		
		solution(str);
		
		System.out.println(min);
	}

	public static int solution(String s) {
		int S = s.length();
		int half = S/2;
		min = Integer.MAX_VALUE;// return값

		if (S == 1)
			return 1;

		// i : 문자 쪼개는 길이
		for (int i = 1; i <= half; i++) { //문자열의 절반만 반복을 돌것. 절반 넘어가면 반복되는 문자열 없다.
			String sum = "";
			String newStr = "";
			int cnt = 1;

			for (int j = 0; j < S / i; j++) { //i길이씩 쪼개어 사용.
				String sumNext = s.substring(i * j, i * (j + 1));

				// 문자를 압축할 수 있으면 cnt++ 
				if (sum.equals(sumNext)) 
					cnt++;
				else { // 그렇지 못하면 상태업데이트. 
					if (cnt > 1)
						newStr += (cnt + sum);
					else
						newStr += sum;
					sum = sumNext;
					cnt = 1;
				}
			}

			if (cnt > 1)
				newStr += (cnt + sum);
			else
				newStr += sum;

			// 나머지 남았을 경우
			if (S % i != 0)
				newStr += s.substring(S - (S % i), S);

			min = Math.min(newStr.length(), min);

		}

		return min;
	}

}