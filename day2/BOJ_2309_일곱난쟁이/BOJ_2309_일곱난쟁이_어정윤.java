import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2309_일곱난쟁이_어정윤 {

    private static List<Integer> list = new ArrayList<>();
    private static int[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        heights = new int[9];
        for (int i = 0; i < 9; i++) {
            heights[i] = Integer.parseInt(bufferedReader.readLine());
        }
        all: for (int i = 0; i < 8; i++) {
            for (int j = i+1; j < 9; j++) {
                int sum = 0;
                for (int k = 0; k < 9; k++) {
                    if (k != i && k != j) {
                        sum += heights[k];
                        list.add(heights[k]);
                    }
                }
                if (sum == 100) {
                    break all;
                } else {
                    list.clear();
                }
            }
        }
        list.stream()
                .sorted()
                .forEach(System.out::println);
    }
}
