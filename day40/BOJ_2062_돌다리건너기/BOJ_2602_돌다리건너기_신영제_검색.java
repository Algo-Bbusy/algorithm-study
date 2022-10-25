package AlgoStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2602_돌다리건너기 {
	
	/*
	 * 접근 DP에 대한 이해를 하지못하여 인터넷을 보며 학습 했습니다. [ 정확히 이해 불가 ] 
	 * DP의 점화식을 사용하여 
	 * 현재 다리 i에 있는 글자가 두루마리 j위치의 글자와 일치하는 경우의 점화식과
	 * 일치하지 않는 점화식을 작성하여 인덱스를 구합니다.
	 * https://mytodays.tistory.com/40
	 */
	
    public static final int ANGEL = 0, DEVIL = 1;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
 
        st = new StringTokenizer(br.readLine());
        String magicScroll = st.nextToken();

        st = new StringTokenizer(br.readLine());
        String angel = st.nextToken();
        
        st = new StringTokenizer(br.readLine());
        String devil = st.nextToken();

 
        System.out.println(solve(angel, devil, magicScroll));
 
    }
    private static int solve(String angel, String devil, String magicScroll) {
        int[][][] dp = new int[2][angel.length()][magicScroll.length()];
 
        // 기저사례
        if(angel.charAt(0) == magicScroll.charAt(0))
            dp[ANGEL][0][0] = 1;
        if(devil.charAt(0) == magicScroll.charAt(0))
            dp[DEVIL][0][0] = 1;
 
        for(int i = 1; i < angel.length(); i++){
 
            dp[ANGEL][i][0] = angel.charAt(i) == magicScroll.charAt(0) ? dp[ANGEL][i-1][0] + 1 : dp[ANGEL][i-1][0];
            for(int j = 1; j < magicScroll.length(); j++)
                dp[ANGEL][i][j] = angel.charAt(i) == magicScroll.charAt(j) ? dp[ANGEL][i-1][j] + dp[DEVIL][i-1][j-1] : dp[ANGEL][i-1][j];
 
            dp[DEVIL][i][0] = devil.charAt(i) == magicScroll.charAt(0) ? dp[DEVIL][i-1][0] + 1 : dp[DEVIL][i-1][0];
            for(int j = 1; j < magicScroll.length(); j++)
                dp[DEVIL][i][j] = devil.charAt(i) == magicScroll.charAt(j) ? dp[DEVIL][i-1][j] + dp[ANGEL][i-1][j-1] : dp[DEVIL][i-1][j];
        }
 
        return dp[DEVIL][devil.length()-1][magicScroll.length()-1] + dp[ANGEL][angel.length()-1][magicScroll.length()-1];
    }
}