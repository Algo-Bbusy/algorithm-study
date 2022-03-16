package sh1n.bj.ps8983사냥꾼;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {
	
	static int killCnt = 0;
	
	static class KillPoint implements Comparable<KillPoint> {
		int position;

		public KillPoint(int position) {
			super();
			this.position = position;
		}
		
		public void kill(List<int[]> animalPos, int rangeOfKP) {
			int animal_x, animal_y;
			int distance;
			for(int i=animalPos.size()-1; i>=0; i--) {
				animal_x = animalPos.get(i)[0];
				animal_y = animalPos.get(i)[1];
				distance = Math.abs(this.position - animal_x) + animal_y;
				
				if(rangeOfKP >= distance) {
					animalPos.remove(i);
					killCnt++;
					continue;
				}
				
				if(this.position > animal_x) {
					animalPos.remove(i);
					continue;
				}
				
				if(this.position + rangeOfKP <= animal_x && animal_y > 1) {
					break;
				}
			}
		}
		
		@Override
		public int compareTo(KillPoint kp) {
			// TODO Auto-generated method stub
			return this.position - kp.position;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps8983사냥꾼/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int killPointNumber = Integer.parseInt(st.nextToken());  
		int animalNumber = Integer.parseInt(st.nextToken());  
		int rangeOfKP = Integer.parseInt(st.nextToken());  
		
		KillPoint[] killPoints = new KillPoint[killPointNumber];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<killPointNumber; i++) {
			killPoints[i] = new KillPoint(Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(killPoints);
		
		List<int[]> animalPos = new ArrayList<int[]>();
		int animal_x, animal_y;
		for(int i=0; i<animalNumber; i++) {
			st = new StringTokenizer(br.readLine());
			animal_x = Integer.parseInt(st.nextToken());
			animal_y = Integer.parseInt(st.nextToken());
			if(animal_y > rangeOfKP) continue;
			animalPos.add(new int[] {animal_x, animal_y});
		}
		Collections.sort(animalPos, ( (pos1, pos2) -> pos1[0] == pos2[0] ? pos2[1] - pos1[1] : pos2[0] - pos1[0] ));
		
		for(int i=0; i<killPointNumber; i++) {
			killPoints[i].kill(animalPos, rangeOfKP);
		}
		
		sb.append(killCnt);
		System.out.println(sb.toString());
	}// end main
	
}
