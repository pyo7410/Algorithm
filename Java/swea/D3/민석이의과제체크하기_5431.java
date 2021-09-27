package D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 민석이의과제체크하기_5431 {
	public static boolean[] submitCheck;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {			
			String input = br.readLine();
			StringTokenizer st = new StringTokenizer(input, " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			submitCheck = new boolean[N + 1];
			
			input = br.readLine();
			st = new StringTokenizer(input, " ");
			for (int i = 0; i < K; ++i) {
				submitCheck[Integer.parseInt(st.nextToken())] = true;
			}
			
			sb.append("#").append(tc).append(" ");
			for (int i = 1; i <= N; ++i) {
				if (!submitCheck[i]) {
					sb.append(i).append(" ");
				}
			}
			
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
