import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2559_수열_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int[] temperatures = new int[n];
        for (int i = 0; i < n; i++) {
            temperatures[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n-k+1; i++) {
            int sum = 0;
            for (int j = i; j < i+k; j++) {
                sum += temperatures[j];
            }
            maxSum = Math.max(maxSum, sum);
        }
        System.out.println(maxSum);
    }
}
