import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1080_행렬_어정윤 {

    private static int n;
    private static int m;
    private static int minChange;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        int[][] A = new int[n][m];
        int[][] B = new int[n][m];
        input(bufferedReader, n, m, A);
        input(bufferedReader, n, m, B);
        if (n < 3 || m < 3) {
            if (!isSame(A, B)) {
                minChange = -1;
            }
        } else {
            // 구현X
        }
        System.out.println(minChange);
    }

    private static boolean isSame(int[][] A, int[][] B) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void input(BufferedReader bufferedReader, int n, int m, int[][] a) throws IOException {
        for (int i = 0; i < n; i++) {
            String inputLine = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = inputLine.charAt(j) - 48;
            }
        }
    }
}
