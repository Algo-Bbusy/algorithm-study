import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10250_ACM호텔_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int height = Integer.parseInt(stringTokenizer.nextToken());
            stringTokenizer.nextToken();
            int guest = Integer.parseInt(stringTokenizer.nextToken());
            int floor = guest % height;
            if (floor == 0) {
                floor = height;
            }
            int room = (int) Math.ceil((double) guest / height);
            stringBuilder.append(floor * 100 + room)
                    .append("\n");
        }
        System.out.println(stringBuilder);
    }
}
