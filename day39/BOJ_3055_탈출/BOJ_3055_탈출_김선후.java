package anystep;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_3055 {
//탈출
//메모리 14364 KB 시간 128 ms
//동시간대에 별도의 두 곳에서 동시에 진행되는 BFS 문제
//같은 시간에 같은 공간 내에서 동일한 회차로 진행
//고슴도치가 이동할 수 없는 요소 (물+물 상하좌우+돌+굴)(+고슴도치가 지나온 자리(최소시간))
//Solution 1.
//1. 고슴도치 시작 지점, 도착 지점 좌표 맵핑.
//2. 초기 물 좌표 큐에 저장
//3. 초기 고슴도치 시작 좌표 큐에 저장 (물부터 진행해야 하기 때문. 고슴도치는 물이 다음 회차에 이동할 장소도 갈 수 없다.)
//4. BFS 진행 (물(123)->고슴도치(456)) 고슴도치가 이동할 수 있는 경우가 없을 때 여부 판단.
//4.1 물 좌표를 큐에서 하나씩 꺼내며 상하좌우의 물 이동 좌표 파악. boolean[][] 변경
//4.2 예상 좌표를 새로운 물 좌표 큐에 저장
//4.3 기존 물 좌표 큐가 비게 될 때, 새로운 물 좌표 큐를 기존 물 좌표 큐로 복사.
//4.4 고슴도치 좌표 큐에서 하나씩 꺼내며 상하좌우로 고슴도치 이동 가능 여부 파악
//4.5 도착 지점으로 이동 가능한 경우 현재 시간+1 반환
//4.6 이동 가능한 경우 boolean[][] 변경 후 이동 좌표를 새로운 고슴도치 좌표 큐에 넣음.
//4.7 시간 1초 증가 후 반복
//5. 최종 결과값 출력
//풀이시간 : 1시간 58분 16초
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer stk;
    static class Point{
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int R,C,ans;
    static char[][] map;
    static boolean[][] isBlocked; //true: 이동 불가, false: 이동 가능
    static Queue<Point> water;
    static Queue<Point> hadgehog;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static Point S,D;
    public static void main(String[] args) throws IOException {
        input();
        ans = escape();
        printAns();
    }

    private static void input() throws IOException {
        stk = new StringTokenizer(in.readLine());

        R = Integer.parseInt(stk.nextToken());
        C = Integer.parseInt(stk.nextToken());

        map = new char[R][C];
        isBlocked = new boolean[R][C];
        water = new LinkedList<>();

        S = null;
        D = null;

        for(int y = 0; y < R; y++) {
            stk = new StringTokenizer(in.readLine());
            String str = stk.nextToken();
            map[y] = str.toCharArray();
            for(int x = 0; x < C; x++) {
                switch (map[y][x]) {
                    case 'D':
                        D = new Point(y,x);
                        isBlocked[y][x] = true;
                        break;
                    case 'S':
                        S = new Point(y,x);
                        break;
                    case '*':
                        water.add(new Point(y,x));
                        isBlocked[y][x] = true;
                        break;
                    case 'X':
                        isBlocked[y][x] = true;
                        break;
                }
            }
        }
    }
    private static int escape() {
        int time = 0;
        hadgehog = new LinkedList<>();
        hadgehog.add(S);

        while (true) {
            if(hadgehog.isEmpty()) {
                return -1;
            }
            //물 진행
            stepWater();
            //고슴도치 진행
            Queue<Point> nextHadgehog = new LinkedList<>();

            while (!hadgehog.isEmpty()) {
                Point currentHadgehog = hadgehog.poll();
                for(int d = 0; d < 4; d++) {
                    int ny = currentHadgehog.y + dy[d];
                    int nx = currentHadgehog.x + dx[d];
                    //다음 좌표가 도착지점이라면
                    if(ny == D.y && nx == D.x) {
                        return time+1;
                    }

                    if(isPosible(ny,nx)) {
                        isBlocked[ny][nx] = true;
                        nextHadgehog.add(new Point(ny,nx));
                    }
                }
            }
            hadgehog = nextHadgehog;
            time++;
        }
    }
    private static void stepWater() {
        Queue<Point> nextWater = new LinkedList<>();

        while (!water.isEmpty()) {
            Point currentWater = water.poll();
            for(int d = 0; d < 4; d++) {
                int ny = currentWater.y + dy[d];
                int nx = currentWater.x + dx[d];
                if(isPosible(ny,nx)) {
                    isBlocked[ny][nx] = true;
                    nextWater.add(new Point(ny,nx));
                }
            }
        }
        water = nextWater;
    }

    private static boolean isPosible(int ny, int nx) {
        if(ny < 0 || ny >= R || nx < 0 || nx >= C || isBlocked[ny][nx]) {
            return false;
        }
        return true;
    }
    private static void printAns() throws IOException {
        out.write(ans==-1?"KAKTUS":ans+"");
        out.flush();
        out.close();
        in.close();
    }
}
