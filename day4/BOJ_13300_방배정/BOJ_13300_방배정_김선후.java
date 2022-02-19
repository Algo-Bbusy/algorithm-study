package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_13300 {
	// 방배정
	/*
	 * 학년 1-6학년까지 있음.
	 * 여학생과 남학생으로 성별이 나뉨
	 * 학년 여학생 남학생
	 * 1
	 * 2
	 * 3
	 * 4
	 * 5
	 * 6
	 * 7*2 혹은 2*7 크기의 배열로 나타낼 수 있음.
	 * 학년은 1인덱스부터 사용
	 * 해당 학년의 성별에 따라서 인덱스의 배열값 증가
	 * 1학년부터 6학년까지 탐색을 돌리며 3가지 경우로 방 수를 계산함.
	 * 1.해당학년의 성별이 K수보다 작거나 같다면 +1
	 * 2.해당학년의 성별이 %K==0이면 /K값만큼 방 수증가
	 * 3.해당학년의 성별이 %K!=0이면 /K값만큼 방수를 증가시키고 한번 더 증가.
	 */
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N,K,ans=0;
	static int[][] student;
	public static void main(String[] args) throws IOException {
		stk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		student = new int[7][2];
		for(int i=0; i<N; i++) {
			stk = new StringTokenizer(in.readLine());
			int sex = Integer.parseInt(stk.nextToken());
			int grade = Integer.parseInt(stk.nextToken());
			if(sex==0) student[grade][0]++;
			else if(sex==1) student[grade][1]++;
		}
		for(int i=1; i<7; i++) {
			if(student[i][0]==0) continue;
			else if(student[i][0]<=K) ans++;
			else if(student[i][0]%K==0) ans+=student[i][0]/K;
			else if(student[i][0]%K!=0) ans+=student[i][0]/K+1;
		}
		for(int i=1; i<7; i++) {
			if(student[i][1]==0) continue;
			else if(student[i][1]<=K) ans++;
			else if(student[i][1]%K==0) ans+=student[i][1]/K;
			else if(student[i][1]%K!=0) ans+=student[i][1]/K+1;
		}
		out.append(ans+"");
		in.close();
		out.flush();
		out.close();
	}

}
