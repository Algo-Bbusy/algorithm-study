import java.util.*;

public class Programmers_summer&winter_internship_영어끝말잇기_정세미 {
    public int[] solution(int n, String[] words) {
        Set<String> usedWords = new HashSet<>();
        int wordNum = words.length;
        char prevAlpha = words[0].charAt(0);
        int i = 0;
        for(; i < wordNum; ++i) {
            String cur = words[i];
            if(usedWords.contains(cur) || cur.charAt(0) != prevAlpha) {
                break;
            }
            usedWords.add(cur);
            prevAlpha = cur.charAt(cur.length() - 1);
        }
        
        int[] answer = (i == wordNum)
            ? new int[] {0, 0} : new int[] {i % n + 1, i / n + 1};
        
        return answer;
    }
}
