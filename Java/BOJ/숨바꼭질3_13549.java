import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 숨바꼭질3_13549 {
	public static class Info {
		int cur, cnt;

		public Info(int cur, int cnt) {
			super();
			this.cur = cur;
			this.cnt = cnt;
		}
		
		public Info() {}
	}
	public static int N, K, answer;
	public static boolean[] visited = new boolean[100001];
	public static int[] dist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
//		bfs();
		dijkstra();
		
		System.out.println(dist[K]);
	}
	
	public static void bfs() {
		// 0-1 BFS : 가중치가 0인 간선에 연결된 정점은 큐의 맨뒤가 아닌 맨앞에 넣는 방법
		Deque<Info> deque = new LinkedList<Info>();
		deque.offer(new Info(N, 0));
		visited[N] = true;
		
		while (!deque.isEmpty()) {
			Info info = deque.poll();
			
			if (info.cur == K) {
				answer = info.cnt;
				return;
			}
			
			int point = info.cur * 2;
			if (point <= 100000 && !visited[point]) {
				deque.addFirst(new Info(point, info.cnt));
				visited[point] = true;
			}
			
			point = info.cur - 1;
			if (point >= 0 && !visited[point]) {
				deque.addLast(new Info(point, info.cnt + 1));
				visited[point] = true;
			}
			
			point = info.cur + 1;
			if (point <= 100000 && !visited[point]) {
				deque.addLast(new Info(point, info.cnt + 1));
				visited[point] = true;
			}
			
		}
	}
	
	public static void dijkstra() {
		PriorityQueue<Info> pq = new PriorityQueue<Info>(new Comparator<Info>() {

			@Override
			public int compare(Info o1, Info o2) {
				return Integer.compare(o1.cnt, o2.cnt);
			}
			
		});
		
		dist = new int[100001];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.offer(new Info(N, 0));
		dist[N] = 0;
		
		while (!pq.isEmpty()) {
			Info info = pq.poll();
			
			if (dist[info.cur] < info.cnt) {
				continue;
			}
			
			int[] nextPoints = new int[] {info.cur - 1, info.cur + 1, info.cur * 2};
			
            for (int i = 0; i < nextPoints.length; i++) {
                if(0 > nextPoints[i] || nextPoints[i] > 100000) {
                	continue;
                }
 
               int second = (i == 2) ? 0 : 1;
 
                if(dist[nextPoints[i]] > info.cnt + second) {
                    pq.offer(new Info(nextPoints[i], info.cnt + second));
                    dist[nextPoints[i]] = info.cnt + second;
                }
            }
		}
	}
}
