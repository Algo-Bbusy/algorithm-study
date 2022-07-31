import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20437_문자열게임2_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String w = bufferedReader.readLine();
            int k = Integer.parseInt(bufferedReader.readLine());
            int[] alphabet = new int[26];
            int minLength = w.length() + 1;
            int maxLength = -1;
            int start = 0;
            int end = 0;
            int length = 1;
            while (start < w.length()) {
                char startAlphabet = w.charAt(start);
                char endAlphabet = w.charAt(end);
                int index = startAlphabet - 97;
                if (++alphabet[index] == k) {
                    minLength = Math.min(minLength, length);
                    if (startAlphabet == endAlphabet) {
                        maxLength = Math.max(maxLength, length);
                    }
                    start++;
                    length--;
                }
                end++;
                length++;
            }
            if (maxLength != -1) {
                stringBuilder.append(minLength)
                        .append(" ")
                        .append(maxLength);
            } else {
                stringBuilder.append(maxLength);
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }
}
