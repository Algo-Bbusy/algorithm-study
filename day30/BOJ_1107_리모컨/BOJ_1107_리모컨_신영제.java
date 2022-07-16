package AlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1107_리모컨 {

	private static int N, M;
	private static boolean[] noButton;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 이동하고자 하는 채널
		N = Integer.parseInt(bf.readLine());
		// 고장난 버튼의 개수
		M = Integer.parseInt(bf.readLine());
		// 사용 못하는 버튼을 담기위한 배열
		noButton = new boolean[10];
		// 사용 못하는 버튼이 있다면 배열에 값 넣기
		if (M > 0) {
			st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < M; i++) {
				int a = Integer.parseInt(st.nextToken());
				noButton[a] = true;
			}
		}

		// +, - 버튼으로만 이동하는 경우.
		int minNum = Math.abs(N - 100);

		// +, -만을 이용한 최대 이동횟수
		for (int i = 0; i <= 1000000; i++) {
			int len = findNum(i); // 입력 가능한 번호에 대한 번호의 길이
			if (len > 0) {
				// 입력 가능한 번호+(+/-로 이동 가능한 횟수) vs (+/- 버튼만으로 이동 가능한 횟수)
				minNum = Math.min(len + Math.abs(N - i), minNum);
			}
		}

		System.out.println(minNum);

	}

	public static int findNum(int n) {
		int cnt = 0;
		
		if (n == 0) { // 입력값이 0인 경우
			if (noButton[0])
				return 0; // 사용 불가능하면 0 리턴
			else
				return 1; // 사용 가능하면 1 리턴
		}
		// 0보다 큰 수의 경우 자릿수를 하나씩 확인
		while (n > 0) { 
			if (noButton[n % 10]) return 0; // 현재 자릿수가 사용 불가능하면 0 리턴
			cnt++; // 현재 자릿수가 사용 가능하면 숫자 길이를 1만큼 증가시키고, 다음 자릿수로 이동
			n /= 10;
		}
		return cnt;
	}
}