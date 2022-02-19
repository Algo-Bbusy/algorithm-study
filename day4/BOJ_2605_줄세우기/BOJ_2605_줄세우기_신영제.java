package com.ssafy.study.homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2605 { // 줄세우기

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 학생수 입력
		ArrayList<Integer> list_arr = new ArrayList<>();

		for (int i = 1; i <= N; i++) { // 1 ~ N까지 리스트 생성.
			list_arr.add(i); //이거 안만들면 오류남
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken()); //순서 입력
			list_arr.add(i - num, i); // 1 - 0 번째에 1삽입 -> 2 - 1 번째에 2삽입 -> 3 - 1 번째에 3삽입
									  // 뒤로 밀려남!
		}

		for (int i = 1; i <= N; i++) {
			System.out.print(list_arr.get(i) + " ");
		}

	}

}

/*
 * 특징 1) 매일 같이 앞자리에 앉은 학생들이 앞에 줄을 서 점심 먹음, -> 뒷자리는 점심을 늦게먹음 2) 한 줄로 줄을 선다. 첫 번째
 * 학생부터 차례로 번호름 뽑는다. 3) 첫 번째로 줄을 선 학생은 무조건 0번 번호를 받아 제일 앞에 줄을 선다. 4) 두 번째로 줄을 선
 * 학생은 0번 또는 1번 둘 중 하나의 번호를 뽑는다. -> 0번은 그자리 ---
 */
