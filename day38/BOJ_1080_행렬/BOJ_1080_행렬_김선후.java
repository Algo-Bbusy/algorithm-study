package anystep;

import java.io.*;
import java.util.StringTokenizer;

public class Java_1080 {
    //행렬
    //메모리 14316 KB 시간 124 ms
    //행렬 A의 부분 행렬(3*3)을 변환하여 행렬 B를 만들 수 있는지 확인하는 문제.
    //행렬 A의 가장 첫 부분(0,0)부터 행렬 B와 비교하며 변환
    //Solution 1.
    //1. 행렬 A와 B를 입력받는다.
    //2. 행렬A(i,j)와 행렬(i,j)의 값이 다르면 (i,j) 기준 부분행렬 만든 후 변환.
    //2.1 주어지는 행렬이 (3*3)보다 작은 경우 변환 불가능하기에 배열의 동일성만 체크
    //3. 더 이상 부분행렬을 만들 수 없으면 비교 중단.
    //4. 비교 중단 후 행렬A 행렬B 가 같은지 확인.
    //풀이시간 : 1시간 01분 24초
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer stk;
    static int N,M,ans;
    static boolean[][] A,B;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        out.write(ans+"");
        out.flush();
        out.close();
        in.close();
    }
    private static void input() throws IOException {
        stk = new StringTokenizer(in.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        A = generateMatrix();
        B = generateMatrix();
    }
    private static boolean[][] generateMatrix() throws IOException {
        boolean[][] matrix = new boolean[N][M];
        for (int y = 0; y < N; y++) {
            String str = in.readLine();
            for (int x = 0; x < M; x++) {
                if (str.charAt(x) == '1') {
                    matrix[y][x] = true;
                } else {
                    matrix[y][x] = false;
                }
            }
        }
        return matrix;
    }
    private static void solve() {
        if (N < 3 || M < 3) {
            ans = isEqual(A, B) ? ans : -1;
            return;
        }
        for (int y = 0; y < N - 2; y++) {
            for (int x = 0; x < M - 2; x++) {
                if(A[y][x] ^ B[y][x]) {
                    ans += changeMatrix(y,x);
                }
            }
        }
        ans = isEqual(A,B) ? ans : -1;
    }
    private static boolean isEqual(boolean[][] matrixA, boolean[][] matrixB) {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (matrixA[y][x] != matrixB[y][x]) return false;
            }
        }
        return true;
    }
    private static int changeMatrix(int row, int col) {
        for (int y = row; y < row + 3; y++) {
            for(int x = col; x < col + 3; x++) {
                A[y][x] = !A[y][x];
            }
        }
        return 1;
    }
}
