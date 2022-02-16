package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Java_2477 {
	//참외밭
	/*fail
	 * 북쪽 4번과 남쪽 3번은 y배열, 높이배열에 값을 저장해두고,
	 * 서쪽 2번과 동쪽 1번은 x배열, 가로배열에 값을 저장해둔다.
	 * 높이배열과 가로배열을 오름차순 혹은 내림차순으로 정렬한후 
	 * (max높이*max가로) - (min높이 * min가로)를 하면 참외밭의 넓이가 나온다.
	 * 넓이 * 참외밭 1m^2당 참외수를 곱하면 총 참외수가 나온다.
	 * 
	 * 정답풀이
	 * 각 변의 인덱스 0,1,2,3,4,5를 받고
	 * i번인덱스의 변*i+1의인덱스의 변의 각 사각형을 구한다.
	 * 사각형중 가장 큰 넓이를 지는것을 전체넓이로 두고
	 * 전체 사각형을 제외하고 각 사각형들의 총합은 구해야하는 넓이*3+파인부분의 넓이가 된다
	 * 즉 전체사각형 - ((3*전체사각형)-각 사각형의 총합)을 해주면 굳이 각 변의 위치값이 필요없다.
	 * 최종결과는 위에서 나온값에 참외수를 곱하면 된다.
	 */
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stk;
	static StringBuilder sb = new StringBuilder();
	static int N, ans;
	static ArrayList<Integer> hwList, sumList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N=Integer.parseInt(in.readLine());
		hwList = new ArrayList<>();
		sumList = new ArrayList<>();
		for(int i=0; i<6; i++) {
			stk = new StringTokenizer(in.readLine());
			int dir = Integer.parseInt(stk.nextToken());
			hwList.add(Integer.parseInt(stk.nextToken()));
		}
		calc();
		sb.append(ans);
		out.write(sb.toString());
		in.close();
		out.flush();
		out.close();
	}
	private static void calc() {
		int max_squre=0;
		int sum=0;
		for(int i=0; i<hwList.size(); i++) {
			if(i==hwList.size()-1) sumList.add(hwList.get(i)*hwList.get(0));
			else sumList.add(hwList.get(i)*hwList.get(i+1));
		}
		Collections.sort(sumList);
//		System.out.println(sumList);
		max_squre = sumList.get(sumList.size()-1);
//		System.out.println(max_squre);
		for(int i=0; i<sumList.size(); i++) {
			sum+=sumList.get(i);
		}
//		System.out.println(max_squre*3+" "+sum);
		ans =  (max_squre - ((max_squre*3)-sum))*N;
	}
}
