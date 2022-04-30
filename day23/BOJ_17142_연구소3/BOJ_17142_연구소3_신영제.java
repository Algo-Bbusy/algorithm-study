package com.ssafy.study.homework23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142_연구소3 {
  
    private static int N, M;
    private static int[][] arr;
    private static boolean[] visit;

    private static int result;
    private static int emptyCnt;

    private static int[] di = {-1, 0, 1, 0};
    private static int[] dj = {0, 1, 0, -1};

    private static ArrayList<Virus> virusList;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        result = Integer.MAX_VALUE;
        emptyCnt = 0;
        virusList = new ArrayList<>();
        arr = new int[N+1][N+1];

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j=1; j<=N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 0) {
                    emptyCnt++;//0개수
                } else if (arr[i][j] == 2) {
                    virusList.add(new Virus(i,j));
                }
            }
        }

        visit = new boolean[virusList.size()];

        dfs(0,0);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);

    }

    private static void dfs(int start, int count) {

        if (count == M) {

            //확산시작
            result = Math.min(result, spreadVirus());

        } else {

            for (int i=start; i<virusList.size(); i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    dfs(i+1, count+1);
                    visit[i] = false;
                }
            }

        }

    }

    private static int spreadVirus() {

        Queue<Virus> q = new LinkedList<>();

        boolean[][] visited = new boolean[N+1][N+1];

        //방문처리된 리스트만 큐에 담기
        for (int i=0; i<virusList.size(); i++) {
            if (visit[i]) {
                q.add(new Virus(virusList.get(i).x, virusList.get(i).y, 0));
            }
        }

        //변수 하나 만들어 최댓값 구하기(만약 이전 최댓값보다 크게 나오면 메서드 종료)
        int value = 0;//시간
        int zeroCnt = 0;//0개수

        while (!q.isEmpty()) {
            Virus v = q.remove();
            int qx = v.x;
            int qy = v.y;
            int qt = v.time;

            for (int i=0; i<di.length; i++) {
                int nx = qx + di[i];
                int ny = qy + dj[i];

                if (nx < 1 || ny < 1 || nx > N || ny > N) {
                    continue;
                }
                if (arr[nx][ny] == 1 || visited[nx][ny]) {
                    continue;
                }

                if (arr[nx][ny] != 1 && !visited[nx][ny]) {
                    if (arr[nx][ny] == 0) {
                        //0일때 0개수+1, 시간 : 이전시간+1
                        zeroCnt++;
                        value = qt+1;
                    }
                    visited[nx][ny] = true;
                    q.add(new Virus(nx,ny,qt+1));
                }
            }
        }

        if (emptyCnt == zeroCnt) {
            //0의 개수가 같으면 모두 확산된 것이므로 최대시간 반환
            return value;
        }

        //0 남으면 맥스값 반환
        return Integer.MAX_VALUE;

    }


    static class Virus {
        int x, y, time;
        Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}