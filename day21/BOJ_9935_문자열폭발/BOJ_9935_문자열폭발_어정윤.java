import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9935_문자열폭발_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        String explosionString = bufferedReader.readLine();
        while (input.contains(explosionString)) {
            input = input.replaceAll(explosionString, "");
        }

        if (input.equals("")) {
            input = "FRULA";
        }
        System.out.println(input);
    }
}
