import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2527_직사각형_어정윤 {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static final char RECTANGLE = 'a';
    private static final char LINE = 'b';
    private static final char DOT = 'c';
    private static final char NO_OVERLAP = 'd';
    private static final int X = 0;
    private static final int Y = 1;
    private static final int P = 2;
    private static final int Q = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int[] firstSquare = new int[4];
            int[] secondSquare = new int[4];
            for (int j = 0; j < 4; j++) {
                firstSquare[j] = Integer.parseInt(stringTokenizer.nextToken());
            }
            for (int j = 0; j < 4; j++) {
                secondSquare[j] = Integer.parseInt(stringTokenizer.nextToken());
            }

            classify(firstSquare, secondSquare);
        }
        System.out.println(STRING_BUILDER);
    }

    private static void classify(int[] firstSquare, int[] secondSquare) {
        if (firstSquare[P] < secondSquare[X] || firstSquare[Q] < secondSquare[Y]
                || firstSquare[X] > secondSquare[P] || firstSquare[Y] > secondSquare[Q]) {
            STRING_BUILDER.append(NO_OVERLAP);
        } else if ((firstSquare[P] == secondSquare[X] || firstSquare[X] == secondSquare[P])
                && (firstSquare[Y] == secondSquare[Q] || firstSquare[Q] == secondSquare[Y])) {
            STRING_BUILDER.append(DOT);
        } else if (firstSquare[P] == secondSquare[X] || firstSquare[X] == secondSquare[P]
                || firstSquare[Q] == secondSquare[Y] || firstSquare[Y] == secondSquare[Q]) {
            STRING_BUILDER.append(LINE);
        } else {
            STRING_BUILDER.append(RECTANGLE);
        }
        STRING_BUILDER.append("\n");
    }
}
