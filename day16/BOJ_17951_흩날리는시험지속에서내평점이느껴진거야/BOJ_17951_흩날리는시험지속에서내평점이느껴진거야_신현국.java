package sh1n.bj2.ps17951흩날리는시험지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간 제한 : 1000 ms
 * 메모리 제한 : 256 MB
 * 완탐 = 시간초과 : (10^5) C (10^5/2)
 * 
 * Case 1. (N/K)개를 포함하는 임의의 그룹 K개를 선택하고, 각 그룹의 점수를 조절하며 접근 (그룹에 초점을 맞춰서 접근)
 * result : 실패 // 양 측 그룹의 합이 같으면서 가장 가까운 점수가 서로 같은 경우 처리 불가 => 무수히 많은 조건분기
 * step 01. K개의 그룹으로 균등하게 그룹을 나눔
 * step 02. 그룹 점수의 합이 가장 낮은 그룹 선택 (PriorityQueue를 이용해 그룹의 합을 저장하고 하나씩 꺼내면서 최솟값 그룹 선택)
 * 		02-1. 해당 그룹의 좌측과 우측을 비교
 * 		02-2. 더 높은 그룹쪽의 점수 1개 가져옴(가장 가까운 점수)
 * step 03. #02 과정을 반복
 * step 04. #02 과정 중에 가져온 점수를 다시 가져가는 경우 두 그룹의 합이 최소가 되는 경우로 그룹을 픽스하고 반복 종료
 * step 05. 형성된 그룹중 가장 낮은 점수 출력
 *
 * Case 2. K개의 그룹으로 나누었을때 받을 수 있는 점수는 N*0점(minScore) ~ N*20(maxScore)점인 점을 고려, 받을 수 있는 최대 점수를 조절하며 접근 (점수에 초점을 맞춰서 접근)
 * result : 성공 // 메모리(21340 KB), 시간(204 ms)
 * step 01. 받을 수 있는 최소 점수는 minScore, 최대 점수는 maxScore이므로 최대 점수를 (0 + N*20)/2(targetScore) 점으로 가정하고 바이너리서치 진행
 * 		01-1. 시험점수 앞에서부터 하나씩 더하다가 targetScore를 넘는 순간 그룹++
 * 		01-2. 모든 점수를 다 서치했을 때 그룹의 수가 K개보다 적으면 targetScore가 너무 높다는 것을 의미함 → maxScore를 targetScore로 변경하고 새로운 targetScore 업데이트
 * 		01-3. 모든 점수를 다 서치했을 때 그룹의 수가 K개보다 많으면 targetScore가 너무 낮다는 것을 의미함 → minScore를 targetScore로 변경하고 새로운 targetScore 업데이트
 * 		01-4*. 모든 점수를 다 서치했을 때 그룹의 수가 K개랑 같으면 targetScore가 더 높을 가능성이 존재 → minScore를 targetScore로 변경하고 새로운 targetScore 업데이트
 * 		01-5. minScore가 maxScore를 넘는 순간 바이너리 서치 종료
 * step 02. 바이너리 서치가 종료된 시점에 minScore 담긴 점수가 마지막으로 성공한 점수임으로 현수가 받을 수 있는 최대 점수
 * 
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] score = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}
		
		int minScore = 0;
		int maxScore = N * 20;
		while(minScore < maxScore - 1) {
			int targetScore = (minScore + maxScore) / 2;
			
			int group = 0;
			int scoreSum = 0;
			for(int i=0; i<N; i++) {
				scoreSum += score[i];
				if(scoreSum >= targetScore) {
					scoreSum = 0;
					group++;
				}
			}
			
			if(group < K) {
				maxScore = targetScore;
			} else {
				minScore = targetScore; 
			}
		}
		
		System.out.println(minScore);
	}// end main
}
