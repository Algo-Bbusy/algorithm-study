package sh1n.bj2.ps2579계단오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static final int ONESTEP = 0;
	public static final int TWOSTEP = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int layer = Integer.parseInt(br.readLine());
		int[] layerPoint = new int[layer + 1];
		for(int i = 1; i <= layer; i++) {
			layerPoint[i] = Integer.parseInt(br.readLine());
		}
		int[][] maxPoint = new int[layer + 1][2];
		maxPoint[1][ONESTEP] = layerPoint[1];
		maxPoint[1][TWOSTEP] = layerPoint[1]; // N이 2인 경우에 디폴트 TWOSTEP 값 지정
		
		for(int i = 2; i <= layer; i++) {
			// i 번째 계단에 올라올 때, 한칸으로 올라오는 경우
			maxPoint[i][ONESTEP] = maxPoint[i-1][TWOSTEP] + layerPoint[i];
			
			// i 번째 계단에 올라올 때, 두칸으로 올라오는 경우
			maxPoint[i][TWOSTEP] = Math.max(maxPoint[i-2][ONESTEP], maxPoint[i-2][TWOSTEP]) + layerPoint[i];
		}
		
		System.out.println(Math.max(maxPoint[layer][ONESTEP], maxPoint[layer][TWOSTEP]));
	}// end main
}
