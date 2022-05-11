package com.ssafy.study.homework25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro_신규아이디추천_신영제 {
	
	static String answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		String str = br.readLine();

		solution(str);
		System.out.println(answer);
	}

	public static String solution(String new_id) {
		// 1단계 소문자를 치환하는 함수 사용.
		answer = new_id.toLowerCase();

		// 2단계 : replaceAll을 사용하여 값을 바꾸어 준다.
		// ^ : 아니라면
		// a-z 영어 소문자, 0부터9, -, _, .
		answer = answer.replaceAll("[^-_.a-z0-9]", "");

		// 3단계 : x{n} x가 n개 이상인 정규표현식 사용하여 변경한다.
		answer = answer.replaceAll("[.]{2,}", ".");

		// 4단계 :
		// ^ : 문자열 시작된다. $ : 문자열 종료된다.
		// | or 연산 수행.
		answer = answer.replaceAll("^[.]|[.]$", "");

		// 5단계 아무것도 없다면 -> 길이가 0이면 a를 추가.
		if (answer.length() == 0)
			answer = "a";

		// 6단계
		// substring 으로 0부터 15까지 자른다.
		// 뒤에 .으로 시작할 수 있으니 . 제거 해준다.
		if (answer.length() >= 16) {
			answer = answer.substring(0, 15).replaceAll("^[.]|[.]$", "");
		}

		// 7단계
		// 마지막 문자를 붙여 길이가 3이 될때까지 반복.
		while (answer.length() < 3) {
			answer += answer.charAt(answer.length() - 1);
		}

		return answer;
	}
}