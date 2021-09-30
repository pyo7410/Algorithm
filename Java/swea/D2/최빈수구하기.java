package D2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 최빈수구하기 {
	public static int[] count;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			br.readLine();
			
			// 초기화 필요
			count = new int[101];
			
			String score = br.readLine();
			StringTokenizer st = new StringTokenizer(score, " ");
			
			for (int i = 0; i < 1000; ++i) {
				count[Integer.parseInt(st.nextToken())]++;
			}
			
			int maxCnt = 0;
			int answer = 0;
			for (int i = 100; i >= 0; i--) {
				if (maxCnt < count[i]) {
					maxCnt = count[i];
					answer = i;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
