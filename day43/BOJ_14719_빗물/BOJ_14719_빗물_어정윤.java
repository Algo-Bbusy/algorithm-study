import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_빗물_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        stringTokenizer.nextToken();
        int width = Integer.parseInt(stringTokenizer.nextToken());
        int[] blocks = new int[width];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int maxBlock = 0;
        for (int i = 0; i < width; i++) {
            int block = Integer.parseInt(stringTokenizer.nextToken());
            blocks[i] = block;
            if (block > blocks[maxBlock]) {
                maxBlock = i;
            }
        }
        int rain = 0;
        int previous = blocks[0];
        for (int i = 1; i < maxBlock; i++) {
            int block = blocks[i];
            if (previous <= block) {
                previous = block;
                continue;
            }
            rain += previous - block;
        }
        previous = blocks[width - 1];
        for (int i = width - 2; i > maxBlock; i--) {
            int block = blocks[i];
            if (previous <= block) {
                previous = block;
                continue;
            }
            rain += previous - blocks[i];
        }
        System.out.println(rain);
    }
}
