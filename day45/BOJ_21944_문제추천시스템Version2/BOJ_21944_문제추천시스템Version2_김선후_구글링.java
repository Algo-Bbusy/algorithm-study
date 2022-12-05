package anystep;

import java.io.*;
import java.util.*;

public class Java_21944 {
    //문제 추천 시스템 Version 2
    //구글링
    //메모리 73096KB 시간 960ms
    //Set과 Map 등의 자료구조 등을 이용해야 하는 문제인것은 문제의 제한을 보고 인지.
    //최악 P 100000 * L 100 * G 100을 * M 10000번을 탐색해야 할 수도 있는 경우 이기에 일반적인 순차탐색은 해결법에서 배제
    //키값을 통한 정보접근이 곧바로 가능한 Set과 Map을 통한 자료구조를 이용해 빠르게 정보에 대한 조회와 수정,추가,삭제가 필요한 문제임을 확인.
    //다만 Set과 Map의 지니고 있는 메서드들이 기억이 나지 않아 구글링.
    //추천리스트의 추가와 삭제, 조회 3가지를 비롯하여 총 5가지의 명령분기를 지니는 문제.
    //조회 3가지의 전제 조건과 삭제 조건 중 문제분류 G를 통한 분류와 난이도 L을 통한 분류, 아무상관없는 전체 분류와 P로 해당 문제의 L과 G의 접근에 사용할 자료구조가 필요.
    //Solution 1.
    //1. 문제의 번호, 난이도, 분류를 요소로 갖는 클래스 선언
    //2. 최초 입력값 초기화
    //3. 명령문 개수만큼 반복문 실행
    //4. 명령의 분기조건에 따른 조회,추가,삭제 실행
    //풀이시간 3시간 24분 12초
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer stk;
    static StringBuilder sb = new StringBuilder();
    static int N, M;

    public static class Problem implements Comparable<Problem> {
        int P;
        int L;
        int G;

        public Problem(int P, int L, int G) {
            this.P = P;
            this.L = L;
            this.G = G;
        }

        @Override
        public int compareTo(Problem o) {
            if (L == o.L) {
                return Integer.compare(P, o.P);
            }
            return Integer.compare(L, o.L);
        }
    }

    static TreeSet<Problem> problems;
    //key : G value : problems
    static Map<Integer, TreeSet<Problem>> problemMap;
    //key : P value : L, G
    //solved command
    static Map<Integer, Integer> LMap;
    static Map<Integer, Integer> GMap;

    public static void main(String[] args) throws IOException {
        input();
        command();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(in.readLine());
        problems = new TreeSet<>();
        problemMap = new HashMap<>();
        LMap = new HashMap<>();
        GMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(in.readLine());
            int P = Integer.parseInt(stk.nextToken());
            int L = Integer.parseInt(stk.nextToken());
            int G = Integer.parseInt(stk.nextToken());
            problems.add(new Problem(P, L, G));

            if (problemMap.containsKey(G)) {
                TreeSet<Problem> temp = problemMap.get(G);
                temp.add(new Problem(P, L, G));
                problemMap.replace(G, temp);
            } else {
                TreeSet<Problem> temp = new TreeSet<>();
                temp.add(new Problem(P, L, G));
                problemMap.put(G, temp);
            }
            LMap.put(P, L);
            GMap.put(P, G);
        }
        M = Integer.parseInt(in.readLine());
    }

    private static void command() throws IOException {
        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(in.readLine());
            String cmd = stk.nextToken();
            //G중에서 가장 크고 작은 문제 번호
            if (cmd.equals("recommend")) {
                int G = Integer.parseInt(stk.nextToken());
                int x = Integer.parseInt(stk.nextToken());
                if (x == 1) {
                    System.out.println(problemMap.get(G).last().P);
                } else {
                    System.out.println(problemMap.get(G).first().P);
                }
            }
            //전체 중에서 가장 크고 작은 문제 번호
            else if (cmd.equals("recommend2")) {
                int x = Integer.parseInt(stk.nextToken());
                if (x == 1) {
                    System.out.println(problems.last().P);
                } else {
                    System.out.println(problems.first().P);
                }
            }
            //전체 중에서 && 난이도 L이상 가장 작은 || L이하 가장 큰 문제 번호
            else if (cmd.equals("recommend3")) {
                int x = Integer.parseInt(stk.nextToken());
                int L = Integer.parseInt(stk.nextToken());
                if (x == 1) {
                    if (problems.ceiling(new Problem(0, L, 0)) == null) {
                        System.out.println(-1);
                    } else {
                        System.out.println(problems.ceiling(new Problem(0, L, 0)).P);
                    }
                } else {
                    if (problems.floor(new Problem(0, L, 0)) == null) {
                        System.out.println(-1);
                    } else {
                        System.out.println(problems.floor(new Problem(0, L, 0)).P);
                    }
                }
            }
            //전체 중 추가(이전에 있던 문제분류가 들어올수 있음)
            else if (cmd.equals("add")) {
                int P = Integer.parseInt(stk.nextToken());
                int L = Integer.parseInt(stk.nextToken());
                int G = Integer.parseInt(stk.nextToken());
                problems.add(new Problem(P, L, G));
                if (problemMap.containsKey(G)) {
                    TreeSet<Problem> temp = problemMap.get(G);
                    temp.add(new Problem(P, L, G));
                    problemMap.replace(G, temp);
                } else {
                    TreeSet<Problem> temp = new TreeSet<>();
                    temp.add(new Problem(P, L, G));
                    problemMap.put(G, temp);
                }
                LMap.put(P, L);
                GMap.put(P, G);
            }
            //전체 중 삭제
            else {
                int P = Integer.parseInt(stk.nextToken());
                int L = LMap.get(P);
                int G = GMap.get(P);
                problems.remove(new Problem(P, L, G));
                problemMap.get(G).remove(new Problem(P, L, G));
                LMap.remove(P);
                GMap.remove(P);
            }
        }
    }
}
