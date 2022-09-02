import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Programmers_summer&winter_internship_영어끝말잇기_어정윤 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"})));
        System.out.println(Arrays.toString(solution(5, new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"})));
        System.out.println(Arrays.toString(solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"})));
    }

    public static int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> toldWords = new HashSet<>();
        char lastAlphabetOfPreviousWord = words[0].charAt(0);
        for (int turn = 0, length = words.length; turn < length; turn++) {
            String word = words[turn];
            if (toldWords.contains(word) || lastAlphabetOfPreviousWord != word.charAt(0)) {
                updateAnswer(n, answer, turn);
                break;
            }
            lastAlphabetOfPreviousWord = word.charAt(word.length() - 1);
            toldWords.add(word);
        }
        return answer;
    }

    private static void updateAnswer(int n, int[] answer, int turn) {
        answer[0] = turn % n + 1;
        answer[1] = (int) Math.ceil((double) (turn + 1) / n);
    }
}
