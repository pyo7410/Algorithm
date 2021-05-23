import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벌꿀채취_2115 {
	public static int N, M, C, answer;
	public static int[][] box;
	public static int[][] maxBox;
	public static int[] number;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			box = new int[N][N];
			maxBox = new int[N][N];
			number = new int[2];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				
				for (int j = 0; j < N; ++j) {
					box[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j <= N - M; ++j) {
					powerset(i, j, 0, 0, 0);
				}
			}
			
			combination(0, 0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void powerset(int y, int x, int cnt, int sum, int pow) {
		if (sum > C) {
			return;
		}
		
		if (cnt == M) {
			maxBox[y][x - M] = (maxBox[y][x - M] < pow) ? pow : maxBox[y][x - M];
			return;
		}
			
		powerset(y, x + 1, cnt + 1, sum + box[y][x], pow + (box[y][x] * box[y][x]));
		powerset(y, x + 1, cnt + 1, sum, pow);
	}
	
	public static void combination(int cnt, int idx) {
		if (cnt == 2) {
			int max = 0;
					
			for (int i = 0; i < 2; ++i) {
				int y = number[i] / N;
				int x = number[i] % N;
				
				max += maxBox[y][x];
			}
			
			answer = (answer < max) ? max : answer;
			
			return;
		}
		
		for (int i = idx; i < (N * N) - M; ++i) {
			if (cnt == 0) {
				number[cnt] = i;
			}
			else if (cnt == 1) {
				number[cnt] = i + M;
			}
			
			combination(cnt + 1, i);
		}
	}
}
