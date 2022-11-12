package src.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int rain = 0;
        int[] blockInfo = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++){
            blockInfo[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=1; i<W; i++){
            int left = 0;
            int right = 0;

            for (int j=i-1; j>=0; j--){
                left = Math.max(left, blockInfo[j]);
            }

            for (int j=i+1; j<W; j++){
                right = Math.max(right, blockInfo[j]);
            }

            if(left<blockInfo[i] || right<blockInfo[i]) continue;;
            rain += Math.min(left,right)-blockInfo[i];
        }
        System.out.println(rain);
    }
}
