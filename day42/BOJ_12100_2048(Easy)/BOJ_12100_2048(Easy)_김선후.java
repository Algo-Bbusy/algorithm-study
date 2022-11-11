package anystep;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java_12100 {
//2048(easy)
//메모리 26972KB 시간 260ms
//한번에 한 방향으로만 이동이 가능, 이동 시 모든 블록이 같이 움직인다.
//이동 과정에서 같은 값을 지닌 블록이 있으면 값을 합친다.
//한번의 이동 과정에서 한번만 합쳐진다.
//20*20*4*5 = 8000 O(N^2*D*MAXDEPTH) 완탐 사용 가능
//Solution 1.
//1. 문제 풀이에 사용될 맵의 초기상태 입력
//2. 최대 5번의 깊이(최대이동수)를 지닌 재귀함수 실행
//2.1 사방탐색을 통해 동일한 깊이에서 각각의 경우에서 이동된 맵을 다음 깊이의 재귀함수로 호출
//2.1.1 이동방향이 위와 왼쪽은 좌측상단부터 탐색 시작, 이동방향이 아래와 오른쪽은 우측하단부터 탐색 시작
//2.1.2 블록이 이동할 좌표값이 공백이면 경계를 벗어날때까지 계속 이동
//2.1.3 블록이 이동할 좌표가 같은 값을 지닌 블록이면 합체여부에 따라 합체종료 혹은 바로종료
//2.1.4 블록이 이동할 좌표가 다른 값을 지닌 블록이면 바로 종료
//2.2 최대깊이까지 재귀함수가 도달하면 현재 지니고 있는 맵의 최대값을 탐색 후 최대값 비교 갱신
//3. 정답값 출력
//풀이시간 1시간 27분 12초
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer stk;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N, ans;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        input();
        moveMaxFive(0, 5, map);
        printAns();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(in.readLine());
        map = new int[N][N];
        for (int y = 0; y < N; y++) {
            stk = new StringTokenizer(in.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(stk.nextToken());
            }
        }
    }

    private static void moveMaxFive(int depth, int maxDepth, int[][] currentMap) {
        //5번 이동 시 최대값 갱신판단 후 재귀함수 종료
        if (depth == maxDepth) {
            int currentAns = 0;
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    currentAns = Math.max(currentAns, currentMap[y][x]);
                }
            }
            ans = Math.max(ans, currentAns);
            return;
        }
        //동일한 경우(depth)의 맵에서 상하좌우 이동의 경우 탐색
        for (int d = 0; d < 4; d++) {
            //블록 합체 여부 판단 배열
            boolean[][] unionCheck = new boolean[N][N];
            //각각의 이동 경우의 수마다 조작할 임시 맵
            int[][] tempMap = new int[N][N];

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    tempMap[y][x] = currentMap[y][x];
                }
            }

            //상, 좌
            if (d == 0 || d == 2) {
                //좌측상단에서 탐색시작
                for (int y = 0; y < N; y++) {
                    for (int x = 0; x < N; x++) {
                        if(tempMap[y][x]==0) continue;
                        //블럭 이동
                        moveBlock(tempMap, unionCheck, d, y, x);
                    }
                }
            }
            //하, 우
            else if (d == 1 || d == 3) {
                //우측하단에서 탐색시작
                for (int y = N - 1; y >= 0; y--) {
                    for (int x = N - 1; x >= 0; x--) {
                        if(tempMap[y][x]==0) continue;
                        //블럭 이동
                        moveBlock(tempMap, unionCheck, d, y, x);
                    }
                }
            }
            moveMaxFive(depth + 1, maxDepth, tempMap);
        }
    }

    private static void moveBlock(int[][] tempMap, boolean[][] unionCheck, int d, int y, int x) {
        int currentY = y;
        int currentX = x;
        int ny = currentY + dy[d];
        int nx = currentX + dx[d];
        //이동 좌표 경계체크
        if (ny >= N || nx >= N || ny < 0 || nx < 0) return;

        boolean endMove = false;

        while (!endMove) {
            //이동할 위치가 빈공간 (0)
            if (tempMap[ny][nx] == 0) {
                //현재 위치의 값을 이동할 위치에 저장
                tempMap[ny][nx] = tempMap[currentY][currentX];
                //현재 위치 값 공백
                tempMap[currentY][currentX] = 0;
                //현재 위치 값 갱신
                currentY = ny;
                currentX = nx;
                //다음 위치 값 갱신
                ny = currentY + dy[d];
                nx = currentX + dx[d];
                //이동 좌표 경계체크
                if (ny >= N || nx >= N || ny < 0 || nx < 0) endMove = true;
            }
            //이동할 위치가 현재 위치 값과 동일(같은 블럭)
            else if (tempMap[ny][nx] == tempMap[currentY][currentX]) {
                //블록 합체 여부 체크 다음 좌표 블록이 합쳐진 적 없다면 합치고 종료
                if (!unionCheck[ny][nx]) {
                    //이동할 값 갱신(블록 합체)
                    tempMap[ny][nx] *= 2;
                    //현재 위치 값 공백
                    tempMap[currentY][currentX] = 0;
                    //이동할 위치 블록 합체 여부 갱신
                    unionCheck[ny][nx] = true;
                }
                endMove = true;
            }
            //이동할 위치의 값이 현재 위치 값과 다름(이동 종료)
            else {
                endMove = true;
            }
        }
    }

    private static void printAns() throws IOException {
        out.write(ans + "");
        out.flush();
        out.close();
        in.close();
    }
}
