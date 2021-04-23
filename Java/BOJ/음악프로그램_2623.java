import java.io.*;
import java.util.*;

public class 음악프로그램_2623 {
	public static int N, M;
	public static int[] in;
	public static int[][] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		in = new int[N];
		adj = new int[N][N];
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int num = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken()) - 1;
			
			for (int j = 1; j < num; ++j) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				
				if (adj[from][to] == 0) {
					adj[from][to] = 1;
					in[to]++;					
				}
				
				from = to;
			}
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < N; ++i) {
			if (in[i] == 0) {
				q.offer(i);
			}
		}
		
		int cnt = 0;
		while (!q.isEmpty()) {
			// 큐를 돌다가 사이클발생이 가능!
			cnt++;
			
			int node = q.poll();
			sb.append(node + 1).append("\n");
			
			for (int i = 0; i < N; ++i) {
				if (adj[node][i] == 1) {
					in[i]--;
					
					if (in[i] == 0) {
						q.offer(i);
					}
				}
			}
		}
		
		System.out.println((cnt != N) ? 0 : sb);
	}
}
