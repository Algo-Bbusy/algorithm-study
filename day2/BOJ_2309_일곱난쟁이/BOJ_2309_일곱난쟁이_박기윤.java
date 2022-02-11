package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2309_일곱난쟁이_박기윤 {
	static int height[];
	static boolean isSelected[];
	static int selectedHeight[] = new int[7];
	static int size;

	public static void find(int count, int start, int sum) {
		if (count == 7) {
			if (sum == 100) {
				int i = 0, j = 0;
				while (i < size) {
					if (isSelected[i])
						selectedHeight[j++] = height[i];
					i++;
				}
			}
			Arrays.sort(selectedHeight);
			return;
		}

		for (int i = start; i < 9; i++) {
			isSelected[i] = true;
			find(count + 1, i + 1, sum + height[i]);
			isSelected[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		height = new int[9];
		size = height.length;
		for (int i = 0; i < size; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}
		isSelected = new boolean[9];
		find(0, 0, 0);
		for (int i = 0; i < 7; i++)
			sb.append(selectedHeight[i] + "\n");
		System.out.println(sb.toString());
	}

}
