package AlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_6087_레이저통신 {

   public static class laser implements Comparable<laser> {
      int x;
      int y;
      int dir;
      int cnt;

      public laser(int x, int y, int dir, int cnt) {
         this.x = x;
         this.y = y;
         this.dir = dir;
         this.cnt = cnt;
      }

      @Override
      public int compareTo(laser o) {
         return this.cnt - o.cnt;
      }
   }

   static int W, H;

   static char[][] map;
   static int[][] visit;

   static int[] dx = { -1, 0, 1, 0 };
   static int[] dy = { 0, 1, 0, -1 };

   static laser start, end;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = null;

      st = new StringTokenizer(br.readLine());
      W = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());

      map = new char[H][W];
      visit = new int[H][W];

      for (int i = 0; i < H; i++) {
         String t = br.readLine();

         for (int j = 0; j < W; j++) {
            map[i][j] = t.charAt(j);
            visit[i][j] = Integer.MAX_VALUE;

            if (map[i][j] == 'C') {
               if (start == null) {
                  start = new laser(i, j, -1, 0);
               } else {
                  end = new laser(i, j, -1, 0);
               }
            }
         }
      }

      bfs();
      System.out.println(visit[end.x][end.y]);
   }

   private static void bfs() {
      PriorityQueue<laser> pq = new PriorityQueue<>();

      pq.add(start);
      visit[start.x][start.y] = 0;

      while (!pq.isEmpty()) {
         laser cur = pq.poll();
         
         if (cur.x == end.x && cur.y == end.y)
            break;

         for (int k = 0; k < 4; k++) {
            int nx = cur.x + dx[k];
            int ny = cur.y + dy[k];
            int ncnt = cur.cnt;

            if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == '*') {
               continue;
            }

            if (cur.dir != -1 && cur.dir != k) {
               ncnt++;               
            }
            
            
            if (visit[nx][ny] >= ncnt) {
               visit[nx][ny] = ncnt;
               pq.add(new laser(nx, ny, k, ncnt));
            }
         }
      }
   }

}