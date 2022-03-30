package sh1n.bj.ps14891톱니바퀴;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Wheel {
		char[] position;
		int leftPointer;
		int rightPointer;
		int dir;
		boolean isRun;

		public Wheel(char[] position, int leftPointer, int rightPointer, int dir, boolean isRun) {
			super();
			this.position = position;
			this.leftPointer = leftPointer;
			this.rightPointer = rightPointer;
			this.dir = dir;
			this.isRun = isRun;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	} // end main()
	
}

