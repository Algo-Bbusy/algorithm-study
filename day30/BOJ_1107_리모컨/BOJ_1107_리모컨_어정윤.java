import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1107_리모컨_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String n = bufferedReader.readLine();
        int m = Integer.parseInt(bufferedReader.readLine());
        Set<Integer> brokenNumbers = new HashSet<>();
        if (m > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int i = 0; i < m; i++) {
                brokenNumbers.add(Integer.parseInt(stringTokenizer.nextToken()));
            }
        }
        int minClick = Math.abs(Integer.parseInt(n) - 100);
        int closeNumber = 0;
        int clickNumberAndOperator = 0;
        for (int i = 0; i < n.length(); i++) {
            int button = n.charAt(i) - 48;
            while (brokenNumbers.contains(button)) {
                button--;

            }
            clickNumberAndOperator++;
            closeNumber *= 10;
            closeNumber += button;
        }
        clickNumberAndOperator += Integer.parseInt(n) - closeNumber;
        if (minClick >= 0) {
            System.out.println(Math.min(minClick, clickNumberAndOperator));
        } else {
            System.out.println(clickNumberAndOperator);
        }
    }
}
