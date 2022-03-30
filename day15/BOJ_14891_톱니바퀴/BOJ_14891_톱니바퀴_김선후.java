package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_14891 {
	// 톱니바퀴
	//문제를 접근법을 생각하는 시간보다 문제 자체의 지문을 이해하는데 시간이 굉장히 오래 걸렸습니다.
	//결국 두 톱니바퀴간의 연결부위가 같냐 다르냐로 회전여부를 정해준다는 말을 이해하는데 오래 걸렸고 입력받는 정보들을 이해하는데 시간이 걸렸습니다.
	//Solution 1.
	//1.톱니바퀴 2차원 배열 gear[i][j]에 i는 톱니바퀴 번호 j는 톱니바퀴 상태를 입력 받는다.
	//2.회전 횟수 K만큼 반복문을 돌아가면서 해당 톱니바퀴번호에 대한 회전방향 dir배열을 초기화해준다.
	//3.톱니바퀴의 왼쪽과 오른쪽 방향의 회전방향을 체크해주고 dir배열을 입력해준다.
	//4.dir배열이 완성되면 모든 톱니바퀴를 지정된 방향으로 회전시켜준다. 회전이 끝나면 K번만큼 1번으로 돌아가서 반복한다.
	//5.톱니바퀴 번호에 맞게끔 ans의 결과에 더해준다.
	//메모리 14484KB 시간 128ms
	//풀이시간 56분 42초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb= new StringBuilder();
	static int[][] gear = new int[4][8];
	static int ans=0, K;
	static int[] dir;
	public static void main(String[] args) throws IOException {
		for(int i=0; i<gear.length; i++) {
			String[] str = in.readLine().split("");
			for(int j=0; j<str.length; j++) {
				gear[i][j] = Integer.parseInt(str[j]);
			}
		}
		K = Integer.parseInt(in.readLine());
		while(K-- >0) {
			stk = new StringTokenizer(in.readLine());
			int idx = Integer.parseInt(stk.nextToken())-1;
			int rotateDir = Integer.parseInt(stk.nextToken());
			
			dir = new int[4];
			dir[idx]=rotateDir;
			
			checkLeft(idx,rotateDir*-1);
			checkRight(idx,rotateDir*-1);
			
			for(int i=0; i<dir.length; i++) rotateGear(i, dir[i]);
		}
		for(int i=0; i<gear.length; i++) {
			if(gear[i][0]==1) {
				ans += 1*Math.pow(2, i);
//				System.out.println(ans);
			}
		}
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static void rotateGear(int idx, int rotateDir) {
		int tempGear=0;
		if(rotateDir==1) {
			tempGear=gear[idx][7];
			for(int i=gear[idx].length-1; i>0; i--) {
				gear[idx][i] = gear[idx][i-1];
			}
			gear[idx][0]=tempGear;
		}else if(rotateDir==-1) {
			tempGear=gear[idx][0];
			for(int i=0; i<gear[idx].length-1; i++) {
				gear[idx][i] = gear[idx][i+1];
			}
			gear[idx][7]=tempGear;
		}
	}
	private static void checkRight(int left, int rotateDir) {
		int right = left+1;
		if(right>3) return;
		if(gear[left][2] != gear[right][6]) {
			dir[right]=rotateDir;
			checkRight(right, rotateDir*-1);
		}
	}
	private static void checkLeft(int right, int rotateDir) {
		int left = right-1;
		if(left<0) return;
		if(gear[left][2] != gear[right][6]) {
			dir[left]=rotateDir;
			checkLeft(left, rotateDir*-1);
		}
	}

}
