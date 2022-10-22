package anystep;

import java.io.*;
import java.util.StringTokenizer;

public class Java_2602 {
    // 돌다리 건너기
    // dp 문제라는 것을 깨닫기 까지 2시간 가량 소모
    // 완탐 실패
    // 풀이 실패후 서칭 그러나 제대로 된 이해 불가

    // 규칙은 총 5가지
    // 1. 왼쪽에서 오른쪽으로 이동
    // 2. 주어진 문자열에 적힌 순서대로 밟기
    // 3. 돌다리 두 개를 번갈아 가면서 건너 뜀
    // 4. 건너뛰는 칸 수는 무관
    // 5. 한 칸 이상 오른쪽으로 이동.

    //Solution 1.
    //1. 입력값으로 초기화
    //2. 천사다리로 시작한 경우의 수와 악마다리로 시작한 이동 경우의 수를 더한다.
    //3. 이동 함수 진행
    //3.1 찾은 문자열 개수가 마법의 단어 개수와 같아지면 경우의 수 증가
    //3.2 현재 dp[찾은 문자열 개수][시작 위치][다리 타입]값이 0이 아니라면 같은 경우의 수가 존재하므로 그대로 반환
    //3.3 천사다리일경우 마법의단어[찾은 문자열 개수]==천사다리[시작 위치] 혹은 악마다리일때 다음 문자열 탐색으로 이동
    //3.4 그 동안의 경우의 수의 합을 dp[찾은 문자열 개수][시작 위치][다리 타입]에 반환

    //메모리 16480KB 시간 400ms
    //풀이 시간 2시간 52분 12초
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer stk;
    static char[] angel, devil, magicKeyword;
    static int ans;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        input();
        ans = move(0,0,0) + move(0,0,1);
        System.out.println(ans);
    }

    private static int move(int findCnt, int start, int type) {
        if(findCnt == magicKeyword.length) {
            return 1;
        }
        if(dp[findCnt][start][type] != 0) {
            return dp[findCnt][start][type];
        }
        int temp = 0;
        for (int i = start; i < angel.length; i++) {
            if((type == 0 && magicKeyword[findCnt] == devil[i] || (type == 1 && magicKeyword[findCnt] == angel[i]))) {
                temp += move(findCnt+1, i+1, (type==0)?1:0);
            }
        }
        return dp[findCnt][start][type] = temp;
    }

    private static void input() throws IOException {
        magicKeyword = in.readLine().toCharArray();
        devil = in.readLine().toCharArray();
        angel = in.readLine().toCharArray();
        dp = new int[magicKeyword.length+1][angel.length+1][2];
    }

//    static String magicKeyword, angel, devil;
//    static int ans;
//    public static void main(String[] args) throws IOException {
//        magicKeyword = in.readLine();
//        devil = in.readLine();
//        angel = in.readLine();
//        move(0, 0, 0);
//        System.out.println(ans);
//    }
//
//    private static void move(int findIdx, int startIdx, int type) {
//        if(findIdx == magicKeyword.length()) {
//            ans++;
//            return;
//        }
//        //악마 돌다리
//        if(type==0) {
//            for(int i = startIdx; i<devil.length(); i++) {
//                if(magicKeyword.charAt(findIdx) == devil.charAt(i)) {
//                    move(findIdx+1,i+1,1 );
//                }
//            }
//        }
//        else {
//            for(int i = startIdx; i<angel.length(); i++) {
//                if(magicKeyword.charAt(findIdx) == angel.charAt(i)) {
//                    move(findIdx+1, i+1, 0);
//                }
//            }
//        }
//    }
}
