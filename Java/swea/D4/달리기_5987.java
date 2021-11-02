package D4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 달리기_5987 {
	public static int N, M, p;
	public static long answer;
	public static boolean[] isSelected;
	public static List<Integer>[] person;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			person = new ArrayList[N + 1];
			isSelected = new boolean[N + 1];
			
			for (int i = 1; i < N + 1; ++i) {
				person[i] = new ArrayList<Integer>();
				person[i].add(0);
			}
			
			for (int i = 0; i < M; ++i) {
				st = new StringTokenizer(br.readLine());
				
				person[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}
			
			answer = 0;
			permutation(0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void permutation(int cnt) {
		if (cnt == N) {
			answer++;
			return;
		}
		
		for (int i = 1; i < N + 1; ++i) {
			if (isSelected[i]) {
				continue;
			}
			
			for (int n : person[i]) {
				if (isSelected[n]) {
					return;
				}
			}
			
			isSelected[i] = true;
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}
}
