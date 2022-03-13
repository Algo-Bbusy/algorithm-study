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
        String[] numbers = formula.split("[+|-]");
        Stack<String> subtractions = new Stack<>();

        int numberIdx = 0;
        subtractions.push(numbers[numberIdx++]);
        int nextOperator = subtractions.peek().length();
        while (nextOperator < formula.length()) {
            if (formula.charAt(nextOperator) == '+') {
                subtractions.push(String.valueOf(Integer.parseInt(subtractions.pop()) + Integer.parseInt(numbers[numberIdx])));
            } else {
                subtractions.push(numbers[numberIdx]);
            }
            nextOperator += String.valueOf(numbers[numberIdx++]).length() + 1;
        }

        for (int i = subtractions.size(); i > 1; i--) {
            answer -= Integer.parseInt(subtractions.pop());
        }
        answer += Integer.parseInt(subtractions.pop());
        System.out.println(answer);
    }
}
