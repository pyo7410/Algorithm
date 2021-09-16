package D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퍼펙트셔플_3499 {
	public static int N;
	public static String[] deck;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());
			deck = new String[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; ++i) {
				deck[i] = st.nextToken();
			}
			
			int idx = N / 2;
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < N / 2; ++i) {
				sb.append(deck[i]).append(" ");
				
				if (N % 2 == 0) {
					sb.append(deck[i + idx]);
				}
				else {
					sb.append(deck[i + idx + 1]);
				}
				
				sb.append(" ");
			}
			
			if (N % 2 != 0) {
				sb.append(deck[idx]);
			}
			
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
