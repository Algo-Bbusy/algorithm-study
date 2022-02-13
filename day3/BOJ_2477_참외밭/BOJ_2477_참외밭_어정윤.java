import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2477_참외밭_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int fruitNum = Integer.parseInt(bufferedReader.readLine());
        int[][] field = new int[6][2];
        for (int i = 0; i < 6; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int direction = Integer.parseInt(stringTokenizer.nextToken());
            int length = Integer.parseInt(stringTokenizer.nextToken());
            field[i] = new int[]{direction, length};
        }

        int smallerWidth;
        int smallerHeight;
        int largerWidth;
        int largerHeight;
        int idx = 0;
        while (true) {
            if (field[idx % 6][0] == field[(idx + 2) % 6][0] && field[(idx + 1) % 6][0] == field[(idx + 3) % 6][0]) {
                smallerHeight = field[(idx + 1) % 6][1];
                smallerWidth = field[(idx + 2) % 6][1];
                largerHeight = field[idx % 6][1] + field[(idx + 2) % 6][1];
                largerWidth = field[(idx + 1) % 6][1] + field[(idx + 3) % 6][1];
                break;
            }
            idx++;
        }
        int area = largerHeight * largerWidth - smallerHeight * smallerWidth;
        System.out.println(area * fruitNum);
    }
}
