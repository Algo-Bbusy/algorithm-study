package sh1n.bj.ps14696딱지놀이;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/sh1n/bj/ps14696딱지놀이/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int round = Integer.parseInt(br.readLine());
		int winner = 0;
		
		for(int i=0; i<round; i++) {
			int[] aDeck = new int[4+1];
			int[] bDeck = new int[4+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int deckN = Integer.parseInt(st.nextToken());
			for(int j=0; j<deckN; j++) {
				aDeck[Integer.parseInt(st.nextToken())]++;
			}
			
			st = new StringTokenizer(br.readLine());
			deckN = Integer.parseInt(st.nextToken());
			for(int j=0; j<deckN; j++) {
				bDeck[Integer.parseInt(st.nextToken())]++;
			}
			
			if(aDeck[4] > bDeck[4]) {
				winner = 1;
			} else if(aDeck[4] == bDeck[4]) {
				if(aDeck[3] > bDeck[3])	{
					winner = 1;
				} else if(aDeck[3] == bDeck[3]) {
					if(aDeck[2] > bDeck[2]) {
						winner = 1;						
					} else if (aDeck[2] == bDeck[2]) {
						if(aDeck[1] > bDeck[1]) {
							winner = 1;
						} else if(aDeck[1] == bDeck[1]) {
							winner = 2;
						}
					}
				}
			}
			
			if(winner == 0) {
				System.out.println("B");
			} else if(winner == 1) {
				System.out.println("A");
			} else {
				System.out.println("D");
			}
			
			winner = 0;
		}
	}// end main()
}
