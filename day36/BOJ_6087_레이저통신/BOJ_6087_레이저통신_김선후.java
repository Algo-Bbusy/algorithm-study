package anystep;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_6087 {
    //레이저 통신
    //메모리 67532KB 시간 384ms
    //* 최대 100*100 맵배열을 DFS로 탐색하고자 할 시 스택오버플로우의 위험성이 있어 BFS로 접근.
    //* H W 크기의 지도에서 C로 표시된 두 칸이 레이저를 쏠 수 있는 칸
    //* 빈 칸은 '.', 벽은 '*'
    //* 레이저가 발사되고 있는 도중 거울을 설치해서 방향 회전
    //* 두 C가 연결되는 경우의 수 중 거울 설치 개수의 최솟값을 구하는 문제.
    //Solution 1.
    //1. 맵을 입력 받은 2차원 char 배열 map과 방문체크 및 거울개수를 기록할 2차원 int 배열 isVisited 초기화.
    //2. 레이저 좌표의 시작점과 종료지점을 담아둘 리스트 lazor 초기화. 이때 레이저 좌표의 현상태를 나타내는 Position 클래스 필요.
    //3. 입력 받는 중 현재 맵배열에 입력될 문자가 'C'인 경우 lazor에 저장. 첫번째가 시작점 두번째가 종료점.
    //4. BFS 탐색을 통한 레이저 경로 탐색 및 거울 설치 시작.
    //5. 큐에 처음으로 넣을 값으로 레이저 시작점을 넣은 후 bfs 진행.
    //6. 현재 진행중인 큐의 Position이 레이저 종료점일 경우 정답값 비교 후 최솟값 갱신
    //7. 사방탐색을 진행하며 경계 및 벽을 만날시 다음 방향으로 전환.
    //8. 다음 레이저 방향이 현재 레이저 방향과 다르거나 종료점이 아니라면 거울 설치
    //9. 만일 이전에 다음 레이저 좌표에 방문한적이 있으며 그때 설치된 거울 수가 현재 설치된 거울 수 보다 작다면 거울설치 취소.
    //10. BFS 종료후 최솟값 출력
    //풀이시간 52분 12초
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer stk;

    static int W, H;
    static char[][] map;
    static int[][] isVisited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int ans = Integer.MAX_VALUE;
    static class Position {
        int y, x, dir, mirror;

        public Position(int y, int x, int dir, int mirror) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.mirror = mirror;
        }
    }
    static ArrayList<Position> lazor;
    public static void main(String[] args) throws IOException {
        input();
        installMirror();
        printAns();
    }

    private static void input() throws IOException {
        stk = new StringTokenizer(in.readLine());
        W = Integer.parseInt(stk.nextToken());
        H = Integer.parseInt(stk.nextToken());
        map = new char[H][W];
        isVisited = new int[H][W];
        lazor = new ArrayList<>();

        for (int y = 0; y < H; y++) {
            String[] element = in.readLine().split("");
            for (int x = 0; x < W; x++) {
                isVisited[y][x] = Integer.MAX_VALUE;

                char mapElement = element[x].charAt(0);
                map[y][x] = mapElement;

                if (mapElement == 'C') lazor.add(new Position(y, x, -1, 0));
            }
        }
        in.close();
    }
    private static void installMirror() {
        Queue<Position> q = new LinkedList<>();

        Position startLazorPosition = lazor.get(0);
        Position endLazorPosition = lazor.get(1);

        q.add(startLazorPosition);
        isVisited[startLazorPosition.y][startLazorPosition.x] = 0;

        while (!q.isEmpty()) {
            Position pos = q.poll();

            int currentLazorY = pos.y;
            int currentLazorX = pos.x;
            int currentLazorDir = pos.dir;
            int currentInstallMirror = pos.mirror;

            //현재 레이저 좌표가 도착점이면 정답값 최소값 비교 후 갱신여부 판단
            if (currentLazorY == endLazorPosition.y && currentLazorX == endLazorPosition.x) {
                ans = Math.min(ans, currentInstallMirror);
            }

            //0 상 1 하 2 좌 3 우
            for (int d = 0; d < 4; d++) {
                int ny = currentLazorY + dy[d];
                int nx = currentLazorX + dx[d];
                int nDir = d;
                //경계체크 및 방향 전환
                if (ny < 0 || nx < 0 || ny >= H || nx >= W || map[ny][nx] == '*') continue;
                //현재 레이저 좌표 방향과 다음 레이저 좌표 방향 비교
                //만일 다르다면 거울 설치
                int temp = currentInstallMirror;
                if (currentLazorDir != nDir && currentLazorDir != -1) temp++;
                //이전에 방문한 다음좌표의 설치거울수보다 현재 거울설치된 거울수가 더 크다면 무시
                if (isVisited[ny][nx] < temp) continue;

                isVisited[ny][nx] = temp;
                q.add(new Position(ny, nx, nDir, temp));
            }
        }
    }
    private static void printAns() throws IOException {
        out.write(ans+"");
        out.flush();
        out.close();
    }
}
