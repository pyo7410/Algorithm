import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 신입사원_1946 {
	public static int T, N;
	public static int[][] newcomers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			newcomers = new int[N][2];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				
				newcomers[i][0] = Integer.parseInt(st.nextToken());
				newcomers[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 서류 등수 순으로 정렬
			Arrays.sort(newcomers, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o1[0], o2[0]);
				}
				
			});
			
			// 1등은 무조건 합격
			int minScore = newcomers[0][1];
			int cnt = 0;
			
			// 2등부터는 앞의 등수와 면접 등수를 비교하여 낮을 경우 둘 다 낮은 등수가 되므로 탈락 
			for (int i = 1; i < N; ++i) {
				if (minScore < newcomers[i][1]) {
					cnt++;
					continue;
				}
				
				minScore = newcomers[i][1];
			}
			
			System.out.println(N - cnt);
		}
	}
}
