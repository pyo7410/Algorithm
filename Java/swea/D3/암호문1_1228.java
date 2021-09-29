package D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 암호문1_1228 {
	public static int N, M;
	public static List<Integer> originCrypt = new LinkedList<Integer>();
	public static String command;
	public static String[] answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; ++tc) {
			N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; ++i) {
				originCrypt.add(Integer.parseInt(st.nextToken()));
			}
			
			M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			
			while (st.hasMoreTokens()) {
				String command = st.nextToken();
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				for (int i = 0; i < y; ++i) {
					originCrypt.add(x + i, Integer.parseInt(st.nextToken()));
				}
			}
			
			sb.append("#").append(tc).append(" ");
			
			for (int i = 0; i < 10; ++i) {
				sb.append(originCrypt.get(i)).append(" ");
			}
			
			System.out.println(sb);
			originCrypt.clear();
			sb.setLength(0);
		}
	}
}
