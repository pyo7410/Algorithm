package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 하나로_1251 {
	public static class Edge implements Comparable<Edge> {
		int from, to;
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	public static int N;
	public static double E, answer;
	public static int[] x, y;
	public static int[] parents, rank;
	public static Edge[] edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			parents = new int[N];
			rank = new int[N];
			x = new int[N];
			y = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; ++i) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; ++i) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			// 간선을 만든다.
			int e = (N * (N - 1)) / 2;
			int v = 0;
			edgeList = new Edge[e];
		
			for (int i = 0; i < N; ++i) {
				int x1 = x[i];
				int y1 = y[i];
				
				for (int j = i + 1; j < N; ++j) {
					int x2 = x[j];
					int y2 = y[j];
					
					// ?
					double weight = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
					edgeList[v++] = new Edge(i, j, weight * E);
				}
			}
			
			Arrays.sort(edgeList);
			
			make();
			answer = 0;
			
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					answer += edge.weight;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(Math.round(answer)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void make() {
		for (int i = 0; i < N; ++i) {
			parents[i] = i;
		}
	}
	
	public static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) {
			return false;
		}
		
		if (rank[aRoot] < rank[bRoot]) {
			parents[aRoot] = bRoot;
		}
		else {
			parents[bRoot] = aRoot;
			
			if (rank[aRoot] == rank[bRoot]) {
				rank[aRoot]++;
			}
		}
		
		return true;
	}
}
