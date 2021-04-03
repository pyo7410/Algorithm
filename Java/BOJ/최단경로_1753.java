import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 최단경로_1753 {
	public static class Edge {
		int v, w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}
	
	public static int V, E, K;
	public static boolean[] visited;
	public static int[] distance;
	public static List<Edge>[] edgeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(br.readLine());
		
		visited = new boolean[V + 1];
		distance = new int[V + 1];
		edgeList = new ArrayList[V + 1];
		
		for (int i = 0; i <= V; ++i) {
			edgeList[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			// 단방향 그래프!!
			edgeList[u].add(new Edge(v, w));
		}
		
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		// 시작점
		distance[K] = 0;
		
		for (int i = 1; i <= V; ++i) {
			int minIdx = 0;
			int min = Integer.MAX_VALUE;
			
			for (int j = 1; j <= V; ++j) {
				if (!visited[j] && min > distance[j]) {
					min = distance[j];
					minIdx = j;
				}
			}
			
			visited[minIdx] = true;
			
			for (Edge e : edgeList[minIdx]) {
				// 인접리스트이므로 0인 값은 들어있지않다!
				if (!visited[e.v] && distance[e.v] > distance[minIdx] + e.w) {
					distance[e.v] = distance[minIdx] + e.w;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder("");
		for (int i = 1; i <= V; ++i) {
			sb.append((visited[i] == false) ? "INF" : distance[i]).append("\n");
		}
		System.out.println(sb);
	}
}
