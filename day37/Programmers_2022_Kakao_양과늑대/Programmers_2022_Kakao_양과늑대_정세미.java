package programmers.kakao_blind_recruitment_2022.양과늑대;

import java.util.*;

public class Programmers_2022_Kakao_양과늑대_정세미 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}}));
        System.out.println(s.solution(new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}));
    }

    static class Solution {
        List<Integer>[] g;
        int answer;

        public int solution(int[] info, int[][] edges) {
            int N = info.length;
            g = new ArrayList[N];
            for (int i = 0; i < N; ++i) {
                g[i] = new ArrayList<>(2);
            }
            for (int[] edge : edges) {
                g[edge[0]].add(edge[1]);
            }

            find(info, 0, g[0], 0, 0);

            return answer;
        }

        void find(int[] info, int current, List<Integer> candidates, int sheep, int wolf) {
            if (info[current] == 0) {
                ++sheep;
            } else {
                ++wolf;
            }

            if (sheep == wolf) {
                return;
            }

            answer = Math.max(answer, sheep);

            for (int next : candidates) {
                List<Integer> copy = new ArrayList<>();
                for (int v : candidates) {
                    if (next == v) {
                        continue;
                    }
                    copy.add(v);
                }
                copy.addAll(g[next]);
                find(info, next, copy, sheep, wolf);
            }
        }
    }
}
