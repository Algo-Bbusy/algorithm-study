import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_1541_잃어버린괄호_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String formula = bufferedReader.readLine();
        int answer = 0;
        if (!formula.contains("-")) {
            String[] split = formula.split("\\+");
            System.out.println(Arrays.toString(split));
            answer = Arrays.stream(split)
                    .filter(s -> !s.equals("+"))
                    .mapToInt(Integer::parseInt)
                    .sum();
        } else if (!formula.contains("+")) {
            String[] split = formula.split("-");
            answer = Integer.parseInt(split[0]);
            for (int i = 1; i < split.length; i++) {
                if (!split[i].equals("-")) {
                    answer -= Integer.parseInt(split[i]);
                }
            }
        } else {
            String[] numbers = formula.split("\\+|-");

            int numberIdx = 0;
            List<Integer> subtractions = new ArrayList<>();
            int sum = 0;
            for (int i = 0; i < formula.length(); i++) {
                if (formula.charAt(i) == '+') {
                    sum += Integer.parseInt(numbers[numberIdx++]);
                    if (numberIdx == numbers.length-1) {
                        sum += Integer.parseInt(numbers[numberIdx]);
                        subtractions.add(sum);
                    }
                } else if (formula.charAt(i) == '-') {
                    subtractions.add(Integer.parseInt(numbers[numberIdx++]));
                    if (numberIdx == numbers.length-1) {
                        subtractions.add(Integer.parseInt(numbers[numberIdx]));
                    }
                }
            }
            answer = subtractions.get(0);
            for (int i = 1; i < subtractions.size(); i++) {
                answer -= subtractions.get(i);
            }
        }
        System.out.println(answer);
    }
}
