import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 중량제한_1939 {
	public static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(o.weight, this.weight);
		}
	}
	
	public static int N, M, factory1, factory2, answer;
	public static PriorityQueue<Edge> pq;
	public static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pq = new PriorityQueue<>();
		parents = new int[N + 1];
		
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			pq.offer(new Edge(A, B, C));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		
		factory1 = Integer.parseInt(st.nextToken());
		factory2 = Integer.parseInt(st.nextToken());
		
		make();
		
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			union(edge.from, edge.to);
			
			// 우선순위 큐를 사용해 가장 큰 가중치를 갖고 있는 Edge먼저 연결하므로
			// 만약 두 공장이 같은 부모를 같는다면 이는 연결됬다는 의미이고
			// 그때의 가중치가 가장 큰 가중치가 되어 찾고자하는 최댓값이 된다.
			if (findSet(factory1) == findSet(factory2)) {
				answer = edge.weight;
				break;
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
