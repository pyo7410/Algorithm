import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 특정한최단경로_1504 {
	public static class Info implements Comparable<Info> {
		int v, dist;

		public Info(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Info o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	
	public static final int INF = 987654321;
	public static int N, E, v1, v2;
	public static long answer;
	public static boolean[] visited;
	public static int[] distance;
	public static List<Info>[] infoList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		infoList = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; ++i) {
			infoList[i] = new ArrayList<Info>();
		}
		
		for (int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			// 방향성이 없으므로 양방향 연결
			infoList[from].add(new Info(to, dist));
			infoList[to].add(new Info(from, dist));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		
		answer = go();
		
		// 문제에서 요구한 이동할 수 없는 경우가 있다면 -1을 출력
		System.out.println((answer >= INF) ? -1 : answer);
	}
	
	public static long go() {
		// 방안 1
		long dist1 = 0;
		
		// 1번 정점 부터 v1 까지 최단 거리
		dist1 += dijkstra(1, v1);
		
		// v1 부터 v2 까지 최단 거리
		dist1 += dijkstra(v1, v2);
		
		// v2 부터 N까지 최단 거리
		dist1 += dijkstra(v2, N);
		
		// 방안 2
		long dist2 = 0;
		
		// 1번 정점 부터 v2 까지 최단 거리
		dist2 += dijkstra(1, v2);
		
		// v2 부터 v1 까지 최단 거리
		dist2 += dijkstra(v2, v1);
		
		// v1 부터 N 까지 최단 거리
		dist2 += dijkstra(v1, N);
		
		return (dist1 < dist2) ? dist1 : dist2;
	}
	
	public static int dijkstra(int start, int end) {
		visited = new boolean[N + 1];
		distance = new int[N + 1];
		
		// 최단거리를 찾기 위해 큰 숫자로 초기화
		Arrays.fill(distance, INF);
		
		PriorityQueue<Info> pq = new PriorityQueue<Info>();
		pq.offer(new Info(start, 0));
		// 시작 정점 거리를 0으로
		distance[start] = 0;
		
		
		while (!pq.isEmpty()) {
			Info info = pq.poll();
			
			if (visited[info.v]) {
				continue;
			}
			// 방문처리하고
			visited[info.v] = true;
			
			// 정점에 인접한 점들을 전부 조사
			for (Info nextInfo : infoList[info.v]) {
				// 방문하지 않았거나 거리가 더 짧은 경로가 있다면
				if (!visited[nextInfo.v] && distance[nextInfo.v] > info.dist + nextInfo.dist) {
					
					// 더 짧은 경로의 길이로 갱신하고
					distance[nextInfo.v] = info.dist + nextInfo.dist;
					// 방문하게 하여 조사
					pq.offer(new Info(nextInfo.v, distance[nextInfo.v]));
				}
			}
		}
		
		return distance[end];
	}
}
