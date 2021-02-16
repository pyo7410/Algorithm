package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 몬스터사냥_11387 {
	public static int D, L, N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			int damage = 0;
			int percentage = (int) (L * 0.01);
			for (int i = 0; i < N; ++i) {
				damage += D * (1 + (i * (percentage)));
			}
			
			sb.append("#").append(tc).append(" ").append(damage).append("\n");
		}
		
		System.out.println(sb);
	}
}
