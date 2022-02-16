import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2491_수열_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int max = 1;
        int increaseCount = 1;
        int decreaseCount = 1;
        for (int i = 0; i < n-1; i++) {
            if (numbers[i] <= numbers[i+1]) {
                increaseCount++;
            } else {
                increaseCount = 1;
            }

            if (numbers[n-i-1] <= numbers[n-i-2]) {
                decreaseCount++;
            } else {
                decreaseCount = 1;
            }
            int maxVariation = Math.max(increaseCount, decreaseCount);
            max = Math.max(max, maxVariation);
        }
        System.out.println(max);
    }
}
