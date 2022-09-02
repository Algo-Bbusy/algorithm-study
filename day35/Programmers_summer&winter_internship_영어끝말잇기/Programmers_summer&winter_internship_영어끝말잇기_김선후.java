package summer_winter;

import java.util.HashSet;

public class Programmers_summer_winter_internship_영어끝말잇기 {
//    영어끝말잇기
//    Solution 1.
//    1. 끝말잇기를 진행하는 현재 참여자순서, 현재 차례, 마지막 단어 문자 초기화
//    2. 중복단어를 제거해주기 위한 HashSet suggested 사용
//    3. 제시된 단어배열 words 탐색
//    4-1. 현재 제시된 단어가 이전에 제시된 단어와 중복되거나 마지막 단어 문자와 같지 않다면 정답값갱신 후 반복문 탈출.
//    4-2. 마지막 단어 문자 갱신, 제시된 단어에 현재 제시된 단어 추가, 참여자순서와 차례 갱신
//    5. 정답값 반환
//    풀이시간 24분 17초
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        //n번 사람 순서
        int currentMember = 1;
        //n번 차례
        int currentRound = 1;
        //마지막 단어 문자
        char lastWord = words[0].charAt(0);
        //제시된 단어
        HashSet<String> suggested = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            if (suggested.contains(str) || str.charAt(0) != lastWord) {
                answer[0] = currentMember;
                answer[1] = currentRound;
                break;
            }
            lastWord = str.charAt(str.length() - 1);
            suggested.add(str);
            currentMember++;
            if (currentMember > n) {
                currentMember = 1;
                currentRound++;
            }
        }
        return answer;
    }
}
