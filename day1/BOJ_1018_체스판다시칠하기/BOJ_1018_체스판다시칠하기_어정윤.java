import java.util.Scanner;

public class BOJ_1018_체스판다시칠하기_어정윤 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String nextLine = scanner.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = nextLine.charAt(j);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= n-8; i++) {
            for (int j = 0; j <= m-8; j++) {
                int cnt = 0;
                if (board[i][j] == 'B') {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            int dx = i + x;
                            int dy = j + y;
                            if ((x + y) % 2 == 1 && board[dx][dy] != 'W') {
                                cnt++;
                            } else if ((x + y) % 2 == 0 && board[dx][dy] != 'B') {
                                cnt++;
                            }
                        }
                    }
                } else if (board[i][j] == 'W') {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            int dx = i + x;
                            int dy = j + y;
                            if ((x + y) % 2 == 1 && board[dx][dy] != 'B') {
                                cnt++;
                            } else if ((x + y) % 2 == 0 && board[dx][dy] != 'W') {
                                cnt++;
                            }
                        }
                    }
                }
                cnt = Math.min(cnt, 64 - cnt);
                min = Math.min(min, cnt);
            }
        }
        System.out.println(min);
    }
}
