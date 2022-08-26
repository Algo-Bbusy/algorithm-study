package AlgoStudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PRO_영어끝말잇기 {
	public static void main(String[] args) {
		int n = 2;
		String[] words = { "hello", "one", "even", "never", "now", "world", "draw" };
		solution(n, words);
		System.out.println(Arrays.toString(solution(n, words)));
	}

	public static int[] solution(int n, String[] words) {
		int[] answer = new int[2];
		int people = 1;

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < words.length; i++) {
			String word = words[i]; // 현재 단어

			if (list.size() > 0) {
				String beforeWord = words[i - 1]; // 이전 단어
				// 출력 : list안에 동일한 단어가 있거나 전 단어의 끝 알파벳과 첫 알파벳이 일치하지 않을 경우
				if (list.contains(word) || beforeWord.charAt(beforeWord.length() - 1) != word.charAt(0)) {
					answer[0] = people;
					answer[1] = i / n + 1;
					return answer;
				}
			}

			list.add(word);
			people++;

			if (people > n)
				people = 1;
		}

		return answer;
	}
}