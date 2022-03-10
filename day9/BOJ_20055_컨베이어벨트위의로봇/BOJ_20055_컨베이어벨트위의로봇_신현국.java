package sh1n.bj.ps20055컨베이어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_robotList {
	
	static class Conveyor {
		int durability;
		boolean hasRobot;
		
		public Conveyor(int durability, boolean hasRobot) {
			super();
			this.durability = durability;
			this.hasRobot = hasRobot;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Conveyor> conveyorBelt = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N*2; i++) {
			int durability = Integer.parseInt(st.nextToken());
			conveyorBelt.add(new Conveyor(durability, false));
		}
		
		int kCnt = 0;
		int step = 0;
		Queue<Integer> robotIndexInfo = new LinkedList<>();
		while(kCnt < K) {
			step++;
			shiftConveyor(conveyorBelt);
			
			int robotN = robotIndexInfo.size();
			for(int i=0; i<robotN; i++) {
				int robotIndex = robotIndexInfo.poll() + 1;
				Conveyor temp = conveyorBelt.get(robotIndex);
				if (robotIndex == N-1) {
					temp.hasRobot = false;
				} else {
					Conveyor nextConveyor = conveyorBelt.get(robotIndex + 1);
					if (!nextConveyor.hasRobot && nextConveyor.durability > 0) {
						temp.hasRobot = false;
						if (--nextConveyor.durability == 0) {
							kCnt++;
						}
						if (robotIndex+1 != N-1) {
							nextConveyor.hasRobot = true;
							robotIndexInfo.offer(robotIndex + 1);
							continue;
						}
					}
					robotIndexInfo.offer(robotIndex);
				}
			}
			
			Conveyor curConveyor = conveyorBelt.get(0);
			if(curConveyor.durability > 0) {
				if(--curConveyor.durability == 0) {
					kCnt++;
				}
				curConveyor.hasRobot = true;
				robotIndexInfo.offer(0);
			}
			
		}
		
		System.out.println(step);
	}// end main
	
	public static void shiftConveyor(List<Conveyor> conveyorBelt) {
		int size = conveyorBelt.size() - 1;
		Conveyor temp = conveyorBelt.get(size);
		conveyorBelt.remove(size);
		conveyorBelt.add(0, temp);
	}
	
}
