package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 창용마을무리의개수_7465 {
	public static class Edge {
		int from, to;

		public Edge(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}
	}
	
	public static int N, M, answer;
	public static boolean[] check;
	public static int[] parents, rank;
	public static List<Edge> edgeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N + 1];
			rank = new int[N + 1];
			check = new boolean[N + 1];
			edgeList = new ArrayList<Edge>();

			make();
			
			for (int i = 0; i < M; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			
			answer = 0;
			
			for (int i = 1; i <= N; ++i) {
				// union만 할 경우 합쳤을 때 합치기 전의 최상위 부모의 부모값만 바뀌고 그 하위는 바뀌지 않으므로
				// 반드시 findSet을 한번 더 해 path compression을 해주어야 한다.
				int p = findSet(i);
				
				if (!check[p]) {
					check[p] = true;
					answer++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
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
