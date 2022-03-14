import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14889_스타트와링크_어정윤 {

    private static final List<Integer> TEAM_START = new ArrayList<>();
    private static final List<Integer> TEAM_LINK = new ArrayList<>();

    private static int n;
    private static int teamNum;
    private static int[][] adjMatrix;
    private static int[] teams;
    private static int minGap;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        adjMatrix = new int[n+1][n+1];
        teams = new int[n+1];

        for (int i = 1; i <= n; i++) {
            teams[i] = i;
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= n; j++) {
                adjMatrix[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        teamNum = n/2;
        minGap = Integer.MAX_VALUE;
        combination(0, 1);
        System.out.println(minGap);
    }

    private static void combination(int cnt, int start) {
        if (cnt == teamNum) {
            findTeamLink();
            updateMinGap();
        }

        for (int i = start; i <= n; i++) {
            TEAM_START.add(i);
            combination(cnt+1, i+1);
            TEAM_START.remove(TEAM_START.indexOf(i));
        }
    }

    private static void updateMinGap() {
        int teamStartAbility = 0;
        int teamLinkAbility = 0;
        for (int i = 0; i < teamNum-1; i++) {
            for (int j = i+1; j < teamNum; j++) {
                int startFrom = TEAM_START.get(i);
                int startTo = TEAM_START.get(j);
                if (startFrom == startTo) {
                    continue;
                }
                int linkFrom = TEAM_LINK.get(i);
                int linkTo = TEAM_LINK.get(j);
                teamStartAbility += adjMatrix[startFrom][startTo] + adjMatrix[startTo][startFrom];
                teamLinkAbility += adjMatrix[linkFrom][linkTo] + adjMatrix[linkTo][linkFrom];
            }
        }
        minGap = Math.min(minGap, Math.abs(teamStartAbility - teamLinkAbility));
    }

    private static void findTeamLink() {
        TEAM_LINK.clear();
        for (int i = 1; i <= n; i++) {
            if (!TEAM_START.contains(teams[i])) {
                TEAM_LINK.add(i);
            }
        }
    }
}
