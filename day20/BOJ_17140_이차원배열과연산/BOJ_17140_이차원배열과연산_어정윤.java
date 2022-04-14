import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17140_이차원배열과연산_어정윤 {

    private static final int SIZE = 100;
    private static final int[][] A = new int[SIZE][SIZE];
    private static final int[][] REVERSED_A = new int[SIZE][SIZE];

    private static int currentColumnNumber;
    private static int currentRowNumber;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int r = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int c = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        int k = Integer.parseInt(stringTokenizer.nextToken());
        currentColumnNumber = 3;
        currentRowNumber = 3;
        for (int i = 0; i < 3; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = REVERSED_A[j][i] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        int time = 0;
        while (A[r][c] != k) {
            if (time > 100) {
                time = -1;
                break;
            }

            if (currentColumnNumber >= currentRowNumber) {
                operate(A, currentColumnNumber, true);
            } else {
                operate(REVERSED_A, currentRowNumber, false);
            }
            time++;
        }
        System.out.println(time);
    }

    private static void operate(int[][] array, int end, boolean isROperation) {
        int[][] tempA = copy(array);    // 배열 복사(참조 끊기)
        int currentEnd = 0;
        for (int i = 0; i < end; i++) {
            int[] uniqueNumbers = Arrays.stream(tempA[i]).distinct().sorted().toArray();    // i번째 행에 나오는 숫자들을 오름차순 정렬해 배열에 저장
            List<int[]> nextA = new ArrayList<>();  // 다음 배열에 저장될 숫자와 숫자등장횟수를 저장할 리스트
            // 각 숫자의 등장횟수를 세서 숫자와 등장횟수를 nextA에 추가
            for (int j = 1, uniqueNumbersLength = uniqueNumbers.length; j < uniqueNumbersLength; j++) { // 0이 포함되어 있으므로 1~uniqueNumbers의 길이-1까지 탐색
                int uniqueNumber = uniqueNumbers[j];
                int count = 0;
                for (int k = 0; k < end; k++) {
                    if (tempA[i][k] == uniqueNumber) {
                        count++;
                    }
                }
                nextA.add(new int[]{uniqueNumber, count});
            }

            // 숫자의 등장횟수를 기준으로 오름차순 정렬, 등장횟수가 같으면 숫자를 기준으로 오름차순 정렬
            nextA.sort((o1, o2) -> {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            });

            // nextA에 저장된 값들을 A와 REVERSED_A에 저장
            int idx = 0;
            for (int[] numbers : nextA) {
                if (idx == SIZE) {
                    if (isROperation) {
                        currentRowNumber = SIZE;
                    } else {
                        currentColumnNumber = SIZE;
                    }
                    break;
                }

                if (isROperation) {
                    A[i][idx] = REVERSED_A[idx++][i] = numbers[0];
                    A[i][idx] = REVERSED_A[idx++][i] = numbers[1];
                } else {
                    A[idx][i] = REVERSED_A[i][idx++] = numbers[0];
                    A[idx][i] = REVERSED_A[i][idx++] = numbers[1];
                }
            }

            // 저장 후 인덱스부터 현재 열/행 개수까지 남은 배열은 0으로 채워줌
            for (int j = idx; j < end; j++) {
                if (isROperation) {
                    A[i][j] = REVERSED_A[j][i] = 0;
                } else {
                    A[j][i] = REVERSED_A[i][j] = 0;
                }
            }

            // 현재 배열의 길이가 배열의 끝값보다 크면 갱신
            if (nextA.size() * 2 > currentEnd) {
                currentEnd = nextA.size() * 2;
            }
        }

        // 배열의 끝값을 열/행에 갱신
        if (isROperation) {
            currentRowNumber = currentEnd;
        } else {
            currentColumnNumber = currentEnd;
        }
    }

    private static int[][] copy(int[][] array) {
        int[][] copiedArray = new int[SIZE][SIZE];
        for (int i = 0, arrayLength = array.length; i < arrayLength; i++) {
            copiedArray[i] = array[i].clone();
        }
        return copiedArray;
    }
}
