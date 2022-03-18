package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Java_14499 {
	// 주사위 굴리기
	//각 주사위의 방향인덱스는 변하지 않는다. ex) 1번인덱스는 무조건 TOP 6번인덱스는 무조건 BOTTOM
	//4방탐색을 위한 delta 1차원 배열의 크기를 5로 지정해준다.(인덱스 1부터 사용하기 때문에)
	//solution 1 currentDice 클래스를 만들어서 현재 주사위의 좌표와 속성들을 매 순간마다 갱신해준다.
	//백준 테케 4에서 원점으로 돌아오고 다음 동작이 원하는 동작이 나오지 않아서 실패 ㅠㅠ- 원인을 찾지 못했음.
	//solution 2 tempDice 배열에 dice배열을 복사하여 넣어준 뒤 각 명령에 맞게 주사위의 값과 바닥을 갱신해준다.
//	1. 명령의 방향에 따라서 주사위를 굴린다.
//	2.  해당좌표의 map값과 주사위의 BOTTOM값을 비교한다.
//	2-1.해당좌표의 map값이 0이라면 그 순간의 주사위 BOTTOM 값을 map에 저장한다.
//	2-2.해당좌표의 map값이 0이 아니라면 그 값을 주사위의 BOTTOM에 저장하고 map을 0으로 초기화한다.
//	3. 주사위의 현재좌표를 다음좌표로 갱신한다. 그 후 1로 돌아간다.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb= new StringBuilder();
	static int N,M,K, startY, startX;
	static int[] dice = new int[7];
	static final int TOP=1, REER=2, RIGHT=3, LEFT=4, FRONT=5, BOTTOM=6;
	static int[][] map;
	static int[] dy= {0,0,0,-1,1};
	static int[] dx= {0,1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		startY = Integer.parseInt(stk.nextToken());
		startX = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		map = new int[N][M];
		for(int y=0; y<N; y++) {
			stk = new StringTokenizer(in.readLine());
			for(int x=0; x<M; x++) {
				map[y][x]=Integer.parseInt(stk.nextToken());
			}
		}
		stk=new StringTokenizer(in.readLine());
		for(int i=0; i<K; i++) {
			int cmd = Integer.parseInt(stk.nextToken());
			int ny = startY + dy[cmd];
			int nx = startX + dx[cmd];
			
			if(isBoundary(ny,nx)) {
				moveDice(cmd);
				if(map[ny][nx]==0) map[ny][nx]=dice[BOTTOM];
				else {
					dice[BOTTOM] = map[ny][nx];
					map[ny][nx]=0;
				}
				startY=ny;
				startX=nx;
				sb.append(dice[TOP]).append("\n");
			}
		}
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}

	private static void moveDice(int cmd) {
		int[] tempDice = dice.clone();
//		TOP=1, REER=2, RIGHT=3, LEFT=4, FRONT=5, BOTTOM=6
		switch (cmd) {
		case 1:
			dice[TOP]=tempDice[LEFT];
			dice[RIGHT]=tempDice[TOP];
			dice[LEFT]=tempDice[BOTTOM];
			dice[BOTTOM]=tempDice[RIGHT];
			break;
		case 2:
			dice[TOP]=tempDice[RIGHT];
			dice[RIGHT]=tempDice[BOTTOM];
			dice[LEFT]=tempDice[TOP];
			dice[BOTTOM]=tempDice[LEFT];
			break;
		case 3:
			dice[TOP]=tempDice[FRONT];
			dice[REER]=tempDice[TOP];
			dice[FRONT]=tempDice[BOTTOM];
			dice[BOTTOM]=tempDice[REER];
			break;
		default:
			dice[TOP]=tempDice[REER];
			dice[REER]=tempDice[BOTTOM];
			dice[FRONT]=tempDice[TOP];
			dice[BOTTOM]=tempDice[FRONT];
			break;
		}
	}

	private static boolean isBoundary(int ny, int nx) {
		if(nx>=0 && ny>=0 && nx<M && ny<N) return true;
		return false;
	}
//	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
//	static StringTokenizer stk;
//	static StringBuilder sb= new StringBuilder();
//	static int N, M, K;
//	static int[][] map;
//	static int[] cmd;
//	static int[] dy = {0, 0, 0, -1, 1};	//동 서 북 남
//	static int[] dx = {0, 1, -1, 0, 0};
//	static class Dice{
//		int y; int x;
//		int top, bottom, left, right, front, reer;
//		public Dice(int y, int x, int top, int bottom, int left, int right, int front, int reer) {
//			super();
//			this.y = y;
//			this.x = x;
//			this.top = top;
//			this.bottom = bottom;
//			this.left = left;
//			this.right = right;
//			this.front = front;
//			this.reer = reer;
//		}
//		@Override
//		public String toString() {
//			return "Dice [y=" + y + ", x=" + x + ", top=" + top + ", bottom=" + bottom + ", left=" + left + ", right="
//					+ right + ", front=" + front + ", reer=" + reer + "]";
//		}
//		
//	}
//	static Dice currentDice;
//	public static void main(String[] args) throws IOException {
//		stk = new StringTokenizer(in.readLine());
//		N = Integer.parseInt(stk.nextToken());
//		M = Integer.parseInt(stk.nextToken());
//		int startY = Integer.parseInt(stk.nextToken());
//		int startX = Integer.parseInt(stk.nextToken());
//		K = Integer.parseInt(stk.nextToken());
//		map = new int[N][M];
//		cmd = new int[K];
//		currentDice = new Dice(startY, startX, 0, 0, 0, 0, 0, 0);
//		for(int y=0; y<N; y++) {
//			stk = new StringTokenizer(in.readLine());
//			for(int x=0; x<M; x++) {
//				map[y][x]=Integer.parseInt(stk.nextToken());
//			}
//		}
//		
//		stk = new StringTokenizer(in.readLine());
//		for(int k=0; k<K; k++) cmd[k]=Integer.parseInt(stk.nextToken());
//		for(int k=0; k<K; k++) moveDice(currentDice.y, currentDice.x ,cmd[k]);
//		out.write(sb.toString());
//		out.flush();
//		out.close();
//		in.close();
//	}
//	private static void moveDice(int diceY, int diceX, int cmd) {
////		System.out.println("현재 주사위 Y좌표 : "+diceY + " 현재 주사위 X좌표 : "+diceX);
//		for(int[] b : map) System.out.println(Arrays.toString(b)); System.out.println();
//		System.out.println(currentDice);
//		switch (cmd) {
//		case 1:
//			int ny = diceY+dy[1];
//			int nx = diceX+dx[1];
////			System.out.println("다음 주사위 Y좌표 : "+ny + " 현재 주사위 X좌표 : "+nx);
//			if(isBoundary(ny,nx)) {
//				if(map[ny][nx]==0) {
//					currentDice.y = ny;
//					currentDice.x = nx;
//					map[ny][nx]= currentDice.right;
//					int tempBottom = currentDice.bottom;
//					currentDice.bottom = currentDice.right;
//					currentDice.right = currentDice.top;
//					currentDice.top = currentDice.left;
//					currentDice.left = tempBottom;
//					sb.append(currentDice.top).append("\n");
//				}
//				else if(map[ny][nx]!=0){
//					currentDice.y = ny;
//					currentDice.x = nx;
//					currentDice.right = map[ny][nx];
//					map[ny][nx]=0;
//					int tempBottom = currentDice.bottom;
//					currentDice.bottom = currentDice.right;
//					currentDice.right = currentDice.top;
//					currentDice.top = currentDice.left;
//					currentDice.left = tempBottom;
//					sb.append(currentDice.top).append("\n");
//				}
//			}
//			break;
//		case 2:
//			ny = diceY+dy[2];
//			nx = diceX+dx[2];
////			System.out.println("다음 주사위 Y좌표 : "+ny + " 현재 주사위 X좌표 : "+nx);
//			if(isBoundary(ny,nx)) {
//				if(map[ny][nx]==0) {
//					currentDice.y = ny;
//					currentDice.x = nx;
//					map[ny][nx] = currentDice.left;
//					int tempBottom = currentDice.bottom;
//					currentDice.bottom = currentDice.left;
//					currentDice.left = currentDice.top;
//					currentDice.top = currentDice.right;
//					currentDice.right = tempBottom;
//					sb.append(currentDice.top).append("\n");
//				}
//				else if(map[ny][nx]!=0){
//					currentDice.y = ny;
//					currentDice.x = nx;
//					currentDice.left = map[ny][nx];
//					map[ny][nx]=0;
//					int tempBottom = currentDice.bottom;
//					currentDice.bottom = currentDice.left;
//					currentDice.left = currentDice.top;
//					currentDice.top = currentDice.right;
//					currentDice.right = tempBottom;
//					sb.append(currentDice.top).append("\n");
//				}
//			}
//			break;
//		case 3:
//			ny = diceY+dy[3];
//			nx = diceX+dx[3];
////			System.out.println("다음 주사위 Y좌표 : "+ny + " 현재 주사위 X좌표 : "+nx);
//			if(isBoundary(ny,nx)) {
//				if(map[ny][nx]==0) {
//					currentDice.y = ny;
//					currentDice.x = nx;
//					map[ny][nx] = currentDice.reer;
//					int tempBottom = currentDice.bottom;
//					currentDice.bottom = currentDice.reer;
//					currentDice.reer = currentDice.top;
//					currentDice.top = currentDice.front;
//					currentDice.front = tempBottom;
//					sb.append(currentDice.top).append("\n");
//				}
//				else if(map[ny][nx]!=0){
//					currentDice.y = ny;
//					currentDice.x = nx;
//					currentDice.reer = map[ny][nx];
//					map[ny][nx]=0;
//					int tempBottom = currentDice.bottom;
//					currentDice.bottom = currentDice.reer;
//					currentDice.reer = currentDice.top;
//					currentDice.top = currentDice.front;
//					currentDice.front = tempBottom;
//					sb.append(currentDice.top).append("\n");
//				}
//			}
//			break;
//		case 4:
//			ny = diceY+dy[4];
//			nx = diceX+dx[4];
////			System.out.println("다음 주사위 Y좌표 : "+ny + " 현재 주사위 X좌표 : "+nx);
//			if(isBoundary(ny,nx)) {
//				if(map[ny][nx]==0) {
//					currentDice.y = ny;
//					currentDice.x = nx;
//					map[ny][nx] = currentDice.front;
//					int tempBottom = currentDice.bottom;
//					currentDice.bottom = currentDice.reer;
//					currentDice.reer = currentDice.top;
//					currentDice.top = currentDice.front;
//					currentDice.front = tempBottom;
//					sb.append(currentDice.top).append("\n");
//				}
//				else if(map[ny][nx]!=0){
//					currentDice.y = ny;
//					currentDice.x = nx;
//					currentDice.front = map[ny][nx];
//					map[ny][nx]=0;
//					int tempBottom = currentDice.bottom;
//					currentDice.bottom = currentDice.front;
//					currentDice.front = currentDice.top;
//					currentDice.top = currentDice.reer;
//					currentDice.reer = tempBottom;
//					sb.append(currentDice.top).append("\n");
//				}
//			}
//			break;
//		}
//	}
//	private static boolean isBoundary(int ny, int nx) {
//		if(ny<0 || nx<0|| ny>=N || nx>=M) return false;
//		return true;
//	}

}
