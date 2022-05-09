import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmers_2021_Kakao_신규아이디추천_어정윤 {

    public String solution(String new_id) {
        // step 1. 대문자 -> 소문자
        String stepOne = new_id.toLowerCase();

        // step 2. 소문자, 빼기, 밑줄, 마침표만 남기기
        Matcher matcher = Pattern.compile("[a-z0-9-_.]*").matcher(stepOne);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            stringBuilder.append(matcher.group());
        }
        String stepTwo = stringBuilder.toString();

        // step 3. 연속된 마침표 하나의 마침표로 치환
        String stepThree = Pattern.compile("[.][.]+")
                .matcher(stepTwo)
                .replaceAll(".");

        // step 4. 마침표가 처음이나 끝에 위치하면 제거
        String stepFour = stepThree;
        if (stepFour.startsWith(".")) {
            stepFour = stepFour.substring(1);
        }
        if (stepFour.endsWith(".")) {
            stepFour = stepFour.substring(0, stepFour.length() - 1);
        }

        // step 5. 빈 문자열이면 "a" 대입
        String stepFive = stepFour;
        if (stepFive.isEmpty()) {
            stepFive = "a";
        }

        // step 6. 길이가 16자 이상이면 15개 문자 이외 문자 제거
        String stepSix = stepFive;
        if (stepSix.length() > 15) {
            stepSix = stepSix.substring(0, 15);
            if (stepSix.endsWith(".")) {
                stepSix = stepSix.substring(0, 14);
            }
        }

        // step 7. 길이가 2자 이하면 마지막 문자를 길이가 3이될 때까지 반복해서 붙임
        String answer = stepSix;
        if (answer.length() < 3) {
            stringBuilder.setLength(0);
            stringBuilder.append(answer);
            char last = answer.charAt(answer.length() - 1);
            while (stringBuilder.length() < 3) {
                stringBuilder.append(last);
            }
            answer = stringBuilder.toString();
        }
        return answer;
    }
}
