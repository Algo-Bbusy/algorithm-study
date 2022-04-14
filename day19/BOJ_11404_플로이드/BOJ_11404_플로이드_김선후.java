package anystep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Java_11404 {
	// 플로이드
	// 간단하게 플로이드 워샬 알고리즘만 사용하면 풀리는 문제인 줄 알았으나 도중에 고쳐야 할 것이 참으로 많았다.
	// Solution 1.
	//1. 자기 자신으로 향하는 경우의 비용을 0으로 처리하고 from to의 관계가 미연결 상태일 시 INF를 받는다.(Integer.MAX_VALUE는 출력초과 오답)
	//2. 인접행렬을 입력받을 떄 중첩해서 여러번 오는 경우를 방지하기 위해 비용이 최소비용일 경우에만 입력받도록 한다.
	//3. 플로이드 워샬 알고리즘 실행
	//4. 인접행렬에 INF가 남아있으면 0으로 바꾼후 출력한다.(안그러면 오답)
	//메모리 43460KB 시간 456ms
	//풀이시간 41분 12초
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer stk;
	static int N,M;
	static int[][] adjMatrix;
	static final int INF = 100 * 100000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(in.readLine());
		M = Integer.parseInt(in.readLine());
		adjMatrix = new int[N+1][N+1];
		for(int from=1; from<=N; from++) {
			
			for(int to=1; to<=N; to++) {
				//인접행렬에 Integer.MAX_VALUE가 아닌 큰수로 넣었어야 함
				if(from!=to && adjMatrix[from][to]==0) adjMatrix[from][to]=INF;
			}
		}
		for(int i=0; i<M; i++) {
			stk = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			//여기서 최소값만 입력받아야 했는데 나중에 눈치채서 시간이 걸림
//			adjMatrix[from][to]=Integer.parseInt(stk.nextToken());
			adjMatrix[from][to]=Math.min(Integer.parseInt(stk.nextToken()), adjMatrix[from][to]);
		}
		for(int k=1; k<=N; k++) {
			for(int from=1; from<=N; from++) {
				if(from==k) continue;
				for(int to=1; to<=N; to++) {
					if(from==to || to==k) continue;
					if(adjMatrix[from][to]>adjMatrix[from][k] + adjMatrix[k][to]) {
						adjMatrix[from][to] = adjMatrix[from][k] + adjMatrix[k][to];
					}
				}
			}
		}
		//INF를 0으로 처리해놓지 않으면 출력 초과가 나옴
		for(int from=1; from<=N; from++) {
			for(int to=1; to<=N; to++){
				if(adjMatrix[from][to]==INF) adjMatrix[from][to]=0;
			}
		}
		for(int from=1; from<=N; from++) {
			for(int to=1; to<=N; to++){
				sb.append(adjMatrix[from][to]).append(" ");
			}
			sb.append("\n");
		}
		out.write(sb.toString());
		out.flush();
		out.close();
		in.close();
	}

}
