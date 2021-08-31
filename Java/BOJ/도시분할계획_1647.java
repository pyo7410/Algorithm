import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 도시분할계획_1647 {
	public static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	public static int N, M;
	public static int[] parents;
//	public static Edge[] edgeList;
	public static PriorityQueue<Edge> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parents = new int[N + 1];
//		edgeList = new Edge[M];
		pq = new PriorityQueue<>();
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
//			edgeList[i] = new Edge(A, B, C);
			pq.offer(new Edge(A, B, C));
		}
		
//		Arrays.sort(edgeList);
		make();
		
		int weight = 0;
		int cnt = 0;
		
		/*
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				weight += edge.weight;
				
				// 두 마을로 나누었을때, 한 마을 이상은 무조건 포함되어야 하고
				// 크루스칼을 사용해서 마을을 연결할 경우 가중치 순으로 정렬하여
				// 연결을 시도하므로 가장 큰 가중치를 가지는 즉, 크루스칼을 수행하여
				// 마지막에 합쳐지는 길을 빼면 이 길이 가장 큰 가중치 이므로 유지비를 최소로 할 수 있다.
				
				// 이때, cnt는 0부터 시작했으므로 -1이 아닌 -2를 해야한다.
				if (++cnt == N - 2) {
					break;
				}
			}
		}
		*/
		
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			if (union(edge.from, edge.to)) {
				weight += edge.weight;
				
				// 두 마을로 나누었을때, 한 마을 이상은 무조건 포함되어야 하고
				// 크루스칼을 사용해서 마을을 연결할 경우 가중치 순으로 정렬하여
				// 연결을 시도하므로 가장 큰 가중치를 가지는 즉, 크루스칼을 수행하여
				// 마지막에 합쳐지는 길을 빼면 이 길이 가장 큰 가중치 이므로 유지비를 최소로 할 수 있다.
				
				// 이때, cnt는 0부터 시작했으므로 -1이 아닌 -2를 해야한다.
				if (++cnt == N - 2) {
					break;
				}
			}
		}
		
		System.out.println(weight);
	}
	
	public static void make() {
		for (int i = 1; i <= N; ++i) {
			parents[i] = i;
		}
	}
	
	public static int findSet(int a) {
		if (a == parents[a]) {
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
