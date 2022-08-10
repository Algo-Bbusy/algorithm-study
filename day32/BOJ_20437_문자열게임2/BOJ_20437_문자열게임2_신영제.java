package AlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20437_문자열게임2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 문자열 게임의 수.
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			String str = br.readLine();
			int K = Integer.parseInt(br.readLine());
			
			// k = 1일때 
			if (K == 1) { 
				System.out.println("1 1");
				continue;
			}
			// 알파벳 별 카운트 증가.
			int[] arr = new int[26];
			for (int j = 0; j < str.length(); j++) {
				arr[str.charAt(j) - 'a']++;
			}

			int min = Integer.MAX_VALUE;
			int max = -1;
			for (int j = 0; j < str.length(); j++) {
				// 알파벳이 k개보다 작으면 탐색할 필요 없다.
				if (arr[str.charAt(j) - 'a'] < K)
					continue;

				int count = 1;
				for (int l = j + 1; l < str.length(); l++) {
					if (str.charAt(j) == str.charAt(l))
						count++;
					if (count == K) {
						// k만큼 포함되는 순간 탐색을 안해도 된다.
						min = Math.min(min, l - j + 1);
						max = Math.max(max, l - j + 1);
						break;
					}
				}
			}
			if (min == Integer.MAX_VALUE || max == -1)
				System.out.println("-1");
			else
				System.out.println(min + " " + max);
		}
	}
}
