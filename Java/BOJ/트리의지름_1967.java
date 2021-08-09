import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의지름_1967 {
	public static class Edge {
		int v, w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}
	
	public static int n, answer, maxIdx;
	public static boolean[] visited;
	public static List<Edge>[] edgeList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;		
		
		n = Integer.parseInt(br.readLine());
		
		// 노드는 1부터 시작하므로
		edgeList = new ArrayList[n + 1];
		
		for (int i = 0; i <= n; ++i) {
			edgeList[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < n - 1; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			// 방향이 없다는 의미는
			// 양방향으로 갈 수 있다는 이야기
			edgeList[from].add(new Edge(to, weight));
			edgeList[to].add(new Edge(from, weight));
		}
		
		// 노드는 1부터 시작
		visited = new boolean[n + 1];
		// 지름 구하기
		getRadius(1, 0);
		
		// 가장 긴 지름을 갖고있는 노드와 연결된 간선은
		// 가중치가 큰 수들로 이루어졌음이 보장되므로
		// 해당 노드부터 시작하여 더 큰 지름을 구할 수 있다면
		// 그 지름이 가장 긴 지름이 된다.
		// 노드는 1부터 시작
		visited = new boolean[n + 1];
		getRadius(maxIdx, 0);
		
		System.out.println(answer);
	}
	
	public static void getRadius(int v, int w) {
		// 기존의 지름보다 큰 지름이라면
		if (answer < w) {
			// 지름 값 갱신
			answer = w;
			// 가장 긴 지름을 갖고있는 노드를 저장
			maxIdx = v;
		}
		
		// 방문 처리
		visited[v] = true;
		
		// 현재 점과 연결된 모든 점을 방문
		for (Edge edge : edgeList[v]) {
			// 만약 방문한 적이 있는 점이라면 방문 X
			if (!visited[edge.v]) {
				// 지름을 구하기 위해 현재까지 구한 지름에 방문하고자하는 다음 점까지의 길이를 더한다.
				getRadius(edge.v, w + edge.w);
			}
		}
	}
}
