package src.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_21944_문제추천시스템Version2 {

    public static class GroupAlgo implements Comparable<GroupAlgo> {
        int idx;
        int level;
        int algo;

        public GroupAlgo(int idx, int level, int algo) {
            this.idx = idx;
            this.level = level;
            this.algo = algo;
        }

        public int compareTo(GroupAlgo o) {
            if (level - o.level == 0) {
                return idx - o.idx;
            } else {
                return level - o.level;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =null;

        int N = Integer.parseInt(br.readLine());

        TreeSet<GroupAlgo> ts = new TreeSet<>();
        Map<Integer, TreeSet<GroupAlgo>> algoTreeSet = new HashMap<>();
        Map<Integer, Integer> levelMap = new HashMap<>();
        Map<Integer, Integer> algoMap = new HashMap<>();

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int addNum = Integer.parseInt(st.nextToken());
            int addLevel = Integer.parseInt(st.nextToken());
            int addAlgo = Integer.parseInt(st.nextToken());
            ts.add(new GroupAlgo(addNum, addLevel, addAlgo));
            if (algoTreeSet.containsKey(addAlgo)) {
                TreeSet<GroupAlgo> temp = algoTreeSet.get(addAlgo);
                temp.add(new GroupAlgo(addNum, addLevel, addAlgo));
                algoTreeSet.replace(addAlgo,temp);
            }
            else{
                TreeSet<GroupAlgo> temp = new TreeSet<>();
                temp.add(new GroupAlgo(addNum, addLevel, addAlgo));
                algoTreeSet.put(addAlgo,temp);
            }
            levelMap.put(addNum, addLevel);
            algoMap.put(addNum, addAlgo);
        }

        int M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++) {
            String title = br.readLine();
            String[] titleArr = title.split(" ");
            switch (titleArr[0]) {
                case "recommend":
                    int recommendGroup = Integer.parseInt(titleArr[1]);
                    int recommendNum = Integer.parseInt(titleArr[2]);
                    if (recommendNum == 1) {
                        System.out.println(algoTreeSet.get(recommendGroup).last().idx);
                    } else {
                        System.out.println(algoTreeSet.get(recommendGroup).first().idx);
                    }
                    break;
                case "recommend2":
                    int recommendTwoNum = Integer.parseInt(titleArr[1]);
                    if(recommendTwoNum == 1){
                        System.out.println(ts.last().idx);
                    }else{
                        System.out.println(ts.first().idx);
                    }
                    break;
                case "recommend3":
                    int recommendThreeNum = Integer.parseInt(titleArr[1]);
                    int recommendThreeLevel = Integer.parseInt(titleArr[2]);
                    if (recommendThreeNum == 1) {
                        if (ts.ceiling(new GroupAlgo(0, recommendThreeLevel, 0)) == null) {
                            System.out.println(-1);
                        } else {
                            System.out.println(ts.ceiling(new GroupAlgo(0, recommendThreeLevel, 0)).idx);
                        }
                    } else {
                        if (ts.floor(new GroupAlgo(0, recommendThreeLevel, 0)) == null) {
                            System.out.println(-1);
                        } else {
                            System.out.println(ts.floor(new GroupAlgo(0, recommendThreeLevel, 0)).idx);
                        }
                    }
                    break;
                case "add":
                    int addP = Integer.parseInt(titleArr[1]);
                    int addL = Integer.parseInt(titleArr[2]);
                    int addG = Integer.parseInt(titleArr[3]);
                    ts.add(new GroupAlgo(addP, addL, addG));
                    if (algoTreeSet.containsKey(addG)) {
                        TreeSet<GroupAlgo> temp = algoTreeSet.get(addG);
                        temp.add(new GroupAlgo(addP, addL, addG));
                    }else{
                        TreeSet<GroupAlgo> temp = new TreeSet<>();
                        temp.add(new GroupAlgo(addP, addL, addG));
                        algoTreeSet.put(addG, temp);
                    }
                    levelMap.put(addP, addL);
                    algoMap.put(addP, addG);
                    break;
                case "solved":
                    int solvedP = Integer.parseInt(titleArr[1]);
                    int solvedL = levelMap.get(solvedP);
                    int solvedG = algoMap.get(solvedP);
                    ts.remove(new GroupAlgo(solvedP, solvedL, solvedG));
                    algoTreeSet.get(solvedG).remove(new GroupAlgo(solvedP, solvedL, solvedG));
                    levelMap.remove(solvedP);
                    algoMap.remove(solvedP);
                    break;
                default:
                    System.out.println("DEFAULT");
                    break;
            }
            }
    }
}
