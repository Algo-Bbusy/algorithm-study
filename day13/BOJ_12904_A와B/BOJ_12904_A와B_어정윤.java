import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12904_A와B_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        String t = bufferedReader.readLine();
        int lastIndex = t.length() - 1;
        int sLength = s.length();
        boolean isLast = true;
        while (t.length() != sLength) {
            char lastCharacter;
            if (isLast) {
                lastCharacter = t.charAt(lastIndex);
                t = t.substring(0, lastIndex);
            } else {
                lastCharacter = t.charAt(0);
                t = t.substring(1);
            }
            lastIndex--;

            if (lastCharacter == 'B'){
                isLast = !isLast;
            }
        }

        if (!isLast) {
            t = new StringBuffer(t).reverse().toString();
        }

        if (t.equals(s)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
