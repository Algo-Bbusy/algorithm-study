package com.zerojei.stack_two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2304 {
	
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st;
	        int n = Integer.parseInt(br.readLine());	        
	        
	        int[] arr = new int[1001];// 1 <=  기둥 <=1000 이니깐!
	        int start = 1000;
	        int end = 0;
	        
	        for(int i = 0;i<n;i++){
	            st = new StringTokenizer(br.readLine());
	            int L = Integer.parseInt(st.nextToken());
	            int H = Integer.parseInt(st.nextToken());
	            
	            arr[L] = H; //(x, y)
	            start = Math.min(L,start); // 맨 왼쪽 부터 시작해야하니깐 
	            end = Math.max(L,end); // 맨 오른쪽 부터 시작해야하니깐
	        }

	        Stack<Integer> stack = new Stack<>();
	        
	        // 왼쪽부터 탐색
	        int num = arr[start]; 
	        for(int i = start+1; i <= end; i++){
	            if(arr[i] < num) stack.push(i);
	            else{// arr[i] >= num
	                while(!stack.isEmpty()){
	                    int p = stack.pop();
	                    arr[p] = num;
	                }
	                num = arr[i];
	            }
	        }
	        stack.clear(); //스택 초기화
	        
	        
	        num=arr[end];
	        for(int i = end-1; i >= start; i--){
	            if(arr[i] < num) stack.push(i);
	            else{
	                while(!stack.isEmpty()){
	                    int p = stack.pop();
	                    arr[p] = num;
	                }
	                num = arr[i];
	            } 
	        }

	        int sum = 0;
	        for(int l = start; l <= end; l++){
	            sum += arr[l];
	        }
	        System.out.println(sum);
	    }
	}