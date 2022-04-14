import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ_9440_숫자더하기_어정윤 {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        makeSmallestNumber();
        System.out.println(STRING_BUILDER);
    }

    private static void makeSmallestNumber() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        while (true) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int n = Integer.parseInt(stringTokenizer.nextToken());
            if (n == 0) {
                return;
            }

            List<Integer> numbers = new ArrayList<>();
            int zeroCount = 0;
            for (int i = 0; i < n; i++) {
                int number = Integer.parseInt(stringTokenizer.nextToken());
                if (number == 0) {  // 0 개수 count
                    zeroCount++;
                }
                numbers.add(number);
            }

            // 오름차순 정렬
            numbers = numbers.stream().sorted().collect(Collectors.toList());

            int answer;
            // 각 숫자의 첫 번째 자릿수는 0이 아닌 숫자들로 세팅
            int number1 = numbers.get(zeroCount);
            int number2 = numbers.get(zeroCount + 1);
            // 첫 번째 자릿수로 사용된 숫자를 리스트에서 삭제
            numbers.remove(zeroCount);
            numbers.remove(zeroCount);

            // 리스트를 탐색하며 각 숫자의 자릿수를 앞에서부터 채워감
            int size = numbers.size();
            for (int i = 0; i < size; i += 2) {
                number1 *= 10;
                number1 += numbers.get(i);
                if (i == size-1) {  // i가 리스트의 길이-1과 같으면 더 이상 숫자가 남아있지 않으므로 종료
                    break;
                }
                number2 *= 10;
                number2 += numbers.get(i + 1);
            }
            answer = number1 + number2;
            STRING_BUILDER.append(answer)
                    .append("\n");
        }
    }
}
