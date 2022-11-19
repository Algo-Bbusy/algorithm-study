import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_5972_택배배송 {
    static int N, M;
    static List<int[]>[] graph;
    static int[] costs;

    public static void main(String[] args) throws Exception {
        input();
        dijkstra();
        System.out.println(costs[N]);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }
    }

    private static void dijkstra() {
        costs = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{1, 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int vertex = current[0];
            int cost = current[1];

            if (visited[vertex]) {
                continue;
            }
            visited[vertex] = true;
            costs[vertex] = cost;

            if (vertex == N) {
                break;
            }

            for (int i = 0; i < graph[vertex].size(); i++) {
                int[] next = graph[vertex].get(i);
                int nextVertex = next[0];
                int nextCost = cost + next[1];
                if (!visited[nextVertex] && nextCost <= costs[nextVertex]) {
                    pq.offer(new int[]{nextVertex, nextCost});
                }
            }
        }
    }
}
