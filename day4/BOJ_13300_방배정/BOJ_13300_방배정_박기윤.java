package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_13300_방배정_박기윤 {
	static final int genderType = 2;
	static final int gradeType = 6;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int studentCnt[][] = new int[gradeType][genderType];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int gender = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			studentCnt[grade - 1][gender]++;
		}

		int minCnt = 0;
		for (int i = 0; i < gradeType; i++) {
			for (int j = 0; j < genderType; j++) {
				minCnt += Math.ceil((double) studentCnt[i][j] / K);
			}
		}
		bw.write(Integer.toString(minCnt));
		br.close();
		bw.flush();
		bw.close();
	}

}
