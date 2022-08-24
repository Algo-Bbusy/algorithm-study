import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Programmers_summer&winter_internship_영어끝말잇기_어정윤 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));
        System.out.println(Arrays.toString(solution(5, new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"})));
        System.out.println(Arrays.toString(solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"})));
    }

    public static int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Map<String, Integer> toldWord = new HashMap<>();
        int length = words.length;
        for (int turn = 0; turn < length; turn++) {
            String word = words[turn];
            if (toldWord.containsKey(word)) {
                updateAnswer(n, answer, turn);
                break;
            } else {
                toldWord.put(word, 1);
            }
        }
        if (answer[0] == 0) {
            for (int turn = 1; turn < length; turn++) {
                String currentWord = words[turn];
                String previousWord = words[turn - 1];
                if (previousWord.charAt(previousWord.length() - 1) != currentWord.charAt(0)) {
                    updateAnswer(n, answer, turn);
                    break;
                }
            }
        }
        return answer;
    }

    private static void updateAnswer(int n, int[] answer, int turn) {
        answer[0] = turn % n + 1;
        answer[1] = (int) Math.ceil((double) (turn + 1) / n);
    }
}
