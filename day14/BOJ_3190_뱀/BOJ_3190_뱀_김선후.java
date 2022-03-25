package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Java_3190 {
	// 뱀
//	문제 접근법 자체가 어렵지는 않은 문제였으나 시뮬레이션 문제가 늘 그렇듯 생각을 순차적으로 코드로 옮기는 과정이 어려웠습니다.
//	뱀의 이동과정을 사용하는데 큐와 덱을 사용하면 쉽게 구현할 수 있겠다라는 생각이 들었고
//	시간별로 종속된 방향전환 값을 담을 수 있는 자료구조가 하나 필요하다고 생각했습니다.
//	키값에 따른 값을 담는 Map과 기본적인 List 그리고 Que 이렇게 세가지가 떠올랐는데 Map은 다뤄본 경험이 적어서
//	비교적 익숙한 List를 사용하려고 했으나 기왕 뱀을 구현하는데 Deque를 사용했으니 같은걸 사용하는게 생각하기 더 쉬워보여서 Que를 썼습니다.
//	덱에 들어간 가장 마지막 데이터가 뱀의 머리 부분이며 가장 처음으로 들어간 데이터가 뱀의 꼬리부분이라는 것만 혼동하지 않으면 비교적 쉽게 풀리는 문제입니다.
//	Solution 1.
//	1. 이차원 배열 map을 생성 후 빈공간은 0, 사과는 1, 뱀은 2로 저장
//	2. 뱀의 머리부터 꼬리까지의 좌표를 덱으로 관리하기 위해서 시간에 따른 방향값을 담은 Dir 클래스와 뱀의 좌표를 담은 Snake 클래스 선언
//	3. 뱀의 머리가 이동할 때마다 해당 좌표를 덱의 끝부분에 넣어준다.(단 뱀이 이동하려는 좌표가 맵을 벗어나거나 2 SNAKE라면 게임이 끝난다.)
//	4-1. 사과가 없을 경우 덱의 맨 앞 데이터를 poll하고 해당하는 좌표의 map값을 빈공간 BLANK로 갱신
//	4-2. 사과가 있을 경우 덱의 맨 뒤 데이터에 추가하고 해당하는 좌표의 맵값을 뱀 SNAKE로 갱신
//	5. 반복문이 돌아가면서 증가된 시간이 큐에 담겨져있는 첫 데이터의 시간과 같다면 poll한 후에 뱀의 이동방향을 바꾼다.
	
//	메모리 : 14640KB 시간 : 132ms
//	풀이시간 : 1시간 28분 54초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N,K,L,time,currentDir;
	static int[][] map;
	static int[] dy = {0,1,0,-1}; //시작 방향 우측
	static int[] dx = {1,0,-1,0};
	final static int BLANK = 0;
	final static int APPLE = 1;
	final static int SNAKE = 2;
	static class Dir{
		int X;
		String C;
		public Dir(int x, String c) {
			super();
			X = x;
			C = c;
		}
	}
	static class Snake{
		int y, x;

		public Snake(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		K = Integer.parseInt(in.readLine());
		map = new int[N+1][N+1];
		map[1][1]=SNAKE;
		for(int apple=0; apple<K; apple++) {
			stk = new StringTokenizer(in.readLine());
			int y=Integer.parseInt(stk.nextToken());
			int x=Integer.parseInt(stk.nextToken());
			map[y][x]=APPLE;
		}
		L=Integer.parseInt(in.readLine());
		Queue<Dir> dirQue = new LinkedList<>();
		for(int cmd=0; cmd<L; cmd++) {
			stk = new StringTokenizer(in.readLine());
			int X = Integer.parseInt(stk.nextToken());
			String C = stk.nextToken();
			dirQue.add(new Dir(X, C));
		}
		Deque<Snake> snake = new LinkedList<>();
		snake.addLast(new Snake(1, 1));
		while(true) {
			time++;
			Snake snakeHead = snake.getLast();
			int snakeY = snakeHead.y + dy[currentDir%4];
			int snakeX = snakeHead.x + dx[currentDir%4];
			if(isBoundary(snakeY,snakeX)) {
				if(map[snakeY][snakeX]==SNAKE) break;
				if(map[snakeY][snakeX]==BLANK) {
					Snake tail = snake.removeFirst();
					map[tail.y][tail.x]=BLANK;
				}
				snake.addLast(new Snake(snakeY, snakeX));
				map[snakeY][snakeX]=SNAKE;
			}
			else break;
			
			if(!dirQue.isEmpty() && dirQue.peek().X==time) {
				Dir changeDir = dirQue.poll();
				if(changeDir.C.equals("L")) currentDir += 3;
				else currentDir +=1;
			}
		}
		out.write(time+"");
		out.flush();
		out.close();
		in.close();
	}
	private static boolean isBoundary(int snakeY, int snakeX) {
		if(1<=snakeY && 1<=snakeX && snakeY<=N && snakeX<=N) return true;
		return false;
	}

}
