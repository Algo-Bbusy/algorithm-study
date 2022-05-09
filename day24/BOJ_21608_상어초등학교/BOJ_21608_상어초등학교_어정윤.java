import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_21608_상어초등학교_어정윤 {

    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] classroom = new int[n][n];
        int studentNum = (int) Math.pow(n, 2);
        List[] studentInfos = new List[studentNum + 1];
        for (int i = 0, studentInfosLength = studentInfos.length; i < studentInfosLength; i++) {
            studentInfos[i] = new ArrayList<>();
        }
        for (int i = 0; i < studentNum; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int student = Integer.parseInt(stringTokenizer.nextToken());
            int first = Integer.parseInt(stringTokenizer.nextToken());
            int second = Integer.parseInt(stringTokenizer.nextToken());
            int third = Integer.parseInt(stringTokenizer.nextToken());
            int fourth = Integer.parseInt(stringTokenizer.nextToken());

            studentInfos[student].add(first);
            studentInfos[student].add(second);
            studentInfos[student].add(third);
            studentInfos[student].add(fourth);

            int[] location = new int[4];
            location[0] = n;
            location[1] = n;
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (classroom[x][y] == 0) {
                        int favorite = 0;
                        int empty = 0;
                        for (int[] delta : DELTAS) {
                            int dx = x + delta[0];
                            int dy = y + delta[1];
                            if (dx >= 0 && dx < n && dy >= 0 && dy < n) {
                                if (studentInfos[student].contains(classroom[dx][dy])) {
                                    favorite++;
                                } else if (classroom[dx][dy] == 0) {
                                    empty++;
                                }
                            }
                        }
                        if (favorite > location[2]) {
                            location[0] = x;
                            location[1] = y;
                            location[2] = favorite;
                            location[3] = empty;
                        } else if (favorite == location[2] && empty > location[3]) {
                            location[0] = x;
                            location[1] = y;
                            location[3] = empty;
                        } else if (favorite == location[2] && empty == location[3] && location[0] > x) {
                            location[0] = x;
                            location[1] = y;
                        } else if (favorite == location[2] && empty == location[3] && location[0] == x && location[1] > y) {
                            location[1] = y;
                        }
                    }
                }
            }
            classroom[location[0]][location[1]] = student;
        }

        int total = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int student = classroom[x][y];
                int favorite = 0;
                for (int[] delta : DELTAS) {
                    int dx = x + delta[0];
                    int dy = y + delta[1];
                    if (dx >= 0 && dx < n && dy >= 0 && dy < n && studentInfos[student].contains(classroom[dx][dy])) {
                        favorite++;
                    }
                }
                if (favorite != 0) {
                    total += Math.pow(10, favorite - 1);
                }
            }
        }
        System.out.println(total);
    }
}
