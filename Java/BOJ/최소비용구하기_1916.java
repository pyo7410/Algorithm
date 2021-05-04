import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소비용구하기_1916 {
	public static class Edge implements Comparable<Edge> {
		int v, weight;

		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	public static int N, M, start, end;
	public static boolean[] visited;
	public static int[] distance;
	public static PriorityQueue<Edge> pq;
	public static List<Edge>[] edgeList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		edgeList = new ArrayList[M + 1];
		
		for (int i = 1; i <= M; ++i) {
			edgeList[i] = new ArrayList<Edge>();
		}
		
		for (int i = 1; i <= M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList[from].add(new Edge(to, weight));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N + 1];
		distance = new int[N + 1];
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		pq = new PriorityQueue<Edge>();
		pq.offer(new Edge(start, distance[start]));
		
		for (int i = 1; i <= N; ++i) {
			Edge edge = pq.poll();
			
			while (!pq.isEmpty() && visited[edge.v]) {
				edge = pq.poll();
			}
			
			int min = edge.weight;
			int cur = edge.v;
			
			visited[cur] = true;
			
			if (cur == end) {
				break;
			}
			
			for (Edge e : edgeList[cur]) {
				if (!visited[e.v] && distance[e.v] > min + e.weight) {
					distance[e.v] = min + e.weight;
					pq.offer(new Edge(e.v, distance[e.v]));
				}
			}
		}
		
		System.out.println(distance[end]);
	}
}
