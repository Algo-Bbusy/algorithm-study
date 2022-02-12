import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14696_딱지놀이_어정윤 {

    private static final int ROUND = 2;
    private static final int CARD_TYPE = 4;

    private static int[] playerA;
    private static int[] playerB;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            playerA = new int[CARD_TYPE+1];
            playerB = new int[CARD_TYPE+1];
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int cardNumA = Integer.parseInt(stringTokenizer.nextToken());
            for (int k = 0; k < cardNumA; k++) {
                playerA[Integer.parseInt(stringTokenizer.nextToken())]++;
            }
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int cardNumB = Integer.parseInt(stringTokenizer.nextToken());
            for (int k = 0; k < cardNumB; k++) {
                playerB[Integer.parseInt(stringTokenizer.nextToken())]++;
            }
            play();
        }
    }

    private static void play() {
        String winner = "D";
        for (int i = CARD_TYPE; i > 0; i--) {
            if (playerA[i] != playerB[i]) {
                if (playerA[i] > playerB[i]) {
                    winner = "A";
                } else if (playerA[i] < playerB[i]) {
                    winner = "B";
                } else {
                    continue;
                }
                break;
            }
        }
        System.out.println(winner);
    }
}
