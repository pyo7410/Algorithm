package D6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 작업순서_1267 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(" ").append(tc).append(" ");
			int V = sc.nextInt();
			int E = sc.nextInt();
			int[] indegree = new int[V];
			int[][] adj = new int[V][V];
			
			for (int i = 0; i < E; ++i) {
				int from = sc.nextInt() - 1;
				int to = sc.nextInt() - 1;
				
				adj[from][to] = 1;
				indegree[to]++;
			}
			
			Queue<Integer> queue = new LinkedList<Integer>();
			for (int i = 0; i < V; ++i) {
				if (indegree[i] == 0) {
					queue.offer(i);
				}
			}
			
			while (!queue.isEmpty()) {
				int node = queue.poll();
				sb.append(node + 1).append(" ");
				
				for (int i = 0; i < V; ++i) {
					if (adj[node][i] == 1) {
						indegree[i]--;
						
						if (indegree[i] == 0) {
							queue.offer(i);							
						}
					}
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
		sc.close();
	}
}
