import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭_12865 {
	public static int N, K;
	public static int[][] objects;
	public static int[][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 0 : 무게, 1 : 가치
		objects = new int[N + 1][2];
		memo = new int[N + 1][K + 1];
		
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			// 무게
			objects[i][0] = Integer.parseInt(st.nextToken());
			
			// 가치
			objects[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= K; ++j) {
				if (j < objects[i][0]) {
					// j보다 objects[i][0]이 크다면 즉, 현재의 물건의 무게가 더 크다면
					// 가방에 넣을 수 없으므로 이전의 최적값으로 갱신
					memo[i][j] = memo[i - 1][j];
				}
				else {
					// j보다 현재의 물건의 무게가 작거나 같다면 가방에 넣을 수 있고
					// 이때, 가방에 물건을 넣는 경우의 가치가 더 적을수도 있으므로 
					// 이전의 최적값의 경우가 다 다르므로 비교를 해야함
					memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - objects[i][0]] + objects[i][1]);										
				}
			}
		}
		
		System.out.println(memo[N][K]);
	}
}
