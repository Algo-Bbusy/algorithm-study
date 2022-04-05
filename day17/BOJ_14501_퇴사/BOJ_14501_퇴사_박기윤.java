package Day17;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_14501_퇴사_박기윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int schedule[][] = new int[N + 1][2];
		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
		}

		int cost[] = new int[N + 2];
		for (int i = N; i >= 1; i--) {
			int tmp = i + schedule[i][0];
			if (tmp > N + 1) // 퇴사 전 상담을 끝낼 수 없는 경우
				cost[i] = cost[i + 1];
			else
				cost[i] = Math.max(cost[i + 1], cost[tmp] + schedule[i][1]);
		}
		
		bw.write(Integer.toString(cost[1]));
		br.close();
		bw.flush();
		bw.close();

	}

}
