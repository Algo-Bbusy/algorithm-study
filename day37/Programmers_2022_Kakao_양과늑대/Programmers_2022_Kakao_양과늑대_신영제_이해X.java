package AlgoStudy;

import java.util.ArrayList;

public class PRO_양과늑대 {
	  static ArrayList<Integer>[] childs;
	    static int max = 0;

	    public static void main(String[] args) {
	        int[] info = { 0,0,1,1,1,0,1,0,1,0,1,1 };
	        int[][] edges = { {0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9} };
	        System.out.println(solution(info, edges));
	    }

	    static int solution(int[] info, int[][] edges) {
	        childs = new ArrayList[info.length];
	        for(int[] edge: edges){
	            int parent = edge[0];
	            int child = edge[1];
	            if(childs[parent]==null) {
	                childs[parent] = new ArrayList<>();
	            }
	            childs[parent].add(child);
	        }

	        ArrayList<Integer> available = new ArrayList<>();
	        available.add(0);
	        /**
	         * 0 : 양의 수
	         * 0 : 늑대의 수
	         * 0 : 현재 위치
	         * available : 다음 방문 노드 확인
	         * info : 위치의 양, 늑대 확인
	         */
	        dfs(0,0,0,available,info);

	        return max;
	    }

	    static void dfs(int sheepNum, int wolfNum, int cur, ArrayList<Integer> available, int[] info){
	        // xor 연산 활용 [ 배운 점 ]
	        // 현재 위치 : cur
	        // 현재 위치에 양이 있다면 (0) sheep에 +1 이 더해 지고 wolf에는 0이 더해진다.
	        // cur에 늑대가 있다면 (1)  wolf에는 1이 더해지고 sheep에는 0이 더해진다.
	        sheepNum += info[cur] ^ 1;
	        wolfNum += info[cur];
	        max = Math.max(sheepNum,max);

	        if(sheepNum<=wolfNum)   return;

	        ArrayList<Integer> copyAvailable = new ArrayList<>();
	        // addAll : 인자로 전달되는 Collection 객체의 모든 아이템을 리스트에 추가.
	        copyAvailable.addAll(available);

	        if(childs[cur]!=null) {
	            copyAvailable.addAll(childs[cur]);
	        }
	        copyAvailable.remove(Integer.valueOf(cur));

	        for(int next: copyAvailable)
	            dfs(sheepNum,wolfNum,next, copyAvailable, info);
	    }
	}