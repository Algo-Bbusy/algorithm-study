package sh1n.bj.ps3190뱀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	public static final int RIGHT = 0;
	//DOWN = 1, LEFT = 2, UP = 3;
	
	static class Snake {
		List<Position> positions = new ArrayList<>();
		int dir;
		Queue<int[]> changeDirInfo = new LinkedList<>();
		
		public Snake() {
			super();
			positions.add(new Position(1, 1));
			dir = RIGHT;
		}
	}
	
	static class Position {
		int r;
		int c;
		
		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		@Override
		public boolean equals(Object obj) {
			Position pos = (Position) obj;
			
			return (this.r == pos.r) && (this.c == pos.c);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		boolean[][] isApple = new boolean[N+1][N+1];
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			isApple[r][c] = true;
		}
		
		Snake snake = new Snake();
		int L = Integer.parseInt(br.readLine());
		for(int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			int dir;
			if(C == 'L') dir = -1;
			else dir = 1;
			snake.changeDirInfo.offer(new int[] {X, dir});
		}
		
		move(snake, isApple, N, 0);
		
	}
	
	public static void move(Snake snake, boolean[][] isApple, int size, int time) {
		Position snakeHead = snake.positions.get(0);
		int headR = snakeHead.r;
		int headC = snakeHead.c;
		headR += deltas[snake.dir][0];
		headC += deltas[snake.dir][1];
		if(headR < 1 || headR > size || headC < 1 || headC > size || snake.positions.contains(new Position(headR, headC))) {
			System.out.println(time+1);
			System.exit(0);
		}
		
		snake.positions.add(0, new Position(headR, headC));
		if(!isApple[headR][headC]) {
			snake.positions.remove(snake.positions.size()-1);
		} else {
			isApple[headR][headC] = false;
		}
		
		if(!snake.changeDirInfo.isEmpty() && snake.changeDirInfo.peek()[0] == time+1) {
			int dir = snake.changeDirInfo.poll()[1];
			snake.dir = ((snake.dir + dir) + 4) % 4;
		}
		
		move(snake, isApple, size, time+1);
	}
}
