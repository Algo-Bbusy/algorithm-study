package sh1n.bj.ps10775공항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static class Gate {
		boolean isDocking;
		int pointer;
		
		public Gate() {
			this(false, 0);
		}

		public Gate(boolean isDocking, int pointer) {
			super();
			this.isDocking = isDocking;
			this.pointer = pointer;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		Gate[] gates = new Gate[G+1];
		for(int i=0; i<=G; i++) {
			gates[i] = new Gate();
		}
		
		for(int i=0; i<P; i++) {
			int targetGate = Integer.parseInt(br.readLine());
			Gate curGate = gates[targetGate];
			
			if(!curGate.isDocking) {
				curGate.isDocking = true;
				curGate.pointer = --targetGate;
			} else {
				int tempPointer = curGate.pointer;
				int step = 0;
				while(true) {
					step++;
					curGate = gates[tempPointer];
					if(!curGate.isDocking) {
						curGate.isDocking = true;
						curGate.pointer = --tempPointer;
						break;
					}
					tempPointer = curGate.pointer;
				}
				
				curGate = gates[targetGate];
				int temp = curGate.pointer;
				while(step-->0 && temp != 0) {
					curGate.pointer = tempPointer;
					curGate = gates[temp];
					temp = curGate.pointer;
				}
				
				if(tempPointer < 0) {
					System.out.println(i);
					System.exit(0);
				}
			}
		}
		
		System.out.println(P);
	}// end main
}
