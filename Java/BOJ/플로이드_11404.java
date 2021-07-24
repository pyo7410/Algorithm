import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class 플로이드_11404 {
	public static final int INF = 987654321;
	public static int n, m;
	public static int[][] dist;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		// 문제에서 주어지는 정점은 1부터 시작함
		dist = new int[n + 1][n + 1];
		
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (i == j) {
					continue;
				}
				
				// Integer.MAX_VALUE 사용시 플로이드-와샬 알고리즘 계산 시 
				// Integer.MAX_VALUE + 어떤 가중치가 될 경우 스택오버플로우가 발생하므로
				// Integer.MAX_VALUE보다 작으면서 스택오버플로우가 안나는 값을 사용해야 한다.
				// 여기서는 987654321을 사용
				dist[i][j] = INF;
			}
		}
		
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 문제에서 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다고 나와있으므로
			// 가장 적은 비용을 갖고있는 노선을 저장시킨다.
			dist[a][b] = (dist[a][b] > c) ? c : dist[a][b];
		}
		
		// 플로이드-와샬
		for (int k = 1; k <= n; ++k) {
			for (int i = 1; i <= n; ++i) {
				for (int j = 1; j <= n; ++j) {
					int distance = dist[i][k] + dist[k][j];
					dist[i][j] = (dist[i][j] > distance) ? distance : dist[i][j];
				}
			}			
		}
		
		StringBuilder sb = new StringBuilder("");
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				// 갈 수 없는 경우 즉, INF로 되어있는 부분은 문제에서 요구한대로 0으로 바꾸어준다.
				sb.append((dist[i][j] == INF) ? 0 : dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
