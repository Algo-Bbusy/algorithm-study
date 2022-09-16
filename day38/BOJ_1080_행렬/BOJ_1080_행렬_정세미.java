package bj.silver.I.prob1080.행렬;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1080_행렬 {
    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] src = getArray(br);
        char[][] dest = getArray(br);

        int count = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = 0; j < M - 2; j++) {
                if (src[i][j] != dest[i][j]) {
                    ++count;
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            src[i + k][j + l] = src[i + k][j + l] == '0' ? '1' : '0';
                        }
                    }
                }
            }
        }

        if (!check(src, dest)) {
            count = -1;
        }

        System.out.println(count);
    }

    static char[][] getArray(BufferedReader br) throws Exception {
        char[][] ret = new char[N][];
        for (int i = 0; i < N; i++) {
            ret[i] = br.readLine().toCharArray();
        }

        return ret;
    }

    static boolean check(char[][] src, char[][] dest) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (src[i][j] != dest[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
