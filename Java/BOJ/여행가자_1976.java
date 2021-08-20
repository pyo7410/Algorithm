import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 여행가자_1976 {
	public static class Edge {
		int from, to;
		
		public Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	public static int N, M;
	public static int[] parents, cities;
	public static List<Edge> edgeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;		
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N + 1];
		cities = new int[M];
		edgeList = new ArrayList<>();
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; ++j) {
				int node = Integer.parseInt(st.nextToken());
				
				if (node == 1) {
					edgeList.add(new Edge(i, j));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; ++i) {
			cities[i] = Integer.parseInt(st.nextToken());
		}
		
		make();
		
		for (Edge edge : edgeList) {
			union(edge.from, edge.to);
		}
		
		for (int i = 1; i < M; ++i) {
			if (findSet(cities[i]) != findSet(cities[i - 1])) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		
		System.out.println("YES");
	}
	
	public static void make() {
		for (int i = 1; i <= N; ++i) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int a) {
		if (parents[a] == a) {
			return a;
		}
		
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		return true;
	}
}
