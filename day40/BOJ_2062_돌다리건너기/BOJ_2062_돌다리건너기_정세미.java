import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String substr;
    static String[] s;
    static int N;
    static int M;
    static Set<Integer> emptySet = new HashSet<>();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        substr = br.readLine();
        s = new String[2];
        s[0] = br.readLine();
        s[1] = br.readLine();
        N = s[0].length();
        M = substr.length();

        Map<Character, Set<Integer>> charToInt = new HashMap<>();
        for (int i = 0; i < M; i++) {
            Set<Integer> s = charToInt.getOrDefault(substr.charAt(i), new TreeSet<>());
            s.add(i + 1);
            charToInt.put(substr.charAt(i), s);
        }

        int[][] temp = new int[2][M + 1];
        temp[0][0] = temp[1][0] = 1;
        for (int i = 0; i < N; ++i) {
            int[][] newTemp = new int[2][];
            newTemp[0] = temp[0].clone();
            newTemp[1] = temp[1].clone();

            for (int j = 0; j < 2; j++) {
                for (int k : charToInt.getOrDefault(s[j].charAt(i), emptySet)) {
                    newTemp[j][k] += temp[j ^ 1][k - 1];
                }
            }
            temp = newTemp;
        }

        System.out.println(temp[0][M] + temp[1][M]);
    }
}
