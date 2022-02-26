import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BOJ_2635_수이어가기_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int n = Integer.parseInt(bufferedReader.readLine());
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> numbers = new ArrayList<>();
            numbers.add(n);
            numbers.add(i);
            int x = n;
            int y = i;
            int gap;
            while ((gap = x - y) >= 0) {
                numbers.add(gap);
                x = y;
                y = gap;
            }
            list.add(numbers);
        }
        list.sort(Comparator.comparing(List::size));
        List<Integer> answer = list.get(n - 1);
        stringBuilder.append(answer.size())
                .append("\n");
        for (int number : answer) {
            stringBuilder.append(number)
                    .append(" ");
        }
        System.out.println(stringBuilder);
    }
}
