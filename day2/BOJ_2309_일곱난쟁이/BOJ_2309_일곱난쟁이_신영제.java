package com.zerojei.stack_two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class BOJ_2309 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<>();

		int sum = 0;
		for (int i = 0; i < 9; i++) {
			list.add(Integer.parseInt(br.readLine()));
			sum += list.get(i);
		}


// (두명합) - (전체 합) = 100  => 두명을 제거!
// 두명 one, two  전체 합 sum  (one + two) - sum = 100 이면 해당하는 인덱스 값제거	
// 두명 찾기 

		// 두명 찾기

		int ans = 0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				int one = list.get(i);
				int two = list.get(j);
				ans = sum - one - two;
				if (ans == 100) {
					list.remove(Integer.valueOf(i));
					list.remove(Integer.valueOf(j));
				}
			}
		}
		// 키 오름차순 정렬.
		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
