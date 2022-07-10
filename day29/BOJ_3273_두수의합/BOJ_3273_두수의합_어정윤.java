import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273_두수의합_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] numbers = new int[n];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int x = Integer.parseInt(bufferedReader.readLine());
        Arrays.sort(numbers);

        int start = 0;
        int end = n - 1;
        int cnt = 0;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if (sum > x) {
                end--;
            } else if (sum < x) {
                start++;
            } else {
                cnt++;
                start++;
            }
        }
        System.out.println(cnt);
    }
}
