import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12851_숨바꼭질2_어정윤 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int time = 0;
        int way = 1;
        if (n < k) {
            boolean[] isVisited = new boolean[100001];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(n);
            way = 0;
            while (!queue.isEmpty()) {
                time++;
                int size = queue.size();
                while (size-- > 0) {
                    int current = queue.poll();
                    isVisited[current] = true;
                    int[] nexts = {current - 1, current + 1, current * 2};
                    for (int next : nexts) {
                        if (next > 0 && next <= 100000 && !isVisited[next]) {
                            if (next == k) {
                                way++;
                                continue;
                            }
                            queue.offer(next);
                        }
                    }
                }
                if (way != 0) {
                    queue.clear();
                }
            }
        } else if (n > k) {
            time = n - k;
        }

        System.out.println(time);
        System.out.println(way);
    }
}
