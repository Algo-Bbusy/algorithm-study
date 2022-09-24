package AlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055_탈출 {
    static class Node{
        int x;
        int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static Queue<Node> water = new LinkedList<>();
    static Queue<Node> hedgehog = new LinkedList<>();

    static int R,C,cnt;
    static char[][] forest;

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        forest = new char[R][C];

        for(int i=0; i<R; i++){
            String str = br.readLine();
            for(int j=0; j<C; j++){
                forest[i][j] = str.charAt(j);

                if (forest[i][j] == '*') {
                    water.add(new Node(i, j));
                } else if(forest[i][j] == 'S') {
                    hedgehog.add(new Node(i, j));
                }
            }
        }
        MOVE();
    }

    private static void MOVE() {
        cnt=0;
        while (true){
            cnt++;
            // 물의 이동 먼저 진행하여 물 추가
            BFS(water);
            // 물이 추가 되었으면 두더지 이동
            if (!hedgehog.isEmpty() && BFS(hedgehog)){
                // 두더지가 목표 지점에 도달 했을 경우 이동 cnt 출력
                System.out.println(cnt);
                break;
            } else if (hedgehog.isEmpty()) {
                // 두더지가 이동거리가 없을 경우 이동 못함 출력
                System.out.println("KAKTUS");
                break;
            }
        }

    }

    private static boolean BFS(Queue<Node> list) {
        int leng= list.size();
        for (int i=0; i < leng; i++){
            Node node = list.poll();
            for (int d=0; d<4; d++){
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if(nx >=0 && ny >= 0 && nx < R && ny < C){
                    // 물의 이동일 경우 해당 if문 진행
                    if(forest[node.x][node.y] == '*' && forest[nx][ny] == '.'){
                       forest[nx][ny]='*';
                       water.add(new Node(nx, ny));
                    }
                    // 두더지의 이동일 경우 해당 if문 진행
                    if(forest[node.x][node.y] == 'S'){
                        // 목표지점에 도착 했을 경우 true 반환
                        if (forest[nx][ny] == 'D'){
                            return true;
                        } else if (forest[nx][ny] == '.') {
                            forest[nx][ny] = 'S';
                            hedgehog.add(new Node(nx, ny));
                        }
                    }
                }

            }
        }
        return false;
    }
}