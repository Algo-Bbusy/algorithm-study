package sh1n.bj.ps2309일곱난쟁이;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	
	public static final int HEIGHT_SUM = 100;
	public static final int N = 9;
	public static final int C = 7;
	static int[] dwarfInfo;
	static int[] selectedDwarf = new int[C]; 
	static List<Integer> sevenDwarf = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps2309일곱난쟁이/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dwarfInfo = new int[N];
		for(int i=0; i<N; i++) {
			dwarfInfo[i] = Integer.parseInt(br.readLine());
		}
		
		search(0, 0);
		
		Collections.sort(sevenDwarf);
		for(int dwarf : sevenDwarf) {
			System.out.println(dwarf);
		}
	}

	public static void search(int cnt, int start) {
		if(cnt == C) {
			int sum = 0;
			
			for(int i=0; i<C; i++) {
				sum += selectedDwarf[i];
			}
			
			if(sum == HEIGHT_SUM && sevenDwarf.isEmpty()) {
				for(int i=0; i<selectedDwarf.length; i++) {
					sevenDwarf.add(selectedDwarf[i]);
				}
			}
			return;
		}
		
		for(int i=start; i<N; i++) {
			selectedDwarf[cnt] = dwarfInfo[i];
			search(cnt+1, i+1);
		}
	}
}
