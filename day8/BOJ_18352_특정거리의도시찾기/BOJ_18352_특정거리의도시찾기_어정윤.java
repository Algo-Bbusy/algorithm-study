import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18352_특정거리의도시찾기_어정윤 {

    private static int[] distance;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int cityNum = Integer.parseInt(stringTokenizer.nextToken());
        int roadNum = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        int start = Integer.parseInt(stringTokenizer.nextToken());
        List<Integer>[] graph = new List[cityNum + 1];
        for (int i = 1; i <= cityNum; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < roadNum; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            int to = Integer.parseInt(stringTokenizer.nextToken());
            graph[from].add(to);
        }

        distance = new int[cityNum + 1];
        visited = new boolean[cityNum + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        bfs(graph, start);

        for (int j = 1; j <= cityNum; j++) {
            if (distance[j] == k) {
                stringBuilder.append(j)
                        .append("\n");
            }
        }

        if (stringBuilder.length() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(stringBuilder);
        }
    }

    private static void bfs(List<Integer>[] graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        int cnt = 0;
        while (!queue.isEmpty()) {
            cnt++;
            int current = queue.poll();
            for (int next : graph[current]) {
                if (!visited[next]) {
                    distance[next] = distance[current]+1;
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}
