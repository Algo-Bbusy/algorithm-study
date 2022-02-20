package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Java_2605 {
	// 줄 세우기
	/*
	 * 학생 수 만큼 번호를 받아서 넣을 수 있는 학생리스트를 사용한다. 
	 * 학생 수 만큼 입력받은 번호를 num이 0일 경우에는 리스트에 그대로 있게 한다.
	 * 학생 인덱스(들어온 순서) - num(입력받은 숫자)가 리스트에서 위치한 인덱스 값이 된다.
	 */
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb= new StringBuilder();
	static int N;
	static ArrayList<Integer> StudentList = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N=Integer.parseInt(in.readLine());
		stk = new StringTokenizer(in.readLine());
		StudentList.add(-1);
		for(int n=1; n<=N; n++) {
			int num = Integer.parseInt(stk.nextToken());
			StudentList.add(n-num,n);
//			System.out.println("현재 n : "+n+" 현재 n-num :"+(n-num));
//			for(int i=0; i<StudentList.size(); i++) System.out.println(StudentList.get(i)+" ");
		}
		for(int i=1; i<=N; i++) sb.append(StudentList.get(i)+" ");
		out.write(sb.toString());
		in.close();
		out.flush();
		out.close();
	}
}
