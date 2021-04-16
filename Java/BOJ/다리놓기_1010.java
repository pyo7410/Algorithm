import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 다리놓기_1010 {
	public static long[][] memo;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; ++tc) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			memo = new long[31][31];
			
			// 하나의 다리를 두는 경우를 구한다.
	        for (int i = 1; i <= M; ++i) {
	        	// 이때 i개의 동쪽 사이트에  하나의 다리를 두는 경우의 수는 i개가 된다.
	        	memo[1][i] = i;
	        }

	        // 2개이상의 다리를 놓는 경우의 수를 구한다.
	        for (int i = 2; i <= N; ++i) {
	        	// i는 다리의 개수이므로 만약 두개의 다리를 놓는다면
	        	// 왼쪽 2번 사이트가 오른쪽 1번 사이트로 연결할 경우 최대의 다리개수가 안되므로
	        	// 즉, 다리끼리 교차되면 안되므로 i번 이후의 다리들만 보면 된다.
	        	// memo에는 이전 i - N개의 다리를 연결하는 가지수들이 저장되어있기 때문에 가능
	        	for (int j = i; j <= M; ++j) {
	        		
	        		// 마찬가지로 오른쪽 사이트마다 설치할 수 있는 경우를 볼때에도
	        		// i는 다리의 개수이므로 다리끼리 교차되면 안되므로 i번 이후의 다리들만 보면된다.
	        		// 현재 i개의 다리를 설치할때의 경우의 수만 보고싶으므로
	        		for (int k = j - 1; k >= 0; k--) {
	        			
	        			// i개의 다리를 설치하는 경우의 수는
	        			// i - 1개의 다리를 설치할 때의 j번의 동쪽 포인트이전의
	        			// 경우의수를 전부 더해주면된다.
	        			// 만약 3개의 다리를 3번 동쪽 포인트까지만 설치한다면
	        			// 다리는 교차될 수 없으므로 왼쪽포인트의 윗 부분과 동쪽포인트의 윗 부분 즉 1번부터 설치가 되어야하고
	        			// 1번에 다리를 설치하고 2번에 다리를 설치한 후 3번에 설치하면 경우의 수를 구할 수 있기때문이다.
	        			memo[i][j] += memo[i - 1][k];
	        		}
	        	}
	        }
			
			System.out.println(memo[N][M]);
		}
	}
}
