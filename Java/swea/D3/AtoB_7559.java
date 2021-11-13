package D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AtoB_7559 {
	public static long A, B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			
			long cnt = 1;
			while (A < B) {
				if (B % 2 == 0) {
					B /= 2;
				}
				else if (B % 10 == 1) {
					B /= 10;
				}
				else {
					break;
				}
				
				cnt++;
			}
			
			sb.append("#").append(tc).append(" ").append((A == B) ? cnt : -1).append("\n");
		}
		
		System.out.println(sb);
	}
}
