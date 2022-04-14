package com.ssafy.study.homework19;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_9440_숫자더하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		while (true) {
			String[] num = br.readLine().split(" "); // 숫자들을 합쳐야 하므로 스트링으로 받는다.
			if (num.length == 1 && num[0].equals("0")) break; // 종료 조건. 
			
			ArrayList<Integer> list = new ArrayList<>();
			
			int N = Integer.parseInt(num[0]);
			int[] arr = new int[N]; // 숫자들을 담을 배열 생성.
			int zero = 0;
			
			for(int i=0; i<N; i++) {
				 arr[i] = Integer.parseInt(num[i+1]);
				if(arr[i] == 0) zero++; //0이 맨앞에 있는 경우 처리를 따로 해야하기 때문 표시.
			}
			
			Collections.sort(list);	
			
			// 이해 안가는 문법
			 if(zero > 0) {
	                int[] tempArr = new int[zero + 2];

	                for(int i = 0; i < zero + 2; i++) {
	                    tempArr[i] = arr[i];
	                }
	                leftShift(tempArr);

	                for(int i = 0; i < zero + 2; i++) {
	                    arr[i] = tempArr[i];
	                }
	            }

	            StringBuilder num1 = new StringBuilder();
	            StringBuilder num2 = new StringBuilder();
	            for (int i = 0; i < arr.length; i++) {
	                if(i % 2 == 0) num1.append(arr[i]);
	                else num2.append(arr[i]);
	            }

	            int n1 = Integer.parseInt(num1.toString());
	            int n2 = Integer.parseInt(num2.toString());

	            bw.write((n1 + n2) + "\n");
	            bw.flush();
	        }
	        bw.close();
	    }

	    private static void swap(int[] arr, int index1, int index2) {
	        int temp = arr[index1];
	        arr[index1] = arr[index2];
	        arr[index2] = temp;
	    }

	    private static void reverse(int[] arr, int start, int end) {
	        end = end - 1;
	        while(start < end) {
	            swap(arr, start, end);
	            start++;
	            end--;
	        }
	    }

	    private static void leftShift(int[] arr) {
	        int size = arr.length;
	        reverse(arr, size - 2, size);
	        reverse(arr, 0, size - 2);
	        reverse(arr, 0, size);
	    }
	}
