import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14891_톱니바퀴_어정윤 {

    private static final int COGWHEEL_NUM = 4;
    private static final int MET_SIDE_NUM = 3;
    private static final int SAWTOOTH = 8;
    private static final int CLOCKWISE = 1;
    private static final int COUNTERCLOCKWISE = -1;
    private static final int[][] COGWHEELS = new int[COGWHEEL_NUM + 1][SAWTOOTH];
    private static final int[] TOPS = new int[COGWHEEL_NUM + 1];
    private static final boolean[] IS_SAME_SIDES = new boolean[MET_SIDE_NUM + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= COGWHEEL_NUM; i++) {
            String input = bufferedReader.readLine();
            for (int j = 0; j < SAWTOOTH; j++) {
                COGWHEELS[i][j] = input.charAt(j) - 48;
            }
        }

        checkMetSides();

        int rotationNum = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < rotationNum; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int rotationCogwheel = Integer.parseInt(stringTokenizer.nextToken());
            int rotationDirection = Integer.parseInt(stringTokenizer.nextToken());
            rotate(rotationCogwheel, rotationDirection);
            rotateSides(rotationCogwheel, rotationDirection);
            checkMetSides();
        }

        int score = 0;
        for (int i = 1; i <= COGWHEEL_NUM; i++) {
            score += COGWHEELS[i][TOPS[i]] * (1 << (i-1));
        }
        System.out.println(score);
    }

    private static void rotateSides(int rotationCogwheel, int rotationDirection) {
        int nextRotationDirection = rotationDirection;
        for (int cogwheel = rotationCogwheel - 1; cogwheel >= 1; cogwheel--) {
            if (!IS_SAME_SIDES[cogwheel]) {
                nextRotationDirection = getNextDirection(nextRotationDirection, cogwheel);
                rotate(cogwheel, nextRotationDirection);
            } else {
                break;
            }
        }

        nextRotationDirection = rotationDirection;
        for (int cogwheel = rotationCogwheel + 1; cogwheel <= COGWHEEL_NUM; cogwheel++) {
            if (!IS_SAME_SIDES[cogwheel - 1]) {
                nextRotationDirection = getNextDirection(nextRotationDirection, cogwheel);
                rotate(cogwheel, nextRotationDirection);
            } else {
                break;
            }
        }
    }

    private static void rotate(int rotationCogwheel, int rotationDirection) {
        if (rotationDirection == CLOCKWISE) {
            TOPS[rotationCogwheel] = (TOPS[rotationCogwheel] + 7) % SAWTOOTH;
        } else {
            TOPS[rotationCogwheel] = (TOPS[rotationCogwheel] + 1) % SAWTOOTH;
        }
    }

    private static void checkMetSides() {
        for (int i = 1; i <= COGWHEEL_NUM - 1; i++) {
            if (COGWHEELS[i][(TOPS[i] + 2) % SAWTOOTH] == COGWHEELS[i + 1][(TOPS[i + 1] + 6) % SAWTOOTH]) {
                IS_SAME_SIDES[i] = true;
            } else {
                IS_SAME_SIDES[i] = false;
            }
        }
    }

    private static int getNextDirection(int nextRotationDirection, int cogwheel) {
        if (nextRotationDirection == CLOCKWISE) {
            return COUNTERCLOCKWISE;
        } else {
            return CLOCKWISE;
        }
    }
}
