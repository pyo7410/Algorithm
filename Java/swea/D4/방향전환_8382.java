package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 방향전환_8382 {
	public static int x1, y1, x2, y2, answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = null;		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine(), " ");
			
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			int x = Math.abs(x1 - x2);
			int y = Math.abs(y1 - y2);
			int dist = x + y;
			
			if (dist % 2 == 0) {
				answer = 2 * ((x < y) ? y : x);
			}
			else {
				answer = (2 * ((x < y) ? y : x)) - 1;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}
