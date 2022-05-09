public class Programmers_2020_Kakao_문자열압축_어정윤 {

    public int solution(String s) {
        int answer = s.length();
        int len = answer;
        int end = answer / 2;
        int compressCnt = 1;
        StringBuilder compress = new StringBuilder();
        while (compressCnt <= end) {
            compress.setLength(0);

            int idx = 0;
            while (idx < len) {
                int cnt = 1;
                if (idx + compressCnt > len) {
                    compress.append(s.substring(idx));
                    if (answer > compress.length()) {
                        answer = compress.length();
                    }
                    break;
                }
                String character = s.substring(idx, idx+=compressCnt);
                while (idx+compressCnt <= len && character.equals(s.substring(idx, idx + compressCnt))) {
                    cnt++;
                    idx += compressCnt;
                }
                if (cnt > 1) {
                    compress.append(cnt);
                }
                compress.append(character);
            }
            if (answer > compress.length()) {
                answer = compress.length();
            }
            compressCnt++;
        }
        return answer;
    }
}
