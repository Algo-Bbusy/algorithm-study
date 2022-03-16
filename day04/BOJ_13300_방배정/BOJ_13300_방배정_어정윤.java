import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13300_방배정_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int[][] studentInfos = new int[7][2];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int s = Integer.parseInt(stringTokenizer.nextToken());
            int y = Integer.parseInt(stringTokenizer.nextToken());
            studentInfos[y][s]++;
        }

        int cnt = 0;
        for (int i = 1; i <= 6; i++) {
            for (int j = 0; j < 2; j++) {
                if (studentInfos[i][j] != 0) {
                    cnt++;
                    while (studentInfos[i][j] > k) {
                        studentInfos[i][j] -= k;
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
