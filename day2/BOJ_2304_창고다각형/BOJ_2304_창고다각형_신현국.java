package sh1n.bj.ps2304창고다각형;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2304_창고다각형/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int pillarN = Integer.parseInt(br.readLine());
		List<Pillar> pillars = new ArrayList<Pillar>();
		for(int i=0; i<pillarN; i++) {
			st = new StringTokenizer(br.readLine());
			pillars.add(new Pillar(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		// #1 첫 번째 기둥 탐색
		Collections.sort(pillars);
		List<Pillar> mainPillars = new ArrayList<Pillar>();
		Pillar targetPillar = pillars.get(0);
		mainPillars.add(targetPillar);
		
		// #2 타겟 기둥 보다 큰 기둥 서치
		int cnt=1;
		while(cnt < pillarN) {
			// #3 타겟 기둥보다 높이가 크거나 같은 기둥을 타겟 기둥으로 재선정
			if(targetPillar.height <= pillars.get(cnt).height) {
				targetPillar = pillars.get(cnt);
				mainPillars.add(targetPillar);
			}
			cnt++;
		}
		int Height = targetPillar.height;
		
		// #4 타겟 기둥보다 작은 기둥 서치
		while(true) {
			int maxHeight = 0;
			int nextTargetIdx = 0;
			for(int i=pillars.indexOf(targetPillar)+1; i<pillarN; i++) {
				if(pillars.get(i).height >= maxHeight) {
					maxHeight = pillars.get(i).height;
					nextTargetIdx = i;
				}
			}
			if(pillars.indexOf(targetPillar) == pillarN-1) break;
			
			targetPillar = pillars.get(nextTargetIdx);
			mainPillars.add(targetPillar);
		}
		
		for(Pillar p : mainPillars) {
			System.out.println(p.idx + " " + p.height);
		}
		
		// #5 메인 기둥들의 넓이 구하기
		int result = Height * (mainPillars.get(mainPillars.size()-1).idx+1 - mainPillars.get(0).idx);
		int bestHeightIdx = 0;
		int bestHeight = 0;
		for(int i=0; i<mainPillars.size(); i++) {
			if(mainPillars.get(i).height > bestHeight) {
				bestHeight = mainPillars.get(i).height;
				bestHeightIdx = i;
			}
		}
		
		int leftIdx = bestHeightIdx-1;
		int leftHeight, leftWidth;
		int rightIdx = bestHeightIdx+1;
		int rightHeight, rightWidth;
		while(true) {
			if(leftIdx>=0) {
				leftHeight = bestHeight - mainPillars.get(leftIdx).height;
				leftWidth = mainPillars.get(leftIdx+1).idx - mainPillars.get(leftIdx--).idx;
				result -=  leftHeight * leftWidth;
			}
			
			if(rightIdx<mainPillars.size()) {
				rightHeight = bestHeight - mainPillars.get(rightIdx).height;
				rightWidth = (mainPillars.get(rightIdx).idx+1) - (mainPillars.get(rightIdx-1).idx+1);
				rightIdx++;
				result -= rightHeight * rightWidth;
			}
			
			if(leftIdx < 0 && rightIdx >= mainPillars.size()) break;
		}
		
		System.out.println(result);
	}// end main
	
	static class Pillar implements Comparable<Pillar> {
		int idx;
		int height;
		
		public Pillar(int idx, int height) {
			super();
			this.idx = idx;
			this.height = height;
		}

		@Override
		public int compareTo(Pillar pillar) {
			if(pillar.idx < idx) {
				return 1;
			} else if(pillar.idx > idx) {
				return -1;
			}
			return 0;
		}
	}
}
