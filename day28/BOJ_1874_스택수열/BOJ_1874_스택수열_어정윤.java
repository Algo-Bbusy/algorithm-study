import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1874_스택수열_어정윤 {

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final StringBuilder ANSWER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(bufferedReader.readLine());
        }
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        for (int input = 1; input <= n; input++) {
            stack.push(input);
            saveOperator(PLUS);
            while (!stack.isEmpty() && stack.peek() == sequence[idx]) {
                stack.pop();
                idx++;
                saveOperator(MINUS);
            }
        }
        if (idx == n) {
            System.out.println(ANSWER);
        } else {
            System.out.println("NO");
        }
    }

    private static void saveOperator(String operator) {
        ANSWER.append(operator)
                .append("\n");
    }
}
