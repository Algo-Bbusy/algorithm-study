package com.ssafy.study.homework16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 모르겠습니다. 
 * 
 * 이분탐색 공부를 하고왔습니다. 
 * 오름차순 정렬하여 low + high가 아닌 
 * 현재 순서 그대로 K개의 그룹으로 나눠 야 했으므로 현재 그대로에서 high값을 모두 더해준 값으로 했습니다.
 * 최소값을 뽑아야 하므로 low값을 출력해야합니다. -> low를 조절 해야한다? 
 * 
 * mid값을 가지고 arr배열을 계속 더해가며 작업을 해야하는지? 이후 구현이 어려웠습니다 ㅠㅠ  
 */

public class BOJ_17951_흩날리는시험지속에내평점이느껴진거야 {

	 public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = null;
	        
	        st = new StringTokenizer(br.readLine());
	        int N = Integer.parseInt(st.nextToken());
	        int K = Integer.parseInt(st.nextToken());	        
	        
	        int[] arr = new int[N];
	        
	        int high = 0;
	        int low = 0; // 최소값을 뽑아야 하므로 low출력.
	        
	        st = new StringTokenizer(br.readLine());
	        for (int i = 0; i < N; i++) {
	            arr[i] = Integer.parseInt(st.nextToken());
	            high += arr[i]; //그룹 맞은 개수 총합.
	        }
	        
	        while(high >= low) { // low값이 high값과 같아지거나 커지기 전까지 반복.
	        	int mid = (low+high) / 2; 
	        	int sum = 0;
	        	
	        	for(int i = 0; i < N; i++) {
	        		sum = arr[i];
	        		
	        		if(sum >= mid) {
	        			
	        		}
	        	}
	        	
	        }
	        System.out.println(low);
	    }
	}
