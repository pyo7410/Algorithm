import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의지름_1167 {
	public static class Edge {
		int v, weight;

		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
	}
	public static int V, answer, maxVertex;
	public static boolean[] visited;
	public static List<Edge>[] edgeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		V = Integer.parseInt(br.readLine());
		
		edgeList = new ArrayList[V + 1];
		
		for (int i = 1; i <= V; ++i) {
			edgeList[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= V; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int from = Integer.parseInt(st.nextToken());
			
			while (true) {
				int to = Integer.parseInt(st.nextToken());
				
				if (to == -1) {
					break;
				}
				
				int weight = Integer.parseInt(st.nextToken());
				
				edgeList[from].add(new Edge(to, weight));
			}
		}
		
		visited = new boolean[V + 1];
		go(1, 0);
		visited = new boolean[V + 1];
		go(maxVertex, 0);
		
		System.out.println(answer);
	}
	
	public static void go(int v, int w) {
		if (answer < w) {
			answer = w;
			maxVertex = v;
		}
		
		visited[v] = true;
		
		for (Edge edge : edgeList[v]) {
			if (!visited[edge.v]) {
				go(edge.v, edge.weight + w);
			}
		}
	}
}
