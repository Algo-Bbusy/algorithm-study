import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11053_가장긴증가하는부분수열_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] arr = new int[n+1];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int maxLength = 0;
        int[] table = new int[n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && table[i] < table[j] + 1) {
                    table[i] = table[j] + 1;
                    maxLength = Math.max(maxLength, table[i]);
                }
            }
        }
        System.out.println(maxLength);
    }
}
