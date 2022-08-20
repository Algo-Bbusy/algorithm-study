package AlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16918봄버맨 {
	
	static int R, C, N;
	   
	   static char[][] arr;
	   static int[][] bombTime;
	   
	   static int time = 0;
	   
	   static int[] dx = {-1,0,1,0};
	   static int[] dy = {0,1,0,-1};
	   
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = null;
	        
	        st = new StringTokenizer(br.readLine());
	         R = Integer.parseInt(st.nextToken());
	         C = Integer.parseInt(st.nextToken());
	         N = Integer.parseInt(st.nextToken());

	        arr = new char[R][C];
	        bombTime = new int[R][C];

	        for (int i = 0; i < R; i++) {
	            String str = br.readLine();
	            for (int j = 0; j < C; j++) {
	                arr[i][j] = str.charAt(j);
	                if(arr[i][j]=='O'){
	                    bombTime[i][j] = 3;
	                }
	            }
	        }

	        while(true) {
	            if(time>=N) break;
	            time ++;
	            if(time == 1) continue;
	            
	            if(time%2==0) {
	               even();
	            }else if(time%2==1) {
	               bombMap();
	            }     
	        }
	        

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}

	    }

	    /*
	     * 값이 홀수 일 경우 : 해당 시간 과 일치하는 곳과 4방에 . 으로 바꿔주고 0으로 값을 바꿔준다.
	     */
	   private static void bombMap() {
	        for (int i = 0; i < R; i++) {
	            for (int j = 0; j < C; j++) {
	                if (bombTime[i][j] == time) {
	                    arr[i][j] = '.';
	                    for (int d = 0; d < 4; d++) {
	                        int nx = i + dx[d];
	                        int ny = j + dy[d];

	                        if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
	                            if(arr[nx][ny]=='O' && bombTime[nx][ny] != time) { 
	                                arr[nx][ny] = '.';
	                                bombTime[nx][ny] = 0;
	                            }
	                        }
	                    }
	                }
	            }
	        }      
	   }

	   /*
	    * 값이 짝수 일 때 폭탄으로 바꾸어 주고 해당시간에 3초뒤에 터질것을 알리기위해 3을 더해준다.
	    */
	   private static void even() {
	        for (int i = 0; i < R; i++) {
	            for (int j = 0; j < C; j++) {
	                if (arr[i][j] == '.') {
	                    arr[i][j] = 'O';
	                    bombTime[i][j] = time+3;
	                }
	            }
	        }      
	   }
	}


//System.out.println("Time ===> " + time);
//System.out.println("######################################");
//for(int i=0; i<R;i++) {
//   for(int j=0; j<C; j++) {
//      System.out.print(bombTime[i][j]+" ");
//   }
//   System.out.println(" ");
//}
