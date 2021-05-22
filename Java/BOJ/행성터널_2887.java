import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 행성터널_2887 {
	public static class Info {
		int dist, idx;

		public Info(int dist, int idx) {
			super();
			this.dist = dist;
			this.idx = idx;
		}
	}
	public static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	public static int N, answer;
	public static int[] parents;
	public static List<Info> xList, yList, zList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		xList = new ArrayList<Info>();
		yList = new ArrayList<Info>();
		zList = new ArrayList<Info>();
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			xList.add(new Info(x, i));
			yList.add(new Info(y, i));
			zList.add(new Info(z, i));
		}
		
		Collections.sort(xList, new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.dist, o2.dist);
			}
		});
		Collections.sort(yList, new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.dist, o2.dist);
			}
		});
		Collections.sort(zList, new Comparator<Info>() {
			@Override
			public int compare(Info o1, Info o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.dist, o2.dist);
			}
		});
		
		List<Edge> edgeList = new ArrayList<Edge>();
		for (int i = 0; i < N - 1; ++i) {
			int distX = Math.abs(xList.get(i).dist - xList.get(i + 1).dist);
			edgeList.add(new Edge(xList.get(i).idx, xList.get(i + 1).idx, distX));
			
			int distY = Math.abs(yList.get(i).dist - yList.get(i + 1).dist);
			edgeList.add(new Edge(yList.get(i).idx, yList.get(i + 1).idx, distY));
			
			int distZ = Math.abs(zList.get(i).dist - zList.get(i + 1).dist);
			edgeList.add(new Edge(zList.get(i).idx, zList.get(i + 1).idx, distZ));
		}
		
		parents = new int[N + 1];
		
		Collections.sort(edgeList);
		
		make();
		
		int cnt = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				answer += edge.weight;
				
				if (++cnt == N - 1) {
					break;
				}
			}
		}
		
		System.out.println(answer);
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
