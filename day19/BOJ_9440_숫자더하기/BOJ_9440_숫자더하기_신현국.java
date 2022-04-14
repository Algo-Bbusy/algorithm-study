package sh1n.bj2.ps9440숫자더하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**	각 숫자를 어떻게 나눠야 되는지 그리디하게 접근 => 큰 수부터 1의 자리 > 10의 자리 > 100의 자리 순으로 처리하면 최솟값
 * 	step 01. 입력받은 숫자를 내림차순으로 정렬하는 pq에 담는다. 이때, 담는 숫자가 0이면 담지 않고 zeroCnt를 1 증가 시킨다.
 *	step 02. 2개의 그룹을 관리할 리스트를 생성한다.
 *	step 03. pq에서 숫자를 1개씩 꺼내면서 2개의 그룹에 1개씩 순서대로 저장한다.
 *		 03-1. pq의 사이즈가 짝수인 경우에는 각 그룹이 동일한 자리수를 갖고, 홀수인 경우에는 A그룹이 1자리 더 길다.
 *	step 04. (03-1.)을 고려하여 그룹 B, A순으로 zeroCnt 만큼, 각 그룹에 (?0???) 0에 해당하는 자리에 0을 삽입한다.
 *	step 05. 각 그룹의 합을 출력한다. 
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if(N == 0) break;
			
			int[] numbers = new int[N];
			for(int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			// 큰 순으로 pq에 숫자 담기
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.reverseOrder());
			int zeroCnt = 0;
			for(int i = 0; i < N; i++) {
				if(numbers[i] == 0) zeroCnt++;
				else pq.offer(numbers[i]);
			}
			
			// 각 그룹에 큰 수부터 삽입
			List<Integer> groupA = new ArrayList<>();
			List<Integer> groupB = new ArrayList<>();
			while(true) {
				if(!pq.isEmpty()) {
					groupA.add(pq.poll());
				} else {
					break;
				}
				
				if(!pq.isEmpty()) {
					groupB.add(pq.poll());
				} else {
					break;
				}
			}
			
			// (?0???) 0에 해당하는 위치에 0 삽입
			while(zeroCnt > 0) {
				groupB.add(groupB.size() - 1, 0);
				if(--zeroCnt == 0) break;
				
				groupA.add(groupA.size() - 1, 0);
				zeroCnt--;
			}
			
			// 결과 출력
			int sum = 0;
			for(int i = 0; i < groupA.size(); i++) {
				sum += groupA.get(i) * Math.pow(10, i);
			}
			for(int i = 0; i < groupB.size(); i++) {
				sum += groupB.get(i) * Math.pow(10, i);
			}
			
			System.out.println(sum);
		}
		
	}// end main
}
