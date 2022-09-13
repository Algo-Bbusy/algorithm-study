import java.util.*;

public class Programmers_2022_Kakao_양과늑대_어정윤 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}}));
        System.out.println(solution(new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}));
    }

    private static final int SHEEP = 0;
    private static final int WOLF = 1;

    private static List<Integer>[] binaryTree;
    private static boolean[][][] isVisited;
    private static int maxSheepCount;

    public static int solution(int[] info, int[][] edges) {
        int infoLength = info.length;
        binaryTree = new ArrayList[infoLength];
        for (int i = 0; i < infoLength; i++) {
            binaryTree[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            binaryTree[from].add(to);
            binaryTree[to].add(from);
        }
        isVisited = new boolean[infoLength][infoLength + 1][infoLength + 1];
        getCatchableSheep(info, 0, 0, 0);
        return maxSheepCount;
    }

    private static void getCatchableSheep(int[] info, int current, int sheep, int wolves) {
        if (info[current] == SHEEP) {
            sheep++;
        } else if (info[current] == WOLF) {
            wolves++;
        }

        if (sheep <= wolves) {
            return;
        }

        maxSheepCount = Math.max(maxSheepCount, sheep);
        for (int i = 0; i < binaryTree[current].size(); i++) {
            int next = binaryTree[current].get(i);
            int currentValue = info[current];
            if (!isVisited[next][sheep][wolves]) {
                isVisited[current][sheep][wolves] = true;
                info[current] = -1;
                getCatchableSheep(info, next, sheep, wolves);
                info[current] = currentValue;
                isVisited[current][sheep][wolves] = false;
            }
        }
    }
}
