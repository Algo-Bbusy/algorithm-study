package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2605_줄세우기_박기윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int studentNum = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> order = new ArrayList<>();
		for (int i = 0; i < studentNum; i++) {
			int num = Integer.parseInt(st.nextToken());
			order.add(i - num, i + 1);
		}
		order.stream().forEach(n -> sb.append(n + " "));
		System.out.println(sb.toString());
		br.close();
	}

}
