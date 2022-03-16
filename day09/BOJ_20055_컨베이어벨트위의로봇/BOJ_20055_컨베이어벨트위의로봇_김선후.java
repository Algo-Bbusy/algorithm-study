package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_20055 {
	// 컨테이어 벨트 위의 로봇
//	문제 지문이 굉장히 불친절하고 난해해서 구현목표자체가 이해하기가 어려웠습니다.
//	특히 로봇이 대체 어느시점, 어느단계에서 벨트에 올라가는지, 벨트에서 내려와서 어찌 행동하는지를
//	이해하기까지 시간을 많이 사용한 문제였습니다.
//	로봇이 내려오고난 이후 (N-1)~((2*N)-1)까지는 로봇이 없다는 걸 지문에서 이해하기 어려워서
//	구글링으로 지문에 대한 힌트를 얻었습니다.
//	차근차근 단계별로 동작을 요약해보자면 다음과 같다.
//	1. 벨트 한칸 회전 - 내려가는 위치에서 로봇 없애기
//	2. 로봇 이동(앞이 빈공간이고 내구도 1이상) - 내려가는 위치에서 로봇없애기
//	3. 로봇 올리기 - 여기서 로봇이 벨트에 올라감.!!
//	4. 내구도 체크 (내구도 0 K개 이상일시 단계종료) 후에 1단계로 순환.
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N, K, ans;
	static Belt[] beltPoint;
	static int SIZE;
	static class Belt{
		int durability;
		boolean robot;
		public Belt(int durability, boolean robot) {
			super();
			this.durability = durability;
			this.robot = robot;
		}
		
	}
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		SIZE = 2*N;
		beltPoint = new Belt[SIZE];
		
		stk = new StringTokenizer(in.readLine());
		for(int i=0; i<SIZE; i++) beltPoint[i]=new Belt(Integer.parseInt(stk.nextToken()), false);
		ans = startProcess();
		out.write(ans+"");
		out.flush();
		out.close();
		in.close();
	}
	private static int startProcess() {
		int step=0;
		while(true) {
			step++; //단계횟수증가
			rotateBelt(); //1.벨트 한칸 회전
			moveRobot();  //2.로봇 이동
			upRobot();	  //3.로봇 올리기 - 로봇추가
			if(checkDurability()) break; //4. 내구도 체크
		}
		return step;
	}
	private static boolean checkDurability() {
		int cnt=0;
		for(int i=0; i<SIZE; i++) {
			if(beltPoint[i].durability==0) cnt++;
		}
		return cnt>=K ? true : false;
	}
	private static void upRobot() {
		//맨 처음 위치에 로봇이 없고 내구도 1이상일때
		if(!beltPoint[0].robot && beltPoint[0].durability>=1) {
			beltPoint[0].durability--;
			beltPoint[0].robot = true;
		}
	}
	private static void moveRobot() {
		int halfSize = SIZE / 2;
		for(int i = halfSize-2; i>=0; i--) {
			if(beltPoint[i].robot) { //해당 인덱스에 로봇이 있다면
				if(i+1 == halfSize-1) { //다음 인덱스가 내려가는 위치라면
					if(beltPoint[i+1].durability>=1) { //다음 인덱스 내구도가 1이상이면
						beltPoint[i].robot = false;    //로봇을 내린다.
						beltPoint[i+1].durability--;   //다음 인덱스의 내구도를 내린다.
						continue;
					}
				}
				//다음 인덱스에 이미 로봇이 있거나 내구도가 0인경우 스킵
				if(beltPoint[i+1].robot || beltPoint[i+1].durability==0) continue;
				beltPoint[i+1].robot = true;
				beltPoint[i+1].durability--;
				beltPoint[i].robot=false;
			}
		}
		
	}
	private static void rotateBelt() {
		//마지막 인덱스 배열값 임시저장
		boolean tempRobot = beltPoint[SIZE-1].robot;
		int tempDurability = beltPoint[SIZE-1].durability;
		int halfSize = SIZE / 2;
		for(int i = SIZE-1; i>=1; i--) {
			beltPoint[i].robot = beltPoint[i-1].robot;
			beltPoint[i].durability = beltPoint[i-1].durability;
			if(i==halfSize-1) beltPoint[i].robot = false; // 절반구역에서 로봇은 바로 내려간다.
		}
		//덮어쒸어지기 전 마지막 인덱스 배열값 첫번째 값으로 넣기
		beltPoint[0].robot = tempRobot;
		beltPoint[0].durability = tempDurability;
	}

}
