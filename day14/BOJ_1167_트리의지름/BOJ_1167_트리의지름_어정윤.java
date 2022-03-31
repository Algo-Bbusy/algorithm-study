import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1167_트리의지름_어정윤 {

    static class Node {

        int to;
        int distance;
        int initDistance;

        public Node(int to, int distance) {
            this.to = to;
            this.distance = distance;
            this.initDistance = distance;
        }

        public void initialize() {
            distance = initDistance;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", distance=" + distance +
                    '}';
        }
    }

    private static int maxDiameter;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int vertexNum = Integer.parseInt(bufferedReader.readLine());
        List<Node>[] linkInfos = new List[vertexNum + 1];
        for (int i = 1; i <= vertexNum; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(stringTokenizer.nextToken());
            linkInfos[from] = new ArrayList<>();
            while (stringTokenizer.hasMoreTokens()) {
                int to = Integer.parseInt(stringTokenizer.nextToken());
                if (to == -1) {
                    break;
                }
                int distance = Integer.parseInt(stringTokenizer.nextToken());
                linkInfos[from].add(new Node(to, distance));
            }
        }

        maxDiameter = 0;
        for (int i = 1; i <= vertexNum; i++) {
            searchTree(linkInfos, vertexNum, i);
        }
        System.out.println(maxDiameter);
    }

    private static void searchTree(List<Node>[] linkInfos, int vertexNum, int from) {
        int[] distances = new int[vertexNum + 1];
        distances[from] = -1;
        Queue<Node> queue = new LinkedList<>();
        for (Node linkInfo : linkInfos[from]) {
            queue.offer(linkInfo);
        }

        Queue<Node> changedNodes = new LinkedList<>();
        while (!queue.isEmpty()) {
            Node linkInfo = queue.poll();
            if (distances[linkInfo.to] == 0) {
                distances[linkInfo.to] += linkInfo.distance;
                for (Node nextLinkInfo : linkInfos[linkInfo.to]) {
                    if (distances[nextLinkInfo.to] != -1) {
                        nextLinkInfo.distance += linkInfo.distance;
                        queue.offer(nextLinkInfo);
                        changedNodes.offer(nextLinkInfo);
                    }
                }
            }
        }
        while (!changedNodes.isEmpty()) {
            changedNodes.poll().initialize();
        }
        maxDiameter = Math.max(maxDiameter, Arrays.stream(distances).max().getAsInt());
    }
}
