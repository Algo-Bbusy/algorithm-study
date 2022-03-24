package sh1n.bj.ps12904A와B;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cntAOfS = 0;
		int cntBOfS = 0;
		String S = br.readLine();
		for(int i=0; i<S.length(); i++) {
			char elementOfS = S.charAt(i);
			if(elementOfS == 'A') {
				cntAOfS++;
			} else {
				cntBOfS++;
			}
		}
		StringBuilder makeAB = new StringBuilder(S);
		
		int cntAOfT = 0;
		int cntBOfT = 0;
		String T = br.readLine();
		for(int i=0; i<T.length(); i++) {
			char elementOfT = T.charAt(i);
			if(elementOfT == 'A') {
				cntAOfT++;
			} else {
				cntBOfT++;
			}
		}
		
		int opportunityA = cntAOfT - cntAOfS;
		int opportunityB = cntBOfT - cntBOfS;
		makeT(makeAB, opportunityA, opportunityB, T);
		System.out.println(0);
	} // end main()
	
	public static void makeT(StringBuilder makeAB, int opportunityA, int opportunityB, String target) {
		StringBuilder reverseCheck = new StringBuilder(makeAB.toString());
		if(!target.contains(makeAB.toString()) && !target.contains(reverseCheck.reverse().toString())) {
			return;
		}
		
		if(opportunityA == 0 && opportunityB == 0) {
			if(makeAB.toString().equals(target)) {
				System.out.println("1");
				System.exit(0);
			}
		}
		
		if(opportunityA != 0) {
			makeAB.append("A");
			makeT(makeAB, --opportunityA, opportunityB, target);
			opportunityA++;
			makeAB.setLength(makeAB.length() - 1);
		}
		
		if(opportunityB != 0) {
			makeAB.reverse();
			makeAB.append("B");
			makeT(makeAB, opportunityA, --opportunityB, target);
			makeAB.setLength(makeAB.length() - 1);
			makeAB.reverse();
			opportunityB++;
		}
	}
}
