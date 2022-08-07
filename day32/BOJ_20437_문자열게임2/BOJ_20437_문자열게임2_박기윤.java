package BOJ.문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main_BJ_20437_문자열게임2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        while (T-- > 0) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());
            int length = W.length();
            // 각 알파벳의 등장 인덱스를 저장할 맵
            for (char c = 'a'; c <= 'z'; c++)
                map.put(c, new ArrayList<>());
            for (int i = 0; i < length; i++)
                map.get(W.charAt(i)).add(i);
            System.out.println(map);
            int min = length, max = 0;
            ArrayList<Integer> list;
            for (Map.Entry<Character, ArrayList<Integer>> entry : map.entrySet()) {
                list = entry.getValue();
                if (list.isEmpty()) continue;
                for (int i = 0; i < list.size() - K + 1; i++) {
                    min = Math.min(min, list.get(i + K - 1) - list.get(i) + 1);
                    max = Math.max(max, list.get(i + K - 1) - list.get(i) + 1);
                }
            }
            if (max != 0) sb.append(min).append(" ").append(max).append(" ").append("\n");
            else sb.append("-1").append("\n");
            map.clear();
        }
        System.out.println(sb);
        br.close();
    }
}
