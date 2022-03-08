import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14502_연구소_어정윤 {

    private static final int[][] DELTAS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final int EMPTY_SPACE = 0;
    private static final int WALL = 1;
    private static final int VIRUS = 2;
    private static final int WALL_COUNT = 3;
    private static final List<int[]> VIRUSES = new ArrayList<>();
    private static final List<int[]> EMPTY_SPACES = new ArrayList<>();

    private static int[][] laboratory;
    private static int maxSafeZone;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        laboratory = new int[n][m];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(stringTokenizer.nextToken());
                if (input == EMPTY_SPACE) {
                    EMPTY_SPACES.add(new int[]{i, j});
                } else if(input == VIRUS) {
                    VIRUSES.add(new int[]{i, j});
                }
                laboratory[i][j] = input;
            }
        }

        maxSafeZone = Integer.MIN_VALUE;
        setNewWalls(0, EMPTY_SPACES.size());
        System.out.println(maxSafeZone);
    }

    private static void setNewWalls(int cnt, int blanks) {
        if (cnt == WALL_COUNT) {
            int safeZone = getSafeZoneAfterSpreadVirus(blanks);
            maxSafeZone = Math.max(maxSafeZone, safeZone);
            return;
        }

        for (int[] newWall : EMPTY_SPACES) {
            if (laboratory[newWall[0]][newWall[1]] != EMPTY_SPACE) {
                continue;
            }
            laboratory[newWall[0]][newWall[1]] = WALL;
            setNewWalls(cnt+1, blanks-1);
            laboratory[newWall[0]][newWall[1]] = EMPTY_SPACE;
        }
    }

    private static int getSafeZoneAfterSpreadVirus(int blanks) {
        int[][] tempLaboratory = new int[n][m];
        for (int i = 0, tempLaboratoryLength = tempLaboratory.length; i < tempLaboratoryLength; i++) {
            tempLaboratory[i] = laboratory[i].clone();
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int[] virus : VIRUSES) {
            queue.offer(virus);
        }

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            for (int[] delta : DELTAS) {
                int dx = x + delta[0];
                int dy = y + delta[1];
                if (dx >= 0 && dx < n && dy >= 0 && dy < m && tempLaboratory[dx][dy] == EMPTY_SPACE) {
                    tempLaboratory[dx][dy] = VIRUS;
                    blanks--;
                    queue.offer(new int[]{dx, dy});
                }
            }
        }
        return blanks;
    }
}
